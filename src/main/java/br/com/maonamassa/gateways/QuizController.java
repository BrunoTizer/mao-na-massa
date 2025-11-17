package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Curso;
import br.com.maonamassa.domains.Quiz;
import br.com.maonamassa.gateways.dtos.request.QuizRequestDto;
import br.com.maonamassa.gateways.dtos.response.QuizResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizRepository quizRepository;
    private final CursoRepository cursoRepository;

    @GetMapping
    public List<QuizResponseDto> listarTodos() {
        return quizRepository.findAll()
                .stream()
                .map(QuizResponseDto::fromQuiz)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponseDto> buscarPorId(@PathVariable String id) {
        Quiz quiz = quizRepository.findById(UUID.fromString(id)).get();
        return ResponseEntity.ok(QuizResponseDto.fromQuiz(quiz));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuizResponseDto criar(@Valid @RequestBody QuizRequestDto dto) {
        Curso curso = cursoRepository.findById(dto.getCursoId()).get();
        Quiz quiz = dto.toQuiz().withCurso(curso);
        Quiz salvo = quizRepository.save(quiz);
        return QuizResponseDto.fromQuiz(salvo);
    }

    @PutMapping("/{id}")
    public QuizResponseDto atualizar(@PathVariable String id, @Valid @RequestBody QuizRequestDto dto) {
        Quiz quiz = quizRepository.findById(UUID.fromString(id)).get();
        Curso curso = cursoRepository.findById(dto.getCursoId()).get();
        Quiz atualizado = quiz
                .withCurso(curso)
                .withPergunta(dto.getPergunta())
                .withRespostaCorreta(dto.getRespostaCorreta());
        Quiz salvo = quizRepository.save(atualizado);
        return QuizResponseDto.fromQuiz(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        quizRepository.deleteById(UUID.fromString(id));
    }
}
