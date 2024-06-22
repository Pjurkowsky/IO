package DEL;

import java.time.LocalDateTime;

public class Wniosek {

	private static long liczbaWnioskow;

	public long getId() {
		return id;
	}

	private long id;

	public StatusWniosku getStatus() {
		return status;
	}

	private StatusWniosku status = StatusWniosku.rozpatrywany;
	private LocalDateTime dataZlozenia;

	public Pracownik getPracownik() {
		return pracownik;
	}

	private LocalDateTime dataRozpatrzenia;

	public DaneOsobowe getNoweDaneOsobowe() {
		return noweDaneOsobowe;
	}

	private DaneOsobowe noweDaneOsobowe;

	public String getKomentarzZwrotny() {
		return komentarzZwrotny;
	}

	private String komentarzZwrotny;

	public TypWniosku getTypWniosku() {
		return typWniosku;
	}

	private TypWniosku typWniosku;
	private Petent petent;
	private Certyfikat certyfikatUrodzenia;
	private Certyfikat certyfikatMalzenstwa;
	private Certyfikat certyfikatZgonu;

	public void setPracownik(Pracownik pracownik) {
		this.pracownik = pracownik;
	}

	private Pracownik pracownik;


	public Wniosek(Petent petent, LocalDateTime dataZlozenia, TypWniosku typWniosku, Certyfikat certyfikatUrodzenia, Certyfikat certyfikatMalzenstwa) {
		this.petent = petent;
		this.dataZlozenia = dataZlozenia;
		this.typWniosku = typWniosku;
		this.certyfikatUrodzenia = certyfikatUrodzenia;
		this.certyfikatMalzenstwa = certyfikatMalzenstwa;
		this.certyfikatZgonu = certyfikatZgonu;
		liczbaWnioskow++;
		id=liczbaWnioskow;
	}
	public Wniosek(Petent petent, LocalDateTime dataZlozenia, TypWniosku typWniosku, DaneOsobowe daneOsobowe, Certyfikat certyfikatMalzenstwa, Certyfikat certyfikatZgonu) {
		this.petent = petent;
		this.dataZlozenia = dataZlozenia;
		this.typWniosku = typWniosku;
		this.noweDaneOsobowe = daneOsobowe;
		this.certyfikatMalzenstwa = certyfikatMalzenstwa;
		this.certyfikatZgonu = certyfikatZgonu;
		liczbaWnioskow++;
		id=liczbaWnioskow;
	}

	public Petent getPetent() {
		return this.petent;
	}


	public void setStatus(StatusWniosku status) {
		this.status = status;
	}



}