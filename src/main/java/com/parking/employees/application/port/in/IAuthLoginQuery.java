package com.parking.employees.application.port.in;

import com.parking.employees.domain.AuthLogin;

public interface IAuthLoginQuery {

	String execute(AuthLogin authlogin);
	
}
