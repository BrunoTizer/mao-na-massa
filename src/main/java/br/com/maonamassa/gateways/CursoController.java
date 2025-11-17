package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Curso;
import br.com.maonamassa.gateways.dtos.request.CursoRequestDto;
import br.com.maonamassa.gateways.dtos.response.CursoResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoRepository cursoRepository;

    @GetMapping
    public List<CursoResponseDto> listarTodos() {
        return cursoRepository.findAll()
                .stream()
                .map(CursoResponseDto::fromCurso)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDto> buscarPorId(@PathVariable String id) {
        Curso curso = cursoRepository.findById(UUID.fromString(id)).get();
        return ResponseEntity.ok(CursoResponseDto.fromCurso(curso));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoResponseDto criar(@Valid @RequestBody CursoRequestDto dto) {
        Curso curso = dto.toCurso();
        Curso salvo = cursoRepository.save(curso);
        return CursoResponseDto.fromCurso(salvo);
    }

    @PutMapping("/{id}")
    public CursoResponseDto atualizar(@PathVariable String id, @Valid @RequestBody CursoRequestDto dto) {
        Curso curso = cursoRepository.findById(UUID.fromString(id)).get();
        Curso atualizado = curso
                .withTitulo(dto.getTitulo())
                .withDescricao(dto.getDescricao())
                .withArea(dto.getArea())
                .withNivel(dto.getNivel());
        Curso salvo = cursoRepository.save(atualizado);
        return CursoResponseDto.fromCurso(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        cursoRepository.deleteById(UUID.fromString(id));
    }
}
