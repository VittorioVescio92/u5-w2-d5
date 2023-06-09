package VittorioVescio.u5w2d5.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import VittorioVescio.u5w2d5.entities.Dispositivo;
import VittorioVescio.u5w2d5.entities.Utente;
import VittorioVescio.u5w2d5.entities.payloads.RegistrazioneUtentePayload;
import VittorioVescio.u5w2d5.exceptions.NotFoundException;
import VittorioVescio.u5w2d5.services.UtentiService;

@RestController
@RequestMapping("/users")
public class UtentiController {
	@Autowired
	private UtentiService utentiService;

	// GET UTENTI

	@GetMapping("")
	public Page<Utente> getUtenti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return utentiService.find(page, size, sortBy);
	}

	// GET SINGOLO UTENTE

	@GetMapping("/{id}")
	public Utente getById(@PathVariable UUID id) throws NotFoundException {
		return utentiService.findById(id);
	}

	// MODIFICA SINGOLO UTENTE

	@PutMapping("/{userId}")
	public Utente updateUser(@PathVariable UUID userId, @RequestBody RegistrazioneUtentePayload body) throws Exception {
		return utentiService.findByIdAndUpdate(userId, body);
	}

	// ELIMINAZIONE SINGOLO UTENTE

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId) throws NotFoundException {
		utentiService.findByIdAndDelete(userId);
	}

	// GET LISTA DISPOSITIVI UTENTE

	@GetMapping("/{id}/devices")
	public List<Dispositivo> getListaDispositivi(@PathVariable UUID id) {
		return utentiService.findUsersDevices(id);
	}
}
