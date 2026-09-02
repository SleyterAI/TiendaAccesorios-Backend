package com.ConsigueVentas.TiendaAccesorios.Dto.User.Login;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class LoginResponseDto {
    private String token;
    private String email;
    private String role;
}
