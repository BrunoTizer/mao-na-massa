package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<Curso, UUID> {
    Page<Curso> findByAreaId(UUID areaId, Pageable pageable);
}
