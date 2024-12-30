package dev.naman.productservicettsmorningdeb24.commons;

import dev.naman.productservicettsmorningdeb24.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        //Make a call to validateToken API from userService to validate the token.

        UserDto userDto = restTemplate.getForObject(
                "http://localhost:9000/users/validate/" + token,
                UserDto.class
        );

        return userDto;
    }
}

