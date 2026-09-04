package com.ConsigueVentas.TiendaAccesorios.User.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.MessageResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Register.RegisterRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.UserResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.User;

import java.util.List;

public interface IUserService {
    //Create
    MessageResponseDto createUser(RegisterRequestDto registerRequestDto);

    //Read
    List<UserResponseDto> getAllUser();
    User getUserById(Long id);

    //Update
    User updateUser(Long id, RegisterRequestDto registerRequestDto);

    //Delete
    MessageResponseDto deleteUser(Long id);
}
