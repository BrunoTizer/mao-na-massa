package br.com.maonamassa.services;

import br.com.maonamassa.domains.Avaliacao;
import br.com.maonamassa.domains.Servico;
import br.com.maonamassa.domains.Usuario;
import br.com.maonamassa.gateways.AvaliacaoRepository;
import br.com.maonamassa.gateways.ServicoRepository;
import br.com.maonamassa.gateways.UsuarioRepository;
import br.com.maonamassa.gateways.dtos.request.AvaliacaoRequestDto;
import br.com.maonamassa.gateways.dtos.response.AvaliacaoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final ServicoRepository servicoRepository;
    private final UsuarioRepository usuarioRepository;

    public Page<AvaliacaoResponseDto> listarTodos(Pageable pageable) {
        return avaliacaoRepository.findAll(pageable)
                .map(AvaliacaoResponseDto::fromAvaliacao);
    }

    public AvaliacaoResponseDto buscarPorId(UUID id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
        return AvaliacaoResponseDto.fromAvaliacao(avaliacao);
    }

    public AvaliacaoResponseDto criar(AvaliacaoRequestDto dto) {
        Servico servico = servicoRepository.findById(dto.getServicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Avaliacao avaliacao = dto.toAvaliacao()
                .withServico(servico)
                .withUsuario(usuario);

        Avaliacao salva = avaliacaoRepository.save(avaliacao);
        return AvaliacaoResponseDto.fromAvaliacao(salva);
    }

    public void deletar(UUID id) {
        if (!avaliacaoRepository.existsById(id)) {
            throw new RuntimeException("Avaliação não encontrada");
        }
        avaliacaoRepository.deleteById(id);
    }
}
