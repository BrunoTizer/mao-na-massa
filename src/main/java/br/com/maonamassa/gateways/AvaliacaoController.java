package br.com.maonamassa.gateways;

import br.com.maonamassa.gateways.dtos.request.AvaliacaoRequestDto;
import br.com.maonamassa.gateways.dtos.response.AvaliacaoResponseDto;
import br.com.maonamassa.services.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @GetMapping
    public Page<AvaliacaoResponseDto> listarTodos(Pageable pageable) {
        return avaliacaoService.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDto> buscarPorId(@PathVariable String id) {
        AvaliacaoResponseDto avaliacao = avaliacaoService.buscarPorId(UUID.fromString(id));
        return ResponseEntity.ok(avaliacao);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoResponseDto criar(@Valid @RequestBody AvaliacaoRequestDto dto) {
        return avaliacaoService.criar(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        avaliacaoService.deletar(UUID.fromString(id));
    }
}

