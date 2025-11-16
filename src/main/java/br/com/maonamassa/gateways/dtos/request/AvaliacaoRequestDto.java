package br.com.maonamassa.gateways.dtos.request;

import br.com.maonamassa.domains.Avaliacao;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class AvaliacaoRequestDto {

    @NotNull(message = "ID do serviço é obrigatório")
    private UUID servicoId;

    @NotNull(message = "ID do usuário é obrigatório")
    private UUID usuarioId;

    @NotNull(message = "Nota é obrigatória")
    @Min(value = 1, message = "Nota mínima é 1")
    @Max(value = 5, message = "Nota máxima é 5")
    private Integer nota;

    private String comentario;

    public Avaliacao toAvaliacao() {
        return Avaliacao.builder()
                .nota(this.nota)
                .comentario(this.comentario)
                .data(LocalDate.now())
                .build();
    }
}
