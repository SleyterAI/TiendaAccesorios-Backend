package com.ConsigueVentas.TiendaAccesorios.Dto.User;

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
