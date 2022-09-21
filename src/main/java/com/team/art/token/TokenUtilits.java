//package com.team.art.token;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//
//@Component
//public class TokenUtilits {
//	private final String CLAIMS_SUB="sub";
//	private final String CLAIMS_Created="created";
//
//	@Value("${auth.expiration}")
//	private Long TOKEN_VALIDATION=608400L; 
//	@Value("${auth.secret}")
//	private String SECRTE_KEY;
//	
//	public String generateToken(UserDetails customUser) {
//		 //claims
//		 //expiration
//		 // sginIn to token
//		 // compact
//		Map<String, Object>claims=new HashMap<String, Object>();
//		claims.put(CLAIMS_SUB, customUser.getUsername());
//		claims.put(CLAIMS_Created, new Date());
//		return Jwts
//				.builder()
//				.setClaims(claims)
//				.setExpiration(generateExpirationDate())
//				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRTE_KEY)
//				.compact();
//	}
//
//	private Date generateExpirationDate() {
//		return new Date(System.currentTimeMillis()+TOKEN_VALIDATION*1000);
//	}
//	
//	private Claims getClaims(String token) {
//		Claims claims;
//		try {
//			claims=Jwts.parser().setSigningKey(SECRTE_KEY)
//					.parseClaimsJwt(token)
//					.getBody();
//			
//		} catch (Exception e) {
//			claims=null;
//		}
//		return claims;
//	}
//	
//	public String getUserNameFromToken(String token) {
//		try {
//			Claims claims=getClaims(token);
//			return claims.getSubject();
//		} catch (Exception e) {
//		return null;
//		}
//	}
//	private boolean isTokenExpire(String token) {
//		Date expiration=getClaims(token).getExpiration();
//		return expiration.before(new Date());
//	}
//	
//	public boolean isTokenValid(String token,UserDetails userDetails) {
//		String username=getUserNameFromToken(token);
//		return (username.equals(userDetails.getUsername())&&!isTokenExpire(token));
//	}
//
//
//}
