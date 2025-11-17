package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Profissional;
import br.com.maonamassa.domains.Servico;
import br.com.maonamassa.gateways.dtos.request.ServicoRequestDto;
import br.com.maonamassa.gateways.dtos.response.ServicoResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoRepository servicoRepository;
    private final ProfissionalRepository profissionalRepository;

    @GetMapping
    public List<ServicoResponseDto> listarTodos() {
        return servicoRepository.findAll()
                .stream()
                .map(ServicoResponseDto::fromServico)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDto> buscarPorId(@PathVariable String id) {
        Servico servico = servicoRepository.findById(UUID.fromString(id)).get();
        return ResponseEntity.ok(ServicoResponseDto.fromServico(servico));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoResponseDto criar(@Valid @RequestBody ServicoRequestDto dto) {
        Profissional profissional = profissionalRepository.findById(dto.getProfissionalId()).get();
        Servico servico = dto.toServico().withProfissional(profissional);
        Servico salvo = servicoRepository.save(servico);
        return ServicoResponseDto.fromServico(salvo);
    }

    @PutMapping("/{id}")
    public ServicoResponseDto atualizar(@PathVariable String id, @Valid @RequestBody ServicoRequestDto dto) {
        Servico servico = servicoRepository.findById(UUID.fromString(id)).get();
        Profissional profissional = profissionalRepository.findById(dto.getProfissionalId()).get();
        Servico atualizado = servico
                .withProfissional(profissional)
                .withTitulo(dto.getTitulo())
                .withDescricao(dto.getDescricao())
                .withCidade(dto.getCidade())
                .withPreco(dto.getPreco());
        Servico salvo = servicoRepository.save(atualizado);
        return ServicoResponseDto.fromServico(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        servicoRepository.deleteById(UUID.fromString(id));
    }
}
