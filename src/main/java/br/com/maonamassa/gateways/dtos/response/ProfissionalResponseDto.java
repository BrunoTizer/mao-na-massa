package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Profissional;

import java.util.UUID;

public record ProfissionalResponseDto(
        UUID id,
        UUID usuarioId,
        String descricao,
        Double avaliacaoMedia,
        Boolean disponivel
) {
    public static ProfissionalResponseDto fromProfissional(Profissional profissional) {
        return new ProfissionalResponseDto(
                profissional.getId(),
                profissional.getUsuario().getId(),
                profissional.getDescricao(),
                profissional.getAvaliacaoMedia(),
                profissional.getDisponivel()
        );
    }
}
