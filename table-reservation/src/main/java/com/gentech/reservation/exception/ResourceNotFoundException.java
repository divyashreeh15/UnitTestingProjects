package com.gentech.reservation.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldValue;
	private Object valueof;
	
	public ResourceNotFoundException()
	{
		
	}
	public ResourceNotFoundException(String resourceName,String fieldValue,Object valueof)
	{
		super(String.format("Resource named:'%s' with '%s' does not have '%s' ",resourceName, fieldValue,valueof));
		
		this.resourceName=resourceName;
		this.fieldValue=fieldValue;
		this.valueof=valueof;
		
		
	}
	public String getResourceName() {
		return resourceName;
	}
	
	public String getFieldValue() {
		return fieldValue;
	}
	public Object getValueOf() {
		return valueof;
	}

	
}
