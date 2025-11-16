package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.RespostaQuiz;

import java.time.LocalDate;
import java.util.UUID;

public record RespostaQuizResponseDto(
        UUID id,
        UUID usuarioId,
        UUID quizId,
        String resposta,
        Boolean correta,
        LocalDate dataResposta
) {
    public static RespostaQuizResponseDto fromRespostaQuiz(RespostaQuiz respostaQuiz) {
        return new RespostaQuizResponseDto(
                respostaQuiz.getId(),
                respostaQuiz.getUsuario().getId(),
                respostaQuiz.getQuiz().getId(),
                respostaQuiz.getResposta(),
                respostaQuiz.getCorreta(),
                respostaQuiz.getDataResposta()
        );
    }
}
