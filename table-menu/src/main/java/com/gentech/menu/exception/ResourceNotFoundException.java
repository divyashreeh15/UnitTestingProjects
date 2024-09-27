package com.gentech.menu.exception;

public class ResourceNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private Object objectValue;
	public ResourceNotFoundException(String resourceName, String fieldName, Object objectValue) {
		super(String.format("Resource named:'%s' with '%s' does not have '%s' ",resourceName, fieldName,objectValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.objectValue = objectValue;
	}
	public String getResourceName() {
		return resourceName;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public Object getObjectValue() {
		return objectValue;
	}
	
	
	
	

}
