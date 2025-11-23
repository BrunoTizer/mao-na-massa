package br.com.maonamassa.services;

import br.com.maonamassa.domains.Area;
import br.com.maonamassa.gateways.AreaRepository;
import br.com.maonamassa.gateways.CursoRepository;
import br.com.maonamassa.gateways.dtos.request.AreaRequestDto;
import br.com.maonamassa.gateways.dtos.response.AreaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    private final CursoRepository cursoRepository;

    public Page<AreaResponseDto> listarTodos(Pageable pageable) {
        return areaRepository.findAll(pageable)
                .map(AreaResponseDto::fromArea);
    }

    public AreaResponseDto buscarPorId(UUID id) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));
        return AreaResponseDto.fromArea(area);
    }

    public AreaResponseDto criar(AreaRequestDto dto) {
        Area area = dto.toArea();
        Area salva = areaRepository.save(area);
        return AreaResponseDto.fromArea(salva);
    }

    public AreaResponseDto atualizar(UUID id, AreaRequestDto dto) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));
        Area atualizada = area.withNome(dto.getNome());
        Area salva = areaRepository.save(atualizada);
        return AreaResponseDto.fromArea(salva);
    }

    public void deletar(UUID id) {
        if (!areaRepository.existsById(id)) {
            throw new RuntimeException("Área não encontrada");
        }

        if (cursoRepository.existsByAreaId(id)) {
            throw new RuntimeException("Não é possível excluir esta área pois existem cursos vinculados a ela");
        }

        areaRepository.deleteById(id);
    }
}
