package VittorioVescio.u5w2d5.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import VittorioVescio.u5w2d4.auth.payloads.AuthenticationSuccessfullPayload;
import VittorioVescio.u5w2d5.entities.Utente;
import VittorioVescio.u5w2d5.entities.payloads.LoginUtentePayload;
import VittorioVescio.u5w2d5.entities.payloads.RegistrazioneUtentePayload;
import VittorioVescio.u5w2d5.exceptions.NotFoundException;
import VittorioVescio.u5w2d5.exceptions.UnauthorizedException;
import VittorioVescio.u5w2d5.services.UtentiService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UtentiService utentiService;

	@PostMapping("/register")
	public ResponseEntity<Utente> register(@RequestBody @Validated RegistrazioneUtentePayload body) {
		Utente createdUtente = utentiService.create(body);
		return new ResponseEntity<>(createdUtente, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody LoginUtentePayload body)
			throws NotFoundException {
		Utente utente = utentiService.findByUsername(body.getUsername());
		if (!body.getPassword().matches(utente.getPassword()))
			throw new UnauthorizedException("Credenziali di accesso non valide");
		String token = JWTTools.createToken(utente);
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}
