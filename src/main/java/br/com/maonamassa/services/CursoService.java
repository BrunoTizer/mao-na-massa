package br.com.maonamassa.services;

import br.com.maonamassa.domains.Area;
import br.com.maonamassa.domains.Curso;
import br.com.maonamassa.gateways.AreaRepository;
import br.com.maonamassa.gateways.CursoRepository;
import br.com.maonamassa.gateways.dtos.request.CursoRequestDto;
import br.com.maonamassa.gateways.dtos.response.CursoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final AreaRepository areaRepository;

    public Page<CursoResponseDto> listarTodos(UUID areaId, Pageable pageable) {
        Page<Curso> cursos;

        if (areaId != null) {
            cursos = cursoRepository.findByAreaId(areaId, pageable);
        } else {
            cursos = cursoRepository.findAll(pageable);
        }

        return cursos.map(CursoResponseDto::fromCurso);
    }

    public CursoResponseDto buscarPorId(UUID id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        return CursoResponseDto.fromCurso(curso);
    }

    public CursoResponseDto criar(CursoRequestDto dto) {
        Area area = areaRepository.findById(dto.getAreaId())
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));

        Curso curso = dto.toCurso().withArea(area);
        Curso salvo = cursoRepository.save(curso);
        return CursoResponseDto.fromCurso(salvo);
    }

    public CursoResponseDto atualizar(UUID id, CursoRequestDto dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Area area = areaRepository.findById(dto.getAreaId())
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));

        Curso atualizado = curso
                .withTitulo(dto.getTitulo())
                .withDescricao(dto.getDescricao())
                .withArea(area)
                .withNivel(dto.getNivel());

        Curso salvo = cursoRepository.save(atualizado);
        return CursoResponseDto.fromCurso(salvo);
    }

    public void deletar(UUID id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso não encontrado");
        }
        cursoRepository.deleteById(id);
    }
}
