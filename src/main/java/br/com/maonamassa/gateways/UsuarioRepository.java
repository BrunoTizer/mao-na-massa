package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Page<Usuario> findByCidade(String cidade, Pageable pageable);
}
