package br.com.maonamassa.services;

import br.com.maonamassa.domains.Aula;
import br.com.maonamassa.domains.Curso;
import br.com.maonamassa.gateways.AulaRepository;
import br.com.maonamassa.gateways.CursoRepository;
import br.com.maonamassa.gateways.dtos.request.AulaRequestDto;
import br.com.maonamassa.gateways.dtos.response.AulaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository aulaRepository;
    private final CursoRepository cursoRepository;

    public Page<AulaResponseDto> listarTodos(Pageable pageable) {
        return aulaRepository.findAll(pageable)
                .map(AulaResponseDto::fromAula);
    }

    public Page<AulaResponseDto> listarPorCurso(UUID cursoId, Pageable pageable) {
        return aulaRepository.findByCursoId(cursoId, pageable)
                .map(AulaResponseDto::fromAula);
    }

    public AulaResponseDto buscarPorId(UUID id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
        return AulaResponseDto.fromAula(aula);
    }

    public AulaResponseDto criar(AulaRequestDto dto) {
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Aula aula = dto.toAula().withCurso(curso);
        Aula salva = aulaRepository.save(aula);
        return AulaResponseDto.fromAula(salva);
    }

    public AulaResponseDto atualizar(UUID id, AulaRequestDto dto) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Aula atualizada = aula
                .withCurso(curso)
                .withTitulo(dto.getTitulo())
                .withConteudo(dto.getConteudo())
                .withOrdem(dto.getOrdem());

        Aula salva = aulaRepository.save(atualizada);
        return AulaResponseDto.fromAula(salva);
    }

    public void deletar(UUID id) {
        if (!aulaRepository.existsById(id)) {
            throw new RuntimeException("Aula não encontrada");
        }
        aulaRepository.deleteById(id);
    }
}
