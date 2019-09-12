package com.pronghorn.core.jsonResponse;

public class FailedResponse extends CommonResponse{
	
	public static FailedResponse create(String message){
		FailedResponse res = new FailedResponse();
		res.setStatus(CommonResponse.CODE_ERROR);
		res.setMessage(message);
		return res;
	}

	public static FailedResponse create(Object data, String message){
		FailedResponse res = new FailedResponse();
		res.setStatus(CommonResponse.CODE_ERROR);
		res.setMessage(message);
		res.setData(data);
		return res;
	}
}
