package br.com.maonamassa.gateways;

import br.com.maonamassa.gateways.dtos.request.AreaRequestDto;
import br.com.maonamassa.gateways.dtos.response.AreaResponseDto;
import br.com.maonamassa.services.AreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/areas")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @GetMapping
    public Page<AreaResponseDto> listarTodos(Pageable pageable) {
        return areaService.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaResponseDto> buscarPorId(@PathVariable String id) {
        AreaResponseDto area = areaService.buscarPorId(UUID.fromString(id));
        return ResponseEntity.ok(area);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AreaResponseDto criar(@Valid @RequestBody AreaRequestDto dto) {
        return areaService.criar(dto);
    }

    @PutMapping("/{id}")
    public AreaResponseDto atualizar(@PathVariable String id, @Valid @RequestBody AreaRequestDto dto) {
        return areaService.atualizar(UUID.fromString(id), dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        areaService.deletar(UUID.fromString(id));
    }
}
