package com.ConsigueVentas.TiendaAccesorios.User.Service;

import com.ConsigueVentas.TiendaAccesorios.GlobalException.DuplicateResourceException;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Register.RegisterRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.RolNMessageResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.UserResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.MessageResponseDto;
import com.ConsigueVentas.TiendaAccesorios.User.Entity.User;
import com.ConsigueVentas.TiendaAccesorios.User.Repository.UserRepository;
import com.ConsigueVentas.TiendaAccesorios.User.Service.Interface.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MessageResponseDto createUser(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (userRepository.existsByUsername(registerRequestDto.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(
                passwordEncoder.encode(registerRequestDto.getPassword())
        );
        user.setRole("USER");

        userRepository.save(user);

        return new MessageResponseDto("User created correctly");
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole()
                ))
                .toList();
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User updateUser(Long id, RegisterRequestDto registerRequestDto) {
        return null;
    }

    @Override
    public MessageResponseDto deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User doesnt exist");
        }
        userRepository.deleteById(id);
        return new MessageResponseDto("User deleted correctly");
    }

    public RolNMessageResponseDto updateRole(Long id, String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User doesnt exist"));

        user.setRole(role);
        User promotedUser = userRepository.save(user);
        return new RolNMessageResponseDto("User promoted correctly: ", promotedUser.getRole());
    }
}
