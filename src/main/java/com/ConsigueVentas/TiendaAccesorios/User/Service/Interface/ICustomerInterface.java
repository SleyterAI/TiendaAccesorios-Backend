package com.ConsigueVentas.TiendaAccesorios.User.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer.CustomerRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer.CustomerResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer.CustomerUpdateRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.Customer;

public interface ICustomerInterface {

    //create
    Customer createCustomer(String userEmail, CustomerRequestDto customerRequestDto);

    //read
    CustomerResponseDto getCustomerByUserEmail(String userEmail);

    //update
    public CustomerResponseDto updateCustomer(String userEmail, CustomerUpdateRequestDto request);
}
