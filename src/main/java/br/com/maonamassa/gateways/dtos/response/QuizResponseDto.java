package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Quiz;

import java.util.UUID;

public record QuizResponseDto(
        UUID id,
        UUID cursoId,
        String pergunta,
        String respostaCorreta
) {
    public static QuizResponseDto fromQuiz(Quiz quiz) {
        return new QuizResponseDto(
                quiz.getId(),
                quiz.getCurso().getId(),
                quiz.getPergunta(),
                quiz.getRespostaCorreta()
        );
    }
}
