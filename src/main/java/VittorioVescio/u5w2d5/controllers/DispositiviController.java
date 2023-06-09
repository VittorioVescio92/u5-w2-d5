package VittorioVescio.u5w2d5.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import VittorioVescio.u5w2d5.entities.Dispositivo;
import VittorioVescio.u5w2d5.entities.payloads.ModificaDispositivoPayload;
import VittorioVescio.u5w2d5.entities.payloads.NuovoDispositivoPayload;
import VittorioVescio.u5w2d5.exceptions.NotFoundException;
import VittorioVescio.u5w2d5.services.DispositiviService;

@RestController
@RequestMapping("/devices")
public class DispositiviController {
	@Autowired
	private DispositiviService dispositiviService;

	@GetMapping("")
	public Page<Dispositivo> getDispositivo(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return dispositiviService.findAll(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public Dispositivo getById(@PathVariable UUID id) {
		return dispositiviService.findById(id);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivo create(@RequestBody @Validated NuovoDispositivoPayload p) {
		return dispositiviService.create(p);
	}

	@PutMapping("/{id}")
	public Dispositivo updateDispositivo(@PathVariable UUID id, @RequestBody ModificaDispositivoPayload body)
			throws Exception {
		return dispositiviService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDevice(@PathVariable UUID id) throws NotFoundException {
		dispositiviService.findByIdAndDelete(id);
	}

}
