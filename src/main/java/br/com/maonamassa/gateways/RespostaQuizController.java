package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Quiz;
import br.com.maonamassa.domains.RespostaQuiz;
import br.com.maonamassa.domains.Usuario;
import br.com.maonamassa.gateways.dtos.request.RespostaQuizRequestDto;
import br.com.maonamassa.gateways.dtos.response.RespostaQuizResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/respostas-quiz")
@RequiredArgsConstructor
public class RespostaQuizController {

    private final RespostaQuizRepository respostaQuizRepository;
    private final UsuarioRepository usuarioRepository;
    private final QuizRepository quizRepository;

    @GetMapping
    public List<RespostaQuizResponseDto> listarTodos() {
        return respostaQuizRepository.findAll()
                .stream()
                .map(RespostaQuizResponseDto::fromRespostaQuiz)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaQuizResponseDto> buscarPorId(@PathVariable String id) {
        RespostaQuiz respostaQuiz = respostaQuizRepository.findById(UUID.fromString(id)).get();
        return ResponseEntity.ok(RespostaQuizResponseDto.fromRespostaQuiz(respostaQuiz));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespostaQuizResponseDto criar(@Valid @RequestBody RespostaQuizRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).get();
        Quiz quiz = quizRepository.findById(dto.getQuizId()).get();
        RespostaQuiz respostaQuiz = dto.toRespostaQuiz()
                .withUsuario(usuario)
                .withQuiz(quiz);
        RespostaQuiz salva = respostaQuizRepository.save(respostaQuiz);
        return RespostaQuizResponseDto.fromRespostaQuiz(salva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        respostaQuizRepository.deleteById(UUID.fromString(id));
    }
}
