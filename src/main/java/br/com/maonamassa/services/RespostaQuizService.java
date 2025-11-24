package br.com.maonamassa.services;

import br.com.maonamassa.domains.Quiz;
import br.com.maonamassa.domains.RespostaQuiz;
import br.com.maonamassa.domains.Usuario;
import br.com.maonamassa.gateways.QuizRepository;
import br.com.maonamassa.gateways.RespostaQuizRepository;
import br.com.maonamassa.gateways.UsuarioRepository;
import br.com.maonamassa.gateways.dtos.request.RespostaQuizRequestDto;
import br.com.maonamassa.gateways.dtos.response.RespostaQuizResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RespostaQuizService {

    private final RespostaQuizRepository respostaQuizRepository;
    private final UsuarioRepository usuarioRepository;
    private final QuizRepository quizRepository;

    public Page<RespostaQuizResponseDto> listarTodos(Pageable pageable) {
        return respostaQuizRepository.findAll(pageable)
                .map(RespostaQuizResponseDto::fromRespostaQuiz);
    }

    public RespostaQuizResponseDto buscarPorId(UUID id) {
        RespostaQuiz respostaQuiz = respostaQuizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resposta de quiz não encontrada"));
        return RespostaQuizResponseDto.fromRespostaQuiz(respostaQuiz);
    }

    public RespostaQuizResponseDto criar(RespostaQuizRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Quiz quiz = quizRepository.findById(dto.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz não encontrado"));

        RespostaQuiz respostaQuiz = dto.toRespostaQuiz()
                .withUsuario(usuario)
                .withQuiz(quiz);

        RespostaQuiz salva = respostaQuizRepository.save(respostaQuiz);
        return RespostaQuizResponseDto.fromRespostaQuiz(salva);
    }

    public void deletar(UUID id) {
        if (!respostaQuizRepository.existsById(id)) {
            throw new RuntimeException("Resposta de quiz não encontrada");
        }
        respostaQuizRepository.deleteById(id);
    }
}
