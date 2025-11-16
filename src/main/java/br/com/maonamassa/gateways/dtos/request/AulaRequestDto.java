package br.com.maonamassa.gateways.dtos.request;

import br.com.maonamassa.domains.Aula;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AulaRequestDto {

    @NotNull(message = "ID do curso é obrigatório")
    private UUID cursoId;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Conteúdo é obrigatório")
    private String conteudo;

    @NotNull(message = "Ordem é obrigatória")
    private Integer ordem;

    public Aula toAula() {
        return Aula.builder()
                .titulo(this.titulo)
                .conteudo(this.conteudo)
                .ordem(this.ordem)
                .build();
    }
}
