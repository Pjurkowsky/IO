package DEL;

import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.StringException;

public class DaneAdresowe {

	public String getUlica() {
		return ulica;
	}

	public String getNumerMieszkania() {
		return numerMieszkania;
	}

	public String getMiasto() {
		return miasto;
	}

	public String getKodPocztowy() {
		return kodPocztowy;
	}

	public void setUlica(String ulica) throws StringException {
		if(ulica == null || ulica.equals(""))
			throw new StringException("Nazwa ulicy nie moze byc pusta");

		if(!ulica.matches("[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*"))
			throw new StringException("Nazwa ulicy nie moze zawierac znakow specjalnych lub liczb");

		this.ulica = ulica;
	}

	public void setNumerMieszkania(String numerMieszkania) {
		this.numerMieszkania = numerMieszkania;
	}

	public void setMiasto(String miasto) throws StringException {
		if(miasto == null || miasto.equals(""))
			throw new StringException("Nazwa miasta nie moze byc pusta");

		if(!miasto.matches("[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*"))
			throw new StringException("Nazwa miasta nie moze zawierac znakow specjalnych lub liczb");

		this.miasto = miasto;
	}

	public void setKodPocztowy(String kodPocztowy) throws KodPocztowyException {
		if(kodPocztowy == null || kodPocztowy.equals(""))
			throw new KodPocztowyException("Kod pocztowy nie moze byc pusty");
		if (!kodPocztowy.matches("[0-9]{2}-[0-9]{3}"))
			throw new KodPocztowyException("Kod pocztowy musi byc w poprawnym formacie xx-xxx");

		this.kodPocztowy = kodPocztowy;
	}

	private String ulica;
	private String numerMieszkania;

	private String miasto;
	private String kodPocztowy;

	public DaneAdresowe(String ulica, String numerMieszkania, String miasto, String kodPocztowy) throws StringException, KodPocztowyException {
		setUlica(ulica);
		this.numerMieszkania = numerMieszkania;
		setMiasto(miasto);
		setKodPocztowy(kodPocztowy);
	}
	public DaneAdresowe(String[] dane)  throws StringException, KodPocztowyException {
		setUlica(dane[0]);
		this.numerMieszkania = dane[1];
		setMiasto(dane[2]);
		setKodPocztowy(dane[3]);
	}

}