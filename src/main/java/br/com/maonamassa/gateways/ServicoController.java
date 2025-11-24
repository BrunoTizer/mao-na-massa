package br.com.maonamassa.gateways;

import br.com.maonamassa.gateways.dtos.request.ServicoRequestDto;
import br.com.maonamassa.gateways.dtos.response.ServicoResponseDto;
import br.com.maonamassa.services.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    @GetMapping
    public Page<ServicoResponseDto> listarTodos(
            @RequestParam(required = false) String cidade,
            Pageable pageable
    ) {
        return servicoService.listarTodos(cidade, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDto> buscarPorId(@PathVariable String id) {
        ServicoResponseDto servico = servicoService.buscarPorId(UUID.fromString(id));
        return ResponseEntity.ok(servico);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoResponseDto criar(@Valid @RequestBody ServicoRequestDto dto) {
        return servicoService.criar(dto);
    }

    @PutMapping("/{id}")
    public ServicoResponseDto atualizar(@PathVariable String id, @Valid @RequestBody ServicoRequestDto dto) {
        return servicoService.atualizar(UUID.fromString(id), dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        servicoService.deletar(UUID.fromString(id));
    }
}

