package com.jsf.service;

import com.jsf.model.UserDtls;

public interface UserService {
	public UserDtls createUser(UserDtls user);
	public boolean checkEmail(String email);
}
