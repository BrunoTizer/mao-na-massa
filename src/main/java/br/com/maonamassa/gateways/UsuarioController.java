package br.com.maonamassa.gateways;

import br.com.maonamassa.gateways.dtos.request.UsuarioRequestDto;
import br.com.maonamassa.gateways.dtos.response.UsuarioResponseDto;
import br.com.maonamassa.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public Page<UsuarioResponseDto> listarTodos(
            @RequestParam(required = false) String cidade,
            Pageable pageable
    ) {
        return usuarioService.listarTodos(cidade, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable String id) {
        UsuarioResponseDto usuario = usuarioService.buscarPorId(UUID.fromString(id));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDto criar(@Valid @RequestBody UsuarioRequestDto dto) {
        return usuarioService.criar(dto);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDto atualizar(@PathVariable String id, @Valid @RequestBody UsuarioRequestDto dto) {
        return usuarioService.atualizar(UUID.fromString(id), dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        usuarioService.deletar(UUID.fromString(id));
    }
}
