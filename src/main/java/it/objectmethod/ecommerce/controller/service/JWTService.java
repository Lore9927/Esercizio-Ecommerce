package it.objectmethod.ecommerce.controller.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.service.dto.UtenteDTO;
import it.objectmethod.ecommerce.service.mapper.UtenteMapper;

@Component
public class JWTService {

	@Autowired
	UtenteMapper utenteMapper;
	
	private static final String MY_SECRET_JWT_KEY = "my-secret-jwt-key";
	
	public String createJWTToken(UtenteDTO u) {
		Utente utente = utenteMapper.toEntity(u);
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
	
	public UtenteDTO getIdUtenteByToken(String jwtToken) {
		
		Algorithm alg = Algorithm.HMAC256(MY_SECRET_JWT_KEY);
		Long idUtente = null;
		String nomeUtente;
		UtenteDTO utente = null;
		try {
			JWTVerifier ver = JWT.require(alg).build();
			DecodedJWT decoded = ver.verify(jwtToken);
			idUtente = decoded.getClaim("idUtente").asLong();
			nomeUtente = decoded.getClaim("nomeUtente").asString();
			utente = new UtenteDTO();
			utente.setId(idUtente);
			utente.setNome(nomeUtente);
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		}
		return utente;
	}
}
