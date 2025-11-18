package br.com.maonamassa.gateways.dtos.response;

import br.com.maonamassa.domains.RespostaQuiz;

import java.time.LocalDate;
import java.util.UUID;

public record RespostaQuizResponseDto(
        UUID id,
        UsuarioResponseDto usuario,
        QuizResponseDto quiz,
        String resposta,
        Boolean correta,
        LocalDate dataResposta
) {
    public static RespostaQuizResponseDto fromRespostaQuiz(RespostaQuiz respostaQuiz) {
        return new RespostaQuizResponseDto(
                respostaQuiz.getId(),
                UsuarioResponseDto.fromUsuario(respostaQuiz.getUsuario()),
                QuizResponseDto.fromQuiz(respostaQuiz.getQuiz()),
                respostaQuiz.getResposta(),
                respostaQuiz.getCorreta(),
                respostaQuiz.getDataResposta()
        );
    }
}
