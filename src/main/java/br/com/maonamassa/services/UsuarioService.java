package br.com.maonamassa.services;

import br.com.maonamassa.domains.Area;
import br.com.maonamassa.domains.Usuario;
import br.com.maonamassa.gateways.AreaRepository;
import br.com.maonamassa.gateways.UsuarioRepository;
import br.com.maonamassa.gateways.dtos.request.UsuarioRequestDto;
import br.com.maonamassa.gateways.dtos.response.UsuarioResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AreaRepository areaRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<UsuarioResponseDto> listarTodos(String cidade, Pageable pageable) {
        Page<Usuario> usuarios;

        if (cidade != null) {
            usuarios = usuarioRepository.findByCidade(cidade, pageable);
        } else {
            usuarios = usuarioRepository.findAll(pageable);
        }

        return usuarios.map(UsuarioResponseDto::fromUsuario);
    }

    public UsuarioResponseDto buscarPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return UsuarioResponseDto.fromUsuario(usuario);
    }

    public UsuarioResponseDto criar(UsuarioRequestDto dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Area area = null;
        if (dto.getAreaId() != null) {
            area = areaRepository.findById(dto.getAreaId())
                    .orElseThrow(() -> new RuntimeException("Área não encontrada"));
        }

        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());

        Usuario usuario = dto.toUsuario()
                .withSenha(senhaCriptografada)
                .withArea(area);

        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioResponseDto.fromUsuario(salvo);
    }

    public UsuarioResponseDto atualizar(UUID id, UsuarioRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Area area = null;
        if (dto.getAreaId() != null) {
            area = areaRepository.findById(dto.getAreaId())
                    .orElseThrow(() -> new RuntimeException("Área não encontrada"));
        }

        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());

        Usuario atualizado = usuario
                .withNome(dto.getNome())
                .withEmail(dto.getEmail())
                .withSenha(senhaCriptografada)
                .withCidade(dto.getCidade())
                .withArea(area)
                .withTipoUsuario(dto.getTipoUsuario());

        Usuario salvo = usuarioRepository.save(atualizado);
        return UsuarioResponseDto.fromUsuario(salvo);
    }

    public void deletar(UUID id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
