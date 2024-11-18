package com.zdevs.restaurant_reservation_api.controller;


import com.zdevs.restaurant_reservation_api.dto.request.AuthRequestDTO;
import com.zdevs.restaurant_reservation_api.dto.request.SignupRequestDTO;
import com.zdevs.restaurant_reservation_api.dto.response.AuthResponseDTO;
import com.zdevs.restaurant_reservation_api.dto.response.UserProfileResponseDTO;
import com.zdevs.restaurant_reservation_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody AuthRequestDTO authRequest) {
        AuthResponseDTO authResponse = userService.signIn(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserProfileResponseDTO> register(@RequestBody @Validated SignupRequestDTO signupRequestDTO) {
        UserProfileResponseDTO userProfileResponseDTO = userService.signup(signupRequestDTO);
        return new ResponseEntity<>(userProfileResponseDTO, HttpStatus.CREATED);
    }

}
