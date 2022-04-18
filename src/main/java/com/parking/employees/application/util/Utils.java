package com.parking.employees.application.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.employees.adapter.dto.ExceptionResponse;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

	private static final String CHARACTER_ENCODING = "utf-8";
	public static final String MEDIA_TYPE="application/json";


	public  String convertObjectToJson(Object object) throws JsonProcessingException {
	    if (object == null) {
	        return null;
	    }
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.writeValueAsString(object);
	}
	
	public boolean matcher(String[] whiteList,String url) {
		List<String> lista = Arrays.asList(whiteList);
		boolean match=false;
		int i=0;
		
		while(!match && i<lista.size() ) {			
			
			 if(url.contains(lista.get(i))) {
				 match=true;
			 }
			 else {
				 i++;
			 }
		}
		
	
		return match;
	}
	
	public void handleException(HttpServletRequest request,HttpServletResponse response,String message,int httpStatus) throws IOException  {
		  response.resetBuffer();
		ExceptionResponse errorResponse = new ExceptionResponse();
		errorResponse.setData(message);
		errorResponse.setRequestUrl(request.getRequestURI());			
		errorResponse.setTimestamp(Time.getTime());
		
		response.setCharacterEncoding(CHARACTER_ENCODING);
		response.setStatus(httpStatus);
		response.setContentType(MEDIA_TYPE);
		response.getWriter().write(Utils.convertObjectToJson(errorResponse));
		response.getWriter().flush();

		return;
	}
	
}
