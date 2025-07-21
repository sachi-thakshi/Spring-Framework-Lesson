package lk.ijse.gdse.o_13_spring_security_with_jwt.service;

import lk.ijse.gdse.o_13_spring_security_with_jwt.dto.AuthDTO;
import lk.ijse.gdse.o_13_spring_security_with_jwt.dto.AuthResponseDTO;
import lk.ijse.gdse.o_13_spring_security_with_jwt.dto.RegisterDTO;
import lk.ijse.gdse.o_13_spring_security_with_jwt.entity.Role;
import lk.ijse.gdse.o_13_spring_security_with_jwt.entity.User;
import lk.ijse.gdse.o_13_spring_security_with_jwt.repository.UserRepository;
import lk.ijse.gdse.o_13_spring_security_with_jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO authenticate(AuthDTO authDTO){
        User user = userRepository.findByUsername(authDTO.getUsername())
                            .orElseThrow(()->new UsernameNotFoundException(" Username Not Found"));

        if (!passwordEncoder.matches(authDTO.getPassword(),user.getPassword())){
            throw new BadCredentialsException("Incorrect Password");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponseDTO(token);
    }

    public String register(RegisterDTO registerDTO){
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()){
            throw new RuntimeException("Username Already Exists");
        }

        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.valueOf(registerDTO.getRole()))
                .build();

        userRepository.save(user);

        return "User Registration Success";
    }
}
