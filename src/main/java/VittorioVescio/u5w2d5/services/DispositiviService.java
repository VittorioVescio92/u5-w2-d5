package VittorioVescio.u5w2d5.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import VittorioVescio.u5w2d5.entities.Dispositivo;
import VittorioVescio.u5w2d5.entities.Utente;
import VittorioVescio.u5w2d5.entities.payloads.ModificaDispositivoPayload;
import VittorioVescio.u5w2d5.entities.payloads.NuovoDispositivoPayload;
import VittorioVescio.u5w2d5.exceptions.NotFoundException;
import VittorioVescio.u5w2d5.repositories.DispositiviRepository;

@Service
public class DispositiviService {
	@Autowired
	DispositiviRepository dispositiviRepository;
	@Autowired
	UtentiService utentiService;

	public Dispositivo create(NuovoDispositivoPayload d) {
		Dispositivo newDispositivo = new Dispositivo(d.getTipoDispositivo());
		return dispositiviRepository.save(newDispositivo);
	}

	public Page<Dispositivo> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return dispositiviRepository.findAll(pageable);

	}

	public Dispositivo findById(UUID id) throws NotFoundException {
		return dispositiviRepository.findById(id).orElseThrow(() -> new NotFoundException("Dispositivo non trovato!"));
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Dispositivo found = this.findById(id);
		dispositiviRepository.delete(found);
	}

	public Dispositivo findByIdAndUpdate(UUID id, ModificaDispositivoPayload r) throws NotFoundException {
		Utente utente = utentiService.findById(r.getUtenteId());
		Dispositivo found = this.findById(id);
		found.setId(id);
		found.setTipoDispositivo(r.getTipoDispositivo());
		found.setStatoDispositivo(r.getStatoDispositivo());
		found.setUtente(utente);
		return dispositiviRepository.save(found);
	}
}