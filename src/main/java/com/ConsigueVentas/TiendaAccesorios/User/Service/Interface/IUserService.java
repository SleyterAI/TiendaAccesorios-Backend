package com.ConsigueVentas.TiendaAccesorios.User.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.Register.RegisterRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.UserResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.User;

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
