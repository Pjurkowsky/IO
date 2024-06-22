package DEL;

public class Petent {

	private long id;

	public void setDaneOsobowe(DaneOsobowe daneOsobowe) {
		this.daneOsobowe = daneOsobowe;
	}

	private DaneOsobowe daneOsobowe;
	private DaneLogowania daneLogowania;
	private Certyfikat certyfikatUrodzenia;
	private Certyfikat certyfikatMalzenstwa;
	private Certyfikat certyfikatZgonu;



	public Petent(DaneOsobowe daneOsbowe, DaneLogowania daneLogowania) {
		this.daneOsobowe = daneOsbowe;
		this.daneLogowania = daneLogowania;
	}

	public DaneOsobowe getDaneOsobowe() {
		return this.daneOsobowe;
	}

}