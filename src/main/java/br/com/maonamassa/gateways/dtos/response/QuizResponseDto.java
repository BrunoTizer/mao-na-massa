package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.Quiz;

import java.util.UUID;

public record QuizResponseDto(
        UUID id,
        CursoResponseDto curso,
        String pergunta,
        String respostaCorreta
) {
    public static QuizResponseDto fromQuiz(Quiz quiz) {
        return new QuizResponseDto(
                quiz.getId(),
                CursoResponseDto.fromCurso(quiz.getCurso()),
                quiz.getPergunta(),
                quiz.getRespostaCorreta()
        );
    }
}
