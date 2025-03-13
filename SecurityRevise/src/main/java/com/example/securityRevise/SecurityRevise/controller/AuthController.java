package com.example.securityRevise.SecurityRevise.controller;

import com.example.securityRevise.SecurityRevise.entity.RefreshToken;
import com.example.securityRevise.SecurityRevise.model.UserInfoDto;
import com.example.securityRevise.SecurityRevise.response.JwtResponseDTO;
import com.example.securityRevise.SecurityRevise.service.JwtService;
import com.example.securityRevise.SecurityRevise.service.RefreshTokenService;
import com.example.securityRevise.SecurityRevise.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("auth/v1/signup")
    public ResponseEntity SignUp(@RequestBody UserInfoDto userInfoDto){
        try{
            Boolean isSignedUp = userDetailsService.signupUser(userInfoDto);
            if(Boolean.FALSE.equals(isSignedUp)){
                return new ResponseEntity<>("Already exist", HttpStatus.BAD_REQUEST);
            }

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.GenerateToken(userInfoDto.getUsername());
            return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(jwtToken).
                    token(refreshToken.getToken()).build(), HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>("Exception in UserService", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
