package br.com.maonamassa.gateways.dtos.request;

import br.com.maonamassa.domains.RespostaQuiz;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class RespostaQuizRequestDto {

    @NotNull(message = "ID do usuário é obrigatório")
    private UUID usuarioId;

    @NotNull(message = "ID do quiz é obrigatório")
    private UUID quizId;

    @NotBlank(message = "Resposta é obrigatória")
    private String resposta;

    @NotNull(message = "Campo correta é obrigatório")
    private Boolean correta;

    public RespostaQuiz toRespostaQuiz() {
        return RespostaQuiz.builder()
                .resposta(this.resposta)
                .correta(this.correta)
                .dataResposta(LocalDate.now())
                .build();
    }
}
