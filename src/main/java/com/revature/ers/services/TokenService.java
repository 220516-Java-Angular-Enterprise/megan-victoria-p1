package com.revature.ers.services;

import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.utils.JwtConfig;
import com.revature.ers.utils.annotations.Inject;

public class TokenService {
    @Inject
    private JwtConfig jwtConfig;

    public TokenService(){
    };

    @Inject
    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Principal subject) {
        return "";
    }

    public Principal extractRequesterDetails(String token) {
        return null;
    }
}
