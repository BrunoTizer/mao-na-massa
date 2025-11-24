package br.com.maonamassa.gateways;

import br.com.maonamassa.gateways.dtos.request.AulaRequestDto;
import br.com.maonamassa.gateways.dtos.response.AulaResponseDto;
import br.com.maonamassa.services.AulaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService aulaService;

    @GetMapping
    public Page<AulaResponseDto> listarTodos(Pageable pageable) {
        return aulaService.listarTodos(pageable);
    }

    @GetMapping("/curso/{cursoId}")
    public Page<AulaResponseDto> listarPorCurso(@PathVariable String cursoId, Pageable pageable) {
        return aulaService.listarPorCurso(UUID.fromString(cursoId), pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaResponseDto> buscarPorId(@PathVariable String id) {
        AulaResponseDto aula = aulaService.buscarPorId(UUID.fromString(id));
        return ResponseEntity.ok(aula);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AulaResponseDto criar(@Valid @RequestBody AulaRequestDto dto) {
        return aulaService.criar(dto);
    }

    @PutMapping("/{id}")
    public AulaResponseDto atualizar(@PathVariable String id, @Valid @RequestBody AulaRequestDto dto) {
        return aulaService.atualizar(UUID.fromString(id), dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        aulaService.deletar(UUID.fromString(id));
    }
}

