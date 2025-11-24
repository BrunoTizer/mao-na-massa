package br.com.maonamassa.services;

import br.com.maonamassa.domains.Certificado;
import br.com.maonamassa.domains.Curso;
import br.com.maonamassa.domains.Usuario;
import br.com.maonamassa.gateways.CertificadoRepository;
import br.com.maonamassa.gateways.CursoRepository;
import br.com.maonamassa.gateways.UsuarioRepository;
import br.com.maonamassa.gateways.dtos.request.CertificadoRequestDto;
import br.com.maonamassa.gateways.dtos.response.CertificadoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public Page<CertificadoResponseDto> listarTodos(Pageable pageable) {
        return certificadoRepository.findAll(pageable)
                .map(CertificadoResponseDto::fromCertificado);
    }

    public CertificadoResponseDto buscarPorId(UUID id) {
        Certificado certificado = certificadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificado não encontrado"));
        return CertificadoResponseDto.fromCertificado(certificado);
    }

    public CertificadoResponseDto criar(CertificadoRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Certificado certificado = dto.toCertificado()
                .withUsuario(usuario)
                .withCurso(curso);

        Certificado salvo = certificadoRepository.save(certificado);
        return CertificadoResponseDto.fromCertificado(salvo);
    }

    public void deletar(UUID id) {
        if (!certificadoRepository.existsById(id)) {
            throw new RuntimeException("Certificado não encontrado");
        }
        certificadoRepository.deleteById(id);
    }
}
