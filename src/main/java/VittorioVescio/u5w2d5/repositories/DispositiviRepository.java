package VittorioVescio.u5w2d5.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import VittorioVescio.u5w2d5.entities.Dispositivo;

@Repository
public interface DispositiviRepository extends JpaRepository<Dispositivo, UUID> {

}
