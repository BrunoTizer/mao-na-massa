package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Profissional;

import java.util.UUID;

public record ProfissionalResponseDto(
        UUID id,
        UsuarioResponseDto usuario,
        String descricao,
        Double avaliacaoMedia,
        Boolean disponivel
) {
    public static ProfissionalResponseDto fromProfissional(Profissional profissional) {
        return new ProfissionalResponseDto(
                profissional.getId(),
                UsuarioResponseDto.fromUsuario(profissional.getUsuario()),
                profissional.getDescricao(),
                profissional.getAvaliacaoMedia(),
                profissional.getDisponivel()
        );
    }
}
