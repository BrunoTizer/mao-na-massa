package br.com.maonamassa.services;

import br.com.maonamassa.domains.Profissional;
import br.com.maonamassa.domains.Usuario;
import br.com.maonamassa.gateways.ProfissionalRepository;
import br.com.maonamassa.gateways.UsuarioRepository;
import br.com.maonamassa.gateways.dtos.request.ProfissionalRequestDto;
import br.com.maonamassa.gateways.dtos.response.ProfissionalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final UsuarioRepository usuarioRepository;

    public Page<ProfissionalResponseDto> listarTodos(Boolean disponivel, Pageable pageable) {
        Page<Profissional> profissionais;

        if (disponivel != null) {
            profissionais = profissionalRepository.findByDisponivel(disponivel, pageable);
        } else {
            profissionais = profissionalRepository.findAll(pageable);
        }

        return profissionais.map(ProfissionalResponseDto::fromProfissional);
    }

    public ProfissionalResponseDto buscarPorId(UUID id) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        return ProfissionalResponseDto.fromProfissional(profissional);
    }

    public ProfissionalResponseDto criar(ProfissionalRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Profissional profissional = dto.toProfissional().withUsuario(usuario);
        Profissional salvo = profissionalRepository.save(profissional);
        return ProfissionalResponseDto.fromProfissional(salvo);
    }

    public ProfissionalResponseDto atualizar(UUID id, ProfissionalRequestDto dto) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Profissional atualizado = profissional
                .withUsuario(usuario)
                .withDescricao(dto.getDescricao())
                .withAvaliacaoMedia(dto.getAvaliacaoMedia())
                .withDisponivel(dto.getDisponivel());

        Profissional salvo = profissionalRepository.save(atualizado);
        return ProfissionalResponseDto.fromProfissional(salvo);
    }

    public void deletar(UUID id) {
        if (!profissionalRepository.existsById(id)) {
            throw new RuntimeException("Profissional não encontrado");
        }
        profissionalRepository.deleteById(id);
    }
}
