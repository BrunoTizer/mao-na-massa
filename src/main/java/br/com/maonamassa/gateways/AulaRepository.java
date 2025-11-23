package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Aula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AulaRepository extends JpaRepository<Aula, UUID> {
    Page<Aula> findByCursoId(UUID cursoId, Pageable pageable);
}
