package br.com.maonamassa.gateways;

import br.com.maonamassa.gateways.dtos.request.LoginRequestDto;
import br.com.maonamassa.gateways.dtos.request.UsuarioRequestDto;
import br.com.maonamassa.gateways.dtos.response.AuthResponseDto;
import br.com.maonamassa.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        AuthResponseDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDto registrar(@Valid @RequestBody UsuarioRequestDto request) {
        return authService.registrar(request);
    }
}
