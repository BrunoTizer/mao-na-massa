package br.com.maonamassa.gateways.dtos.request;

import br.com.maonamassa.domains.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CursoRequestDto {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "ID da área é obrigatório")
    private UUID areaId;

    @NotBlank(message = "Nível é obrigatório")
    private String nivel;

    public Curso toCurso() {
        return Curso.builder()
                .titulo(this.titulo)
                .descricao(this.descricao)
                .nivel(this.nivel)
                .dataCriacao(LocalDate.now())
                .build();
    }
}
