package org.dev.ecommerce.productcatalogue.model;

public class CustomMessage {
	
	public enum MessageType {
	  SUCCESS, INFO, WARNING, ERROR
	}
	
	private String message;
	private MessageType type;

	public CustomMessage() {
	    super();
	  }

	public CustomMessage(MessageType type, String message) {
	    super();
	    this.message = message;
	    this.type = type;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
	
}
