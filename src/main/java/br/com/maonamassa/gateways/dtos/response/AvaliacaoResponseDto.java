package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Avaliacao;

import java.time.LocalDate;
import java.util.UUID;

public record AvaliacaoResponseDto(
        UUID id,
        UUID servicoId,
        UUID usuarioId,
        Integer nota,
        String comentario,
        LocalDate data
) {
    public static AvaliacaoResponseDto fromAvaliacao(Avaliacao avaliacao) {
        return new AvaliacaoResponseDto(
                avaliacao.getId(),
                avaliacao.getServico().getId(),
                avaliacao.getUsuario().getId(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getData()
        );
    }
}
