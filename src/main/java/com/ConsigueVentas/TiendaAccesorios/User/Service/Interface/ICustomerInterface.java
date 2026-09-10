package com.ConsigueVentas.TiendaAccesorios.User.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer.CustomerRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer.CustomerResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.Customer;

public interface ICustomerInterface {
    CustomerResponseDto getCustomerByUserEmail(String userEmail);
    Customer createCustomer(CustomerRequestDto customerRequestDto);
}
