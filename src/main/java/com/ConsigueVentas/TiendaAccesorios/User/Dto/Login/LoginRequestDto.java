package com.ConsigueVentas.TiendaAccesorios.User.Dto.Login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    private String email;

    @NotBlank
    @Size(min = 8, max = 20, message = "La contraseña debe tener minimo 8 caracteres")
    private String password;
}
