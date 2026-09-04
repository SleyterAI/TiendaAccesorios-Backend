package com.ConsigueVentas.TiendaAccesorios.User.Dto.Register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @NotBlank(message = "Username no puede estar en blanco")
    @Size(min = 5, max = 30, message = "El username debe tener entre 5 y 30 caracteres")
    private String username;

    @NotBlank(message = "Email no puede estar en blanco")
    @Email(message = "El email no es válido")
    private String email;

    @NotBlank(message = "Password no puede estar en blanco")
    @Size(min = 8, max = 20, message = "Password debe tener entre 8 y 30 caracteres")
    private String password;

    //"ADMIN", "CLIENTE"
}
