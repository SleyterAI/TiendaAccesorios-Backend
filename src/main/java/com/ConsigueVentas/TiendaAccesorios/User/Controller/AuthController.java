package com.ConsigueVentas.TiendaAccesorios.User.Controller;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.Login.LoginRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Login.LoginResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
}
