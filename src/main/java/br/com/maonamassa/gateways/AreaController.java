package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Area;
import br.com.maonamassa.gateways.dtos.request.AreaRequestDto;
import br.com.maonamassa.gateways.dtos.response.AreaResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/areas")
@RequiredArgsConstructor
public class AreaController {

    private final AreaRepository areaRepository;

    @GetMapping
    public Page<AreaResponseDto> listarTodos(Pageable pageable) {
        return areaRepository.findAll(pageable)
                .map(AreaResponseDto::fromArea);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaResponseDto> buscarPorId(@PathVariable String id) {
        Area area = areaRepository.findById(UUID.fromString(id)).get();
        return ResponseEntity.ok(AreaResponseDto.fromArea(area));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AreaResponseDto criar(@Valid @RequestBody AreaRequestDto dto) {
        Area area = dto.toArea();
        Area salva = areaRepository.save(area);
        return AreaResponseDto.fromArea(salva);
    }

    @PutMapping("/{id}")
    public AreaResponseDto atualizar(@PathVariable String id, @Valid @RequestBody AreaRequestDto dto) {
        Area area = areaRepository.findById(UUID.fromString(id)).get();
        Area atualizada = area.withNome(dto.getNome());
        Area salva = areaRepository.save(atualizada);
        return AreaResponseDto.fromArea(salva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        areaRepository.deleteById(UUID.fromString(id));
    }
}
