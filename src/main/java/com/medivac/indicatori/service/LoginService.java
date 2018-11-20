package com.medivac.indicatori.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.medivac.indicatori.domain.Token;
import com.medivac.indicatori.domain.User;
import com.medivac.indicatori.pojo.LoginRequest;
import com.medivac.indicatori.pojo.LoginResponse;
import com.medivac.indicatori.repositories.RepositoryRegistry;

@Service
public class LoginService {
	
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

    
}
