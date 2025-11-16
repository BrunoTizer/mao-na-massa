package br.com.maonamassa.gateways.dtos.request;

import br.com.maonamassa.domains.Profissional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ProfissionalRequestDto {

    @NotNull(message = "ID do usuário é obrigatório")
    private UUID usuarioId;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    private Double avaliacaoMedia = 0.0;

    private Boolean disponivel = true;

    public Profissional toProfissional() {
        return Profissional.builder()
                .descricao(this.descricao)
                .avaliacaoMedia(this.avaliacaoMedia)
                .disponivel(this.disponivel)
                .build();
    }
}
