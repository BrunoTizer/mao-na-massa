package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Aula;
import br.com.maonamassa.domains.Curso;
import br.com.maonamassa.gateways.dtos.request.AulaRequestDto;
import br.com.maonamassa.gateways.dtos.response.AulaResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final AulaRepository aulaRepository;
    private final CursoRepository cursoRepository;

    @GetMapping
    public Page<AulaResponseDto> listarTodos(Pageable pageable) {
        return aulaRepository.findAll(pageable)
                .map(AulaResponseDto::fromAula);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaResponseDto> buscarPorId(@PathVariable String id) {
        Aula aula = aulaRepository.findById(UUID.fromString(id)).get();
        return ResponseEntity.ok(AulaResponseDto.fromAula(aula));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AulaResponseDto criar(@Valid @RequestBody AulaRequestDto dto) {
        Curso curso = cursoRepository.findById(dto.getCursoId()).get();
        Aula aula = dto.toAula().withCurso(curso);
        Aula salva = aulaRepository.save(aula);
        return AulaResponseDto.fromAula(salva);
    }

    @PutMapping("/{id}")
    public AulaResponseDto atualizar(@PathVariable String id, @Valid @RequestBody AulaRequestDto dto) {
        Aula aula = aulaRepository.findById(UUID.fromString(id)).get();
        Curso curso = cursoRepository.findById(dto.getCursoId()).get();
        Aula atualizada = aula
                .withCurso(curso)
                .withTitulo(dto.getTitulo())
                .withConteudo(dto.getConteudo())
                .withOrdem(dto.getOrdem());
        Aula salva = aulaRepository.save(atualizada);
        return AulaResponseDto.fromAula(salva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        aulaRepository.deleteById(UUID.fromString(id));
    }
}
