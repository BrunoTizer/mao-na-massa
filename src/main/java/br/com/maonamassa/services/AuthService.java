package br.com.maonamassa.services;

import br.com.maonamassa.domains.Usuario;
import br.com.maonamassa.gateways.UsuarioRepository;
import br.com.maonamassa.gateways.dtos.request.LoginRequestDto;
import br.com.maonamassa.gateways.dtos.request.UsuarioRequestDto;
import br.com.maonamassa.gateways.dtos.response.AuthResponseDto;
import br.com.maonamassa.gateways.dtos.response.UsuarioResponseDto;
import br.com.maonamassa.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);

        return AuthResponseDto.builder()
                .token(token)
                .build();
    }

    public AuthResponseDto registrar(UsuarioRequestDto request) {
        usuarioService.criar(request);

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);

        return AuthResponseDto.builder()
                .token(token)
                .build();
    }
}
