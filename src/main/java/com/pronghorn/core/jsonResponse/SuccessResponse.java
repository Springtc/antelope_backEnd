package com.pronghorn.core.jsonResponse;

public class SuccessResponse extends CommonResponse{

	public static SuccessResponse create(String message){
		return SuccessResponse.create(null, message);
	}

	public static SuccessResponse create(Object data, String message){
		SuccessResponse res = new SuccessResponse();
		res.setStatus(CommonResponse.CODE_OK);
		res.setMessage(message);
		res.setData(data);
		return res;
	}

}
