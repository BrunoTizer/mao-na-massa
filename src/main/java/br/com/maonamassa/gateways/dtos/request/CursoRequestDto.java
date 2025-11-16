package br.com.maonamassa.gateways.dtos.request;

import br.com.maonamassa.domains.Curso;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CursoRequestDto {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "Área é obrigatória")
    private String area;

    @NotBlank(message = "Nível é obrigatório")
    private String nivel;

    public Curso toCurso() {
        return Curso.builder()
                .titulo(this.titulo)
                .descricao(this.descricao)
                .area(this.area)
                .nivel(this.nivel)
                .dataCriacao(LocalDate.now())
                .build();
    }
}
