package br.com.maonamassa.gateways;

import br.com.maonamassa.gateways.dtos.request.RespostaQuizRequestDto;
import br.com.maonamassa.gateways.dtos.response.RespostaQuizResponseDto;
import br.com.maonamassa.services.RespostaQuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/respostas-quiz")
@RequiredArgsConstructor
public class RespostaQuizController {

    private final RespostaQuizService respostaQuizService;

    @GetMapping
    public Page<RespostaQuizResponseDto> listarTodos(Pageable pageable) {
        return respostaQuizService.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaQuizResponseDto> buscarPorId(@PathVariable String id) {
        RespostaQuizResponseDto respostaQuiz = respostaQuizService.buscarPorId(UUID.fromString(id));
        return ResponseEntity.ok(respostaQuiz);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespostaQuizResponseDto criar(@Valid @RequestBody RespostaQuizRequestDto dto) {
        return respostaQuizService.criar(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        respostaQuizService.deletar(UUID.fromString(id));
    }
}

