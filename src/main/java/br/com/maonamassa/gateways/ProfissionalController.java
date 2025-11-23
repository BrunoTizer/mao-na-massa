package br.com.maonamassa.gateways;

import br.com.maonamassa.gateways.dtos.request.ProfissionalRequestDto;
import br.com.maonamassa.gateways.dtos.response.ProfissionalResponseDto;
import br.com.maonamassa.services.ProfissionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profissionais")
@RequiredArgsConstructor
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    @GetMapping
    public Page<ProfissionalResponseDto> listarTodos(
            @RequestParam(required = false) Boolean disponivel,
            Pageable pageable
    ) {
        return profissionalService.listarTodos(disponivel, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalResponseDto> buscarPorId(@PathVariable String id) {
        ProfissionalResponseDto profissional = profissionalService.buscarPorId(UUID.fromString(id));
        return ResponseEntity.ok(profissional);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalResponseDto criar(@Valid @RequestBody ProfissionalRequestDto dto) {
        return profissionalService.criar(dto);
    }

    @PutMapping("/{id}")
    public ProfissionalResponseDto atualizar(@PathVariable String id, @Valid @RequestBody ProfissionalRequestDto dto) {
        return profissionalService.atualizar(UUID.fromString(id), dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        profissionalService.deletar(UUID.fromString(id));
    }
}

