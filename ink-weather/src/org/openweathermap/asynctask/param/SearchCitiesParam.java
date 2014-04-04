package org.openweathermap.asynctask.param;

import org.openweathermap.asynctask.base.IParam;

/**
 * @author samkirton
 */
public class SearchCitiesParam implements IParam {
	private String mSearchTerms;
	
	public String getSearchTerms() {
		return mSearchTerms;
	}
	
	public void setSearchTerms(String newVal) {
		mSearchTerms = newVal;
	}
}
