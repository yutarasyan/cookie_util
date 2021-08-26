package ru.mos.mesh.cookie_util.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mos.mesh.cookie_util.dto.CookieRequestDto;

@RestController
@RequestMapping(path = "/cookie_util")
public class СookieController {

  @CrossOrigin
  @PostMapping(
      path = "/{startId}",
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity getSecureCookie(
      @PathVariable(name = "startId") String startId,
      @RequestBody CookieRequestDto cookieRequestDto
  ) {
    Map<String, Object> jwtData = cookieRequestDto.getMap();
    Key key = new SecretKeySpec(cookieRequestDto.getSecret().getBytes(), SignatureAlgorithm.HS512.getJcaName());
    String token = Jwts.builder().setClaims(jwtData).signWith(key).compact();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, String.format("__Secure-%s=%s; Max-Age=60; Path=/; Domain=mesh-test.hostco.ru; Secure; HttpOnly; SameSite=None", startId, token)).build();
  }
}
