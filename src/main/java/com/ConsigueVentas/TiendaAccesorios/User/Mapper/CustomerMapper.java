package com.ConsigueVentas.TiendaAccesorios.User.Mapper;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.Card.CardResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer.CustomerResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.Card;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDto toResponseDto(Customer customer);

    CardResponseDto toResponseDto(Card card);
}
