package org.openweathermap.adapter;

import org.openweather.R;
import org.openweathermap.dto.CityDTO;
import org.openweathermap.utils.StringHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * An adapter to display a list of City DTO using the view_city_list_item layout resource
 * @author samkirton
 */
public class CityArrayAdapter extends ArrayAdapter<CityDTO> {
	private final Context mContext;
	private final CityDTO[] mValues;

	public CityArrayAdapter(Context context, CityDTO[] values) {
		super(context, R.layout.view_city_list_item, values);
		this.mContext = context;
		this.mValues = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = (View)inflater.inflate(R.layout.view_city_list_item, parent, false);
			
			viewHolder = new ViewHolder();
			viewHolder.uiTitleTextView = (TextView)convertView.findViewById(R.id.view_city_list_title);
			viewHolder.uiLocationTextView = (TextView)convertView.findViewById(R.id.view_city_list_location);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
	    
		// get the city name and location coords from the CityDTO
	    CityDTO cityDTO = mValues[position];
	    String city = cityDTO.getName() + " (" + cityDTO.getCountry() + ")";
	    String locationCoords = StringHelper.buildLocationString(
	    	cityDTO.getCoord().getLat(), 
	    	cityDTO.getCoord().getLon()
	    );
	    
	    viewHolder.uiTitleTextView.setText(city);
	    viewHolder.uiLocationTextView.setText(locationCoords);
	    
	    return convertView;
	}
	
	/**
	 * A static class that is used to hold a reference of the row view 
	 * in memory so that multiple views do not need to be inflated 
	 * for the same common row layout
	 */
	private static class ViewHolder {
		TextView uiTitleTextView;
        TextView uiLocationTextView;
	}
}