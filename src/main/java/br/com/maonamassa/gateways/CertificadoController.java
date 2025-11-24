package br.com.maonamassa.gateways;

import br.com.maonamassa.gateways.dtos.request.CertificadoRequestDto;
import br.com.maonamassa.gateways.dtos.response.CertificadoResponseDto;
import br.com.maonamassa.services.CertificadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/certificados")
@RequiredArgsConstructor
public class CertificadoController {

    private final CertificadoService certificadoService;

    @GetMapping
    public Page<CertificadoResponseDto> listarTodos(Pageable pageable) {
        return certificadoService.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificadoResponseDto> buscarPorId(@PathVariable String id) {
        CertificadoResponseDto certificado = certificadoService.buscarPorId(UUID.fromString(id));
        return ResponseEntity.ok(certificado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CertificadoResponseDto criar(@Valid @RequestBody CertificadoRequestDto dto) {
        return certificadoService.criar(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        certificadoService.deletar(UUID.fromString(id));
    }
}

