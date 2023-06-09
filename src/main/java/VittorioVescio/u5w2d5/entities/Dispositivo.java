package VittorioVescio.u5w2d5.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "dispositivi")
public class Dispositivo {
	@Id
	@GeneratedValue
	private UUID id;
	@Enumerated(EnumType.STRING)
	private TipoDispositivo tipoDispositivo;
	@Enumerated(EnumType.STRING)
	private StatoDispositivo statoDispositivo;
	@ManyToOne
	private Utente utente;

	public Dispositivo(TipoDispositivo tipoDispositivo, StatoDispositivo statoDispositivo, Utente utente) {
		super();
		this.tipoDispositivo = tipoDispositivo;
		this.statoDispositivo = statoDispositivo;
		this.utente = utente;
	}

	public Dispositivo(TipoDispositivo tipoDispositivo, StatoDispositivo statoDispositivo) {
		super();
		this.tipoDispositivo = tipoDispositivo;
		this.statoDispositivo = statoDispositivo;
	}

	public Dispositivo(TipoDispositivo tipoDispositivo) {
		super();
		this.tipoDispositivo = tipoDispositivo;
		this.statoDispositivo = StatoDispositivo.DISPONIBILE;
	}

	@Override
	public String toString() {
		return "Dispositivo [id=" + id + ", tipoDispositivo=" + tipoDispositivo + ", statoDispositivo="
				+ statoDispositivo + "]";
	}

}
