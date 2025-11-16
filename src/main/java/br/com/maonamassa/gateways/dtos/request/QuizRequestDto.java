package br.com.maonamassa.gateways.dtos.request;

import br.com.maonamassa.domains.Quiz;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class QuizRequestDto {

    @NotNull(message = "ID do curso é obrigatório")
    private UUID cursoId;

    @NotBlank(message = "Pergunta é obrigatória")
    private String pergunta;

    @NotBlank(message = "Resposta correta é obrigatória")
    private String respostaCorreta;

    public Quiz toQuiz() {
        return Quiz.builder()
                .pergunta(this.pergunta)
                .respostaCorreta(this.respostaCorreta)
                .build();
    }
}
