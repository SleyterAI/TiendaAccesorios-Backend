package com.ConsigueVentas.TiendaAccesorios.User.Service;

import com.ConsigueVentas.TiendaAccesorios.Security.Jwt.JwtService;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Login.LoginRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Login.LoginResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Service.Interface.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                ));
        String token = jwtService.generarToken(authentication);
        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String roleUser = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        return new LoginResponseDto(token,userDetails.getUsername(), roleUser);
    }
}
