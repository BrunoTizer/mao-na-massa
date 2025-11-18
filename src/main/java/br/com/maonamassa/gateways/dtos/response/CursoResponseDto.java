package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Curso;

import java.time.LocalDate;
import java.util.UUID;

public record CursoResponseDto(
        UUID id,
        String titulo,
        String descricao,
        AreaResponseDto area,
        String nivel,
        LocalDate dataCriacao
) {
    public static CursoResponseDto fromCurso(Curso curso) {
        return new CursoResponseDto(
                curso.getId(),
                curso.getTitulo(),
                curso.getDescricao(),
                AreaResponseDto.fromArea(curso.getArea()),
                curso.getNivel(),
                curso.getDataCriacao()
        );
    }
}
