package com.oljubuncic1.entities;

import java.util.List;

public class CSVConfiguration extends Configuration {
	
	
private List<String> data;
private Boolean hasHeaders;
	
	

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
		
	}

	public Boolean getHasHeaders() {
		return hasHeaders;
	}

	public void setHasHeaders(Boolean hasHeaders) {
		this.hasHeaders = hasHeaders;
	}

}
