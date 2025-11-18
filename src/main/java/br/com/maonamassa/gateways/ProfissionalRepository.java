package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Profissional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, UUID> {
    Page<Profissional> findByDisponivel(Boolean disponivel, Pageable pageable);
}
