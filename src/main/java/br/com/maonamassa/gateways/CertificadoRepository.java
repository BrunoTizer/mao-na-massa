package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, UUID> {
    boolean existsByUsuarioId(UUID usuarioId);
    boolean existsByCursoId(UUID cursoId);
}
