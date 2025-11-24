package br.com.maonamassa.gateways;

import br.com.maonamassa.gateways.dtos.request.QuizRequestDto;
import br.com.maonamassa.gateways.dtos.response.QuizResponseDto;
import br.com.maonamassa.services.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public Page<QuizResponseDto> listarTodos(Pageable pageable) {
        return quizService.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponseDto> buscarPorId(@PathVariable String id) {
        QuizResponseDto quiz = quizService.buscarPorId(UUID.fromString(id));
        return ResponseEntity.ok(quiz);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuizResponseDto criar(@Valid @RequestBody QuizRequestDto dto) {
        return quizService.criar(dto);
    }

    @PutMapping("/{id}")
    public QuizResponseDto atualizar(@PathVariable String id, @Valid @RequestBody QuizRequestDto dto) {
        return quizService.atualizar(UUID.fromString(id), dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        quizService.deletar(UUID.fromString(id));
    }
}

