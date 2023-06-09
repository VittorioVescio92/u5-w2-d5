package VittorioVescio.u5w2d5.entities.payloads;

import java.util.UUID;

import VittorioVescio.u5w2d5.entities.StatoDispositivo;
import VittorioVescio.u5w2d5.entities.TipoDispositivo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ModificaDispositivoPayload {
	@NotNull(message = "è obbligatorio inserire il tipo del dispositivo!")
	TipoDispositivo tipoDispositivo;
	@NotNull(message = "è obbligatorio inserire lo stato del dispositivo!")
	StatoDispositivo statoDispositivo;
	@NotNull(message = "è obbligatorio inserire l'assegnatario dell'apparecchio!")
	UUID utenteId;
}