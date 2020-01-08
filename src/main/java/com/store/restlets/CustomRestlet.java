package com.store.restlets;

import org.restlet.Restlet;

public abstract class CustomRestlet extends Restlet{
	public static final String RESPONSE_ATTRIBUTE_STATUS = "status";
	public static final String RESPONSE_ATTRIBUTE_RESULTS= "results";
	
	public enum Status{
		SUCCESS("success"), NO_RESULTS_FOUND("no results found");
		
		private String status;
		Status(String status){
			this.status = status;
		}
		
		@Override
		public String toString() {
			return status;
		}
	}
}
