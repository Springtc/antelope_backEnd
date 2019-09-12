package com.pronghorn.core.jsonResponse;

public class CommonResponse {

	public final static String SUCCESS = "success";
	public final static String FAILED = "failed";
	public final static String CODE_OK = "200";
	public final static String CODE_ERROR = "500";
	public final static String ERROR_SUB_CODE = "460";

	protected String status;
	protected String message;
	protected Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
