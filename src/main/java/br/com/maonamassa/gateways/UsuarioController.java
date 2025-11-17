package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Usuario;
import br.com.maonamassa.gateways.dtos.request.UsuarioRequestDto;
import br.com.maonamassa.gateways.dtos.response.UsuarioResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public List<UsuarioResponseDto> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDto::fromUsuario)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable String id) {
        Usuario usuario = usuarioRepository.findById(UUID.fromString(id)).get();
        return ResponseEntity.ok(UsuarioResponseDto.fromUsuario(usuario));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDto criar(@Valid @RequestBody UsuarioRequestDto dto) {
        Usuario usuario = dto.toUsuario();
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioResponseDto.fromUsuario(salvo);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDto atualizar(@PathVariable String id, @Valid @RequestBody UsuarioRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(UUID.fromString(id)).get();
        Usuario atualizado = usuario
                .withNome(dto.getNome())
                .withEmail(dto.getEmail())
                .withSenha(dto.getSenha())
                .withCidade(dto.getCidade())
                .withAreaInteresse(dto.getAreaInteresse())
                .withTipoUsuario(dto.getTipoUsuario());
        Usuario salvo = usuarioRepository.save(atualizado);
        return UsuarioResponseDto.fromUsuario(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        usuarioRepository.deleteById(UUID.fromString(id));
    }
}
