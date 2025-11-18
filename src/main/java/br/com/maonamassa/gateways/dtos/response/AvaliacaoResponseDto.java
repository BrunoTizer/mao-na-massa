package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Avaliacao;

import java.time.LocalDate;
import java.util.UUID;

public record AvaliacaoResponseDto(
        UUID id,
        ServicoResponseDto servico,
        UsuarioResponseDto usuario,
        Integer nota,
        String comentario,
        LocalDate data
) {
    public static AvaliacaoResponseDto fromAvaliacao(Avaliacao avaliacao) {
        return new AvaliacaoResponseDto(
                avaliacao.getId(),
                ServicoResponseDto.fromServico(avaliacao.getServico()),
                UsuarioResponseDto.fromUsuario(avaliacao.getUsuario()),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getData()
        );
    }
}
