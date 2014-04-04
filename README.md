City weather demo application
==================================================
Android version: 2.3.3

source: /ink-weather/

tests: /ink-weather/tests/

dependancies: /ink-weather/lib/

The application can be downloaded on google play at:
https://play.google.com/store/apps/details?id=com.ink.weather

# About
This demo application uses the openweathermap.org API to query and return weather details for world cities. It utilises the Volley networking
library for retrieving JSON data and images. Instead of using JSONObjects directly in the application code, the response from the web service
is deserialized into data transfer objects (DTO), these DTOs are a java object representation of the api response. The user can select more 
weather details from a list and also search for a custom city.

# Networking / API tier
A VolleyRequest wrapper has been written to provide a generic method for deserialising the json responses to their DTO representation. 
Any activities that use the wrapper must implement the VolleyResponseCallback, an observer pattern is used here to send a response 
back to the activity when it is available, these responses are in the form of an object that implements IDTO. The expected response class 
definition (implements IDTO) is provided as an argument to the VolleyRequest wrapper and when a response is available reflection is used to 
create a new instance of the class. The IDTO toJson() method is then populated with the JSONObject response and the VolleyResponseCallback is 
triggered.

**Why?**

This approach decouples the raw api data from the application code, it also encapsulates the data transfer format within the DTO objects. If the api
depreciated JSON and switched to an XML only format, a toXML() method could be added to IDTO and a new parsing implementation written for each DTO 
without having to modify any other application code (apart from changing toJson() -> toXML() in VolleyRequest).

# WeatherApplication singleton
The WeatherApplication class inherits android.app.Application and follows the singleton pattern, it creates an instance of RequestQueue and exposes 
it as an accessor method.

**Why?**

Rather than creating a new instance of the volley RequestQueue each time it is required, it is attached to the WeatherApplication singleton 
throughout the lifecycle of the application. 

# NavigationDrawer
The MainActivity of the application utilises a NavigationDrawer that allows the user to search for and pick other city weather data. The activity 
inherits from a BaseActivity which contains the logic for setting up and animating the NavigationDrawer content. BaseActivity has three abstract 
classes; getContentLayout(), getNavigationContentLayout() and getNavigationDrawer(), these methods are implemented by the Activity and are used to 
provide the BaseActivity with the layouts it requires to animate the NavigationDrawer.

**Why?**

If the application was extended to include multiple activities that use a NavigationDrawer, no code duplication would be required. The responsibility 
of the NavigationDrawer has already been given to BaseActivity, any Activity that extends from it will also obtain the functionality. 

# Dialog
The DialogActivity displays a modal dialog with a message and an optional "OK" button, it is used while data is loading and when errors occur. Intent 
extras are used to provide the dialog with the message and a flag to indicate what button should be shown. A broadcast receiver is registered to listen
for activities that want to notify the dialog to close. Helper methods for opening and closing dialogs are part of the BaseActivity.

# Testing considerations
- Unit tests have been written for the DTO json deserialisation classes to ensure the DTO data members match the original JSONObject data members, a 
discrepancy here would introduce critical bugs. 
- Unit tested the ReflectionHelper to ensure it creates object instances that can be cast into objects that implement IDTO.
- Tested the application on ldpi, mdpi, hdpi and xhdpi simulators to ensure the ui scales correctly
- Tested the application on 2.3.3 and 4.2.1 operating systems to confirm the application is backward compatible across the major versions

# TODO:
- More Android Framework specific test coverage (activities, fragments, views intents)
- Build the application with Gradle 
- Implement a "current weather option as well as the 7 day forecast
- The temp displayed in the 7 day forecast should be the average of that day
- Create a settings screen where the user can switch between degrees and fahrenheit
- Create a widget for the 7 day and current views 