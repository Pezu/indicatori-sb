package com.medivac.indicatori.service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.medivac.indicatori.domain.Token;
import com.medivac.indicatori.domain.User;
import com.medivac.indicatori.pojo.LoginRequest;
import com.medivac.indicatori.pojo.LoginResponse;
import com.medivac.indicatori.repositories.RepositoryRegistry;

@Service
public class LoginService {
	
	private final int EXPIRE_TIME_IN_MINUTES = 180;
	
    private final RepositoryRegistry repositoryRegistry;
    
    public LoginService(final RepositoryRegistry repositoryRegistry) {
		this.repositoryRegistry = repositoryRegistry;
	}

	public LoginResponse login(LoginRequest request) {
		User user = repositoryRegistry.getUsersRepository().findOneByUsernameAndPassword(request.getUsername(), request.getPassword());
		if (user == null) return null;
		
		String uid = UUID.randomUUID().toString();
		Token token = new Token(null, uid);
		token.setUpdater(user.getId());
		repositoryRegistry.getTokensRepository().save(token);

		LoginResponse response = new LoginResponse(uid, user.getName(), user.getRole(), user.getRights());
		return response;
	}
	
	public Boolean verifyToken(String value) {
		
		if (value == null) return false;
		
		Token token = repositoryRegistry.getTokensRepository().findOneByToken(value);
		if (token == null) return false;
		
		long diffInMillies = (new Date()).getTime() - token.getUpdatedAt().getTime();
	    long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    if (diff > EXPIRE_TIME_IN_MINUTES) return false;
	    
	    token.setUpdater(token.getUpdatedBy());
	    repositoryRegistry.getTokensRepository().save(token);
		return true;
		
	}
    
}
