package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Aula;

import java.util.UUID;

public record AulaResponseDto(
        UUID id,
        CursoResponseDto curso,
        String titulo,
        String conteudo,
        Integer ordem
) {
    public static AulaResponseDto fromAula(Aula aula) {
        return new AulaResponseDto(
                aula.getId(),
                CursoResponseDto.fromCurso(aula.getCurso()),
                aula.getTitulo(),
                aula.getConteudo(),
                aula.getOrdem()
        );
    }
}
