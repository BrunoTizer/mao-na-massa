package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Aula;

import java.util.UUID;

public record AulaResponseDto(
        UUID id,
        UUID cursoId,
        String titulo,
        String conteudo,
        Integer ordem
) {
    public static AulaResponseDto fromAula(Aula aula) {
        return new AulaResponseDto(
                aula.getId(),
                aula.getCurso().getId(),
                aula.getTitulo(),
                aula.getConteudo(),
                aula.getOrdem()
        );
    }
}
