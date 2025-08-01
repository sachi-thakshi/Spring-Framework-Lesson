package lk.ijse.gdse.o_13_spring_security_with_jwt.controller;

import lk.ijse.gdse.o_13_spring_security_with_jwt.dto.ApiResponse;
import lk.ijse.gdse.o_13_spring_security_with_jwt.dto.AuthDTO;
import lk.ijse.gdse.o_13_spring_security_with_jwt.dto.RegisterDTO;
import lk.ijse.gdse.o_13_spring_security_with_jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse>registerUser(@RequestBody RegisterDTO registerDTO){
        return ResponseEntity.ok(new ApiResponse(
                200,
                "OK",
                authService.register(registerDTO)
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse>login(@RequestBody AuthDTO authDTO){
        return ResponseEntity.ok(new ApiResponse(
                200,
                "OK",
                authService.authenticate(authDTO)
        ));
    }
}
