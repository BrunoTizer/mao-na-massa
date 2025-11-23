package br.com.maonamassa.gateways;

import br.com.maonamassa.gateways.dtos.request.CursoRequestDto;
import br.com.maonamassa.gateways.dtos.response.CursoResponseDto;
import br.com.maonamassa.services.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public Page<CursoResponseDto> listarTodos(
            @RequestParam(required = false) UUID areaId,
            Pageable pageable
    ) {
        return cursoService.listarTodos(areaId, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDto> buscarPorId(@PathVariable String id) {
        CursoResponseDto curso = cursoService.buscarPorId(UUID.fromString(id));
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoResponseDto criar(@Valid @RequestBody CursoRequestDto dto) {
        return cursoService.criar(dto);
    }

    @PutMapping("/{id}")
    public CursoResponseDto atualizar(@PathVariable String id, @Valid @RequestBody CursoRequestDto dto) {
        return cursoService.atualizar(UUID.fromString(id), dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        cursoService.deletar(UUID.fromString(id));
    }
}
