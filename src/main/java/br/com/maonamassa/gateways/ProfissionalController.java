package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Profissional;
import br.com.maonamassa.domains.Usuario;
import br.com.maonamassa.gateways.dtos.request.ProfissionalRequestDto;
import br.com.maonamassa.gateways.dtos.response.ProfissionalResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profissionais")
@RequiredArgsConstructor
public class ProfissionalController {

    private final ProfissionalRepository profissionalRepository;
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public List<ProfissionalResponseDto> listarTodos() {
        return profissionalRepository.findAll()
                .stream()
                .map(ProfissionalResponseDto::fromProfissional)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalResponseDto> buscarPorId(@PathVariable String id) {
        Profissional profissional = profissionalRepository.findById(UUID.fromString(id)).get();
        return ResponseEntity.ok(ProfissionalResponseDto.fromProfissional(profissional));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalResponseDto criar(@Valid @RequestBody ProfissionalRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).get();
        Profissional profissional = dto.toProfissional().withUsuario(usuario);
        Profissional salvo = profissionalRepository.save(profissional);
        return ProfissionalResponseDto.fromProfissional(salvo);
    }

    @PutMapping("/{id}")
    public ProfissionalResponseDto atualizar(@PathVariable String id, @Valid @RequestBody ProfissionalRequestDto dto) {
        Profissional profissional = profissionalRepository.findById(UUID.fromString(id)).get();
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).get();
        Profissional atualizado = profissional
                .withUsuario(usuario)
                .withDescricao(dto.getDescricao())
                .withAvaliacaoMedia(dto.getAvaliacaoMedia())
                .withDisponivel(dto.getDisponivel());
        Profissional salvo = profissionalRepository.save(atualizado);
        return ProfissionalResponseDto.fromProfissional(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        profissionalRepository.deleteById(UUID.fromString(id));
    }
}
