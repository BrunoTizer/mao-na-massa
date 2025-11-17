package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Avaliacao;
import br.com.maonamassa.domains.Servico;
import br.com.maonamassa.domains.Usuario;
import br.com.maonamassa.gateways.dtos.request.AvaliacaoRequestDto;
import br.com.maonamassa.gateways.dtos.response.AvaliacaoResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoRepository avaliacaoRepository;
    private final ServicoRepository servicoRepository;
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public List<AvaliacaoResponseDto> listarTodos() {
        return avaliacaoRepository.findAll()
                .stream()
                .map(AvaliacaoResponseDto::fromAvaliacao)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDto> buscarPorId(@PathVariable String id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(UUID.fromString(id)).get();
        return ResponseEntity.ok(AvaliacaoResponseDto.fromAvaliacao(avaliacao));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoResponseDto criar(@Valid @RequestBody AvaliacaoRequestDto dto) {
        Servico servico = servicoRepository.findById(dto.getServicoId()).get();
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).get();
        Avaliacao avaliacao = dto.toAvaliacao()
                .withServico(servico)
                .withUsuario(usuario);
        Avaliacao salva = avaliacaoRepository.save(avaliacao);
        return AvaliacaoResponseDto.fromAvaliacao(salva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        avaliacaoRepository.deleteById(UUID.fromString(id));
    }
}
