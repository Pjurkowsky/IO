package DEL;

import java.time.LocalDate;

public class Certyfikat {

	private long id;
	private TypCertyfikatu typCertyfikatu;
	private LocalDate dataWydania;


	public Certyfikat(long id, TypCertyfikatu typCertyfikatu, LocalDate dataWydania) {
		this.id = id;
		this.typCertyfikatu = typCertyfikatu;
		this.dataWydania = dataWydania;
	}

}