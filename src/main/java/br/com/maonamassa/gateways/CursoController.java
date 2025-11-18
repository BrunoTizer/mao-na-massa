package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Area;
import br.com.maonamassa.domains.Curso;
import br.com.maonamassa.gateways.dtos.request.CursoRequestDto;
import br.com.maonamassa.gateways.dtos.response.CursoResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoRepository cursoRepository;
    private final AreaRepository areaRepository;

    @GetMapping
    public Page<CursoResponseDto> listarTodos(
            @RequestParam(required = false) UUID areaId,
            Pageable pageable
    ) {
        Page<Curso> cursos;

        if (areaId != null) {
            cursos = cursoRepository.findByAreaId(areaId, pageable);
        } else {
            cursos = cursoRepository.findAll(pageable);
        }

        return cursos.map(CursoResponseDto::fromCurso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDto> buscarPorId(@PathVariable String id) {
        Curso curso = cursoRepository.findById(UUID.fromString(id)).get();
        return ResponseEntity.ok(CursoResponseDto.fromCurso(curso));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoResponseDto criar(@Valid @RequestBody CursoRequestDto dto) {
        Area area = areaRepository.findById(dto.getAreaId()).get();
        Curso curso = dto.toCurso().withArea(area);
        Curso salvo = cursoRepository.save(curso);
        return CursoResponseDto.fromCurso(salvo);
    }

    @PutMapping("/{id}")
    public CursoResponseDto atualizar(@PathVariable String id, @Valid @RequestBody CursoRequestDto dto) {
        Curso curso = cursoRepository.findById(UUID.fromString(id)).get();
        Area area = areaRepository.findById(dto.getAreaId()).get();
        Curso atualizado = curso
                .withTitulo(dto.getTitulo())
                .withDescricao(dto.getDescricao())
                .withArea(area)
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
