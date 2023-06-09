package VittorioVescio.u5w2d5.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import VittorioVescio.u5w2d5.entities.Utente;

@Repository
public interface UtentiRepository extends JpaRepository<Utente, UUID> {
	Optional<Utente> findByEmail(String email);

	Optional<Utente> findByUsername(String username);
}
