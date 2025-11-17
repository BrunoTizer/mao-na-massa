package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Certificado;
import br.com.maonamassa.domains.Curso;
import br.com.maonamassa.domains.Usuario;
import br.com.maonamassa.gateways.dtos.request.CertificadoRequestDto;
import br.com.maonamassa.gateways.dtos.response.CertificadoResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/certificados")
@RequiredArgsConstructor
public class CertificadoController {

    private final CertificadoRepository certificadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    @GetMapping
    public List<CertificadoResponseDto> listarTodos() {
        return certificadoRepository.findAll()
                .stream()
                .map(CertificadoResponseDto::fromCertificado)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificadoResponseDto> buscarPorId(@PathVariable String id) {
        Certificado certificado = certificadoRepository.findById(UUID.fromString(id)).get();
        return ResponseEntity.ok(CertificadoResponseDto.fromCertificado(certificado));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CertificadoResponseDto criar(@Valid @RequestBody CertificadoRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).get();
        Curso curso = cursoRepository.findById(dto.getCursoId()).get();
        Certificado certificado = dto.toCertificado()
                .withUsuario(usuario)
                .withCurso(curso);
        Certificado salvo = certificadoRepository.save(certificado);
        return CertificadoResponseDto.fromCertificado(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        certificadoRepository.deleteById(UUID.fromString(id));
    }
}
