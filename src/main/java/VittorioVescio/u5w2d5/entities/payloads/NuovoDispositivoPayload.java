package VittorioVescio.u5w2d5.entities.payloads;

import VittorioVescio.u5w2d5.entities.TipoDispositivo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NuovoDispositivoPayload {
	@NotNull(message = "è obbligatorio inserire il tipo del dispositivo!")
	TipoDispositivo tipoDispositivo;
}
