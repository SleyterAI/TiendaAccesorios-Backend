package com.ConsigueVentas.TiendaAccesorios.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.Dto.User.Register.RegisterRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.User.UserResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.User;

import java.util.List;

public interface IUserService {
    //Create
    UserResponseDto createUsuario(RegisterRequestDto registerRequestDto);

    //Read
    List<UserResponseDto> getAllUsuario();
    User getUsuarioById(Long id);

    //Update
    User updateUsuario(Long id, RegisterRequestDto registerRequestDto);

    //Delete
    UserResponseDto deleteUsuario(Long id);
}
