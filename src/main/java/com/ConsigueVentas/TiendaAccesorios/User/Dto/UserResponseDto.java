package com.ConsigueVentas.TiendaAccesorios.User.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

    private Long id;
    private String username;

    private String email;

    private String role;

    private String message;

}
