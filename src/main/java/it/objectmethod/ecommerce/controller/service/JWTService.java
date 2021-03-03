package it.objectmethod.ecommerce.controller.service;


import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import it.objectmethod.ecommerce.entity.Utente;

@Component
public class JWTService {

	private static final String MY_SECRET_JWT_KEY = "my-secret-jwt-key";
	
	public String createJWTToken(Utente utente) {
		
		Algorithm alg = Algorithm.HMAC256(MY_SECRET_JWT_KEY);
		String token = JWT.create().withClaim("idUtente", utente.getId()).withClaim("nomeUtente", utente.getNome())
				.withExpiresAt(new Date(System.currentTimeMillis() + (1440 * 60 * 1000))).sign(alg);
		
		return token;
	}
	
	public boolean checkJWTToken(String jwtToken) {
		
		boolean valido = false;
		Algorithm alg = Algorithm.HMAC256(MY_SECRET_JWT_KEY);
		try {
			JWTVerifier ver = JWT.require(alg).build();
			DecodedJWT decoded = ver.verify(jwtToken);
			Long idUtente = decoded.getClaim("idUtente").asLong();
			String NomeUtente = decoded.getClaim("nomeUtente").asString();
			System.out.println("Utente verificato! " + idUtente + " - " + NomeUtente);
			valido = true;
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		}
		return valido;
	}
	
	public Long getIdUtenteByToken(String jwtToken) {
		
		Algorithm alg = Algorithm.HMAC256(MY_SECRET_JWT_KEY);
		Long idUtente = null;
		try {
			JWTVerifier ver = JWT.require(alg).build();
			DecodedJWT decoded = ver.verify(jwtToken);
			idUtente = decoded.getClaim("idUtente").asLong();
			
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		}
		return idUtente;
	}
}
