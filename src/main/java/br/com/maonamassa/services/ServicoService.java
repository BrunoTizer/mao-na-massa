package br.com.maonamassa.services;

import br.com.maonamassa.domains.Profissional;
import br.com.maonamassa.domains.Servico;
import br.com.maonamassa.gateways.ProfissionalRepository;
import br.com.maonamassa.gateways.ServicoRepository;
import br.com.maonamassa.gateways.dtos.request.ServicoRequestDto;
import br.com.maonamassa.gateways.dtos.response.ServicoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final ProfissionalRepository profissionalRepository;

    public Page<ServicoResponseDto> listarTodos(String cidade, Pageable pageable) {
        Page<Servico> servicos;

        if (cidade != null) {
            servicos = servicoRepository.findByCidade(cidade, pageable);
        } else {
            servicos = servicoRepository.findAll(pageable);
        }

        return servicos.map(ServicoResponseDto::fromServico);
    }

    public ServicoResponseDto buscarPorId(UUID id) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        return ServicoResponseDto.fromServico(servico);
    }

    public ServicoResponseDto criar(ServicoRequestDto dto) {
        Profissional profissional = null;

        if (dto.getProfissionalId() != null) {
            profissional = profissionalRepository.findById(dto.getProfissionalId())
                    .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        }

        Servico servico = dto.toServico()
                .withProfissional(profissional)
                .withStatus(profissional == null ? "PENDENTE" : "ACEITO");

        Servico salvo = servicoRepository.save(servico);
        return ServicoResponseDto.fromServico(salvo);
    }

    public ServicoResponseDto atualizar(UUID id, ServicoRequestDto dto) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Profissional profissional = profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        Servico atualizado = servico
                .withProfissional(profissional)
                .withTitulo(dto.getTitulo())
                .withDescricao(dto.getDescricao())
                .withCidade(dto.getCidade())
                .withPreco(dto.getPreco());

        Servico salvo = servicoRepository.save(atualizado);
        return ServicoResponseDto.fromServico(salvo);
    }

    public ServicoResponseDto aceitar(UUID id, UUID profissionalId) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        if (!"PENDENTE".equals(servico.getStatus())) {
            throw new RuntimeException("Serviço já foi aceito por outro profissional");
        }

        Profissional profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        Servico aceito = servico
                .withProfissional(profissional)
                .withStatus("ACEITO");

        Servico salvo = servicoRepository.save(aceito);
        return ServicoResponseDto.fromServico(salvo);
    }

    public void deletar(UUID id) {
        if (!servicoRepository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado");
        }
        servicoRepository.deleteById(id);
    }
}
