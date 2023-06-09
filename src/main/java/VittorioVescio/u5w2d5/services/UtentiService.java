package VittorioVescio.u5w2d5.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import VittorioVescio.u5w2d5.entities.Dispositivo;
import VittorioVescio.u5w2d5.entities.StatoDispositivo;
import VittorioVescio.u5w2d5.entities.Utente;
import VittorioVescio.u5w2d5.entities.payloads.RegistrazioneUtentePayload;
import VittorioVescio.u5w2d5.exceptions.BadRequestException;
import VittorioVescio.u5w2d5.exceptions.NotFoundException;
import VittorioVescio.u5w2d5.repositories.UtentiRepository;

@Service
public class UtentiService {
	@Autowired
	UtentiRepository utentiRepository;

	public Page<Utente> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return utentiRepository.findAll(pageable);
	}

	public Utente findById(UUID id) throws NotFoundException {
		return utentiRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato!"));
	}

	public Utente create(RegistrazioneUtentePayload u) {
		utentiRepository.findByEmail(u.getEmail()).ifPresent(user -> { // VERIFICO SE LA MAIL ESISTE GIA'
			throw new BadRequestException("Email " + user.getUsername() + " already in use!");
		});
		List<Dispositivo> list = new ArrayList<>();
		Utente newUtente = new Utente(u.getUsername(), u.getNome(), u.getCognome(), u.getEmail(), u.getPassword(),
				list);
		return utentiRepository.save(newUtente);
	}

	public Utente findByIdAndUpdate(UUID id, RegistrazioneUtentePayload r) throws NotFoundException {
		Utente found = this.findById(id);

		found.setId(id);
		found.setUsername(r.getUsername());
		found.setNome(r.getNome());
		found.setCognome(r.getCognome());
		found.setEmail(r.getEmail());
		found.setPassword(r.getPassword());
		found.setDispositivi(found.getDispositivi());
		return utentiRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Utente found = this.findById(id);
		List<Dispositivo> dispositiviUtente = found.getDispositivi();
		Iterator<Dispositivo> i = dispositiviUtente.iterator();
		while (i.hasNext()) { // PRIMA DI ELIMINARE L'UTENTE SETTO IL DISPOSITIVO COME DISPONIBILE E LA
								// CHIAVE ESTERNA A NULL
			Dispositivo current = i.next();
			current.setStatoDispositivo(StatoDispositivo.DISPONIBILE);
			current.setUtente(null);
		}
		utentiRepository.delete(found);

	}

	public Utente findByUsername(String username) throws NotFoundException {
		return utentiRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("username non trovato"));
	}

	public List<Dispositivo> findUsersDevices(UUID id) throws NotFoundException {
		Utente utente = this.findById(id);
		return utente.getDispositivi();
	}
}
