package com.ConsigueVentas.TiendaAccesorios.User.Service;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.Card.CardRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Card.CardResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer.CustomerRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Customer.CustomerResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.Card;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.Customer;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.User;
import com.ConsigueVentas.TiendaAccesorios.User.Repository.CustomerRepository;
import com.ConsigueVentas.TiendaAccesorios.User.Repository.UserRepository;
import com.ConsigueVentas.TiendaAccesorios.User.Service.Interface.ICustomerInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService implements ICustomerInterface {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Override
    public CustomerResponseDto getCustomerByUserEmail(String userEmail) {
        Customer customer = customerRepository.findByUserEmail(userEmail)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
        CardResponseDto cardResponseDto = CardResponseDto.builder()
                .cardNumber(customer.getCard().getCardNumber())
                .expirationDate(customer.getCard().getExpirationDate())
                .build();
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .card(cardResponseDto)
                .build();

    }

    @Override
    public Customer createCustomer(CustomerRequestDto customerRequestDto) {
        User user = userRepository.findById(customerRequestDto.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        Card card = Card.builder()
                .cardNumber(customerRequestDto.getCard().getCardNumber())
                .expirationDate(customerRequestDto.getCard().getExpirationDate())
                .build();
        Customer customer = Customer.builder()
                .name(customerRequestDto.getName())
                .lastName(customerRequestDto.getLastName())
                .phoneNumber(customerRequestDto.getPhoneNumber())
                .address(customerRequestDto.getAddress())
                .card(card)
                .user(user)
                .build();
        card.setCustomer(customer);
        return customerRepository.save(customer);
    }
}
