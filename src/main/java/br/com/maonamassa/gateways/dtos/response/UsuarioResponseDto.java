package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Usuario;

import java.time.LocalDate;
import java.util.UUID;

public record UsuarioResponseDto(
        UUID id,
        String nome,
        String email,
        String cidade,
        String areaInteresse,
        String tipoUsuario,
        LocalDate dataCriacao
) {
    public static UsuarioResponseDto fromUsuario(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCidade(),
                usuario.getAreaInteresse(),
                usuario.getTipoUsuario(),
                usuario.getDataCriacao()
        );
    }
}
