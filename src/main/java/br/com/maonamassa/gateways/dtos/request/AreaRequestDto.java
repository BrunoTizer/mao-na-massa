package br.com.maonamassa.gateways.dtos.request;

import br.com.maonamassa.domains.Area;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AreaRequestDto {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    public Area toArea() {
        return Area.builder()
                .nome(this.nome)
                .build();
    }
}
