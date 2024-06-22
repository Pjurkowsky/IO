package DEL;


import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.PeselException;
import DEL.Exceptions.StringException;

import java.time.LocalDate;


public class DaneOsobowe {

	private String imie;

	public DaneOsobowe(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9) throws StringException, PeselException, KodPocztowyException {
		setImie(s);
		setDrugieImie(s1);
		setNazwisko(s2);
		this.plec = Boolean.parseBoolean(s3);
		setPesel(s4);
		this.dataUrodzenia = LocalDate.parse(s5);
		this.daneAdresowe = new DaneAdresowe(s6,s7,s8,s9);
	}

	public String getImie() {
		return imie;
	}

	public String getDrugieImie() {
		return drugieImie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public boolean getPlec() {
		return plec;
	}

	public LocalDate getDataUrodzenia() {
		return dataUrodzenia;
	}

	public String getMiejsceUrodzenia() {
		return miejsceUrodzenia;
	}

	public DaneAdresowe getDaneAdresowe() {
		return daneAdresowe;
	}

	public void setImie(String imie) throws StringException {
		if(imie == null || imie.equals(""))
			throw new StringException("Imie nie moze byc puste");

		if(!imie.matches("[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*"))
			throw new StringException("Imie zawiera cyfry badz znaki specjalne");

		this.imie = imie;
	}

	public void setDrugieImie(String drugieImie) throws StringException {

		if(!drugieImie.matches("[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*"))
			throw new StringException("Drugie Imie zawiera cyfry badz znaki specjalne");

		this.drugieImie = drugieImie;
	}

	public void setNazwisko(String nazwisko) throws StringException {
		if(nazwisko == null || nazwisko.equals(""))
			throw new StringException("Nazwisko nie moze byc puste");

		if(!nazwisko.matches("[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*"))
			throw new StringException("Nazwisko zawiera cyfry badz znaki specjalne");
		this.nazwisko = nazwisko;
	}

	public void setPlec(boolean plec) {
		this.plec = plec;
	}

	public void setPesel(String pesel) throws PeselException {

		if (!pesel.matches("\\d+")) {
			throw new PeselException("Pesel contains not only numbers");
		}
		if (!pesel.matches("\\d{11}"))
		{
			throw new PeselException("Pesel is too long");
		}

		this.pesel = pesel;
	}

	public void setDataUrodzenia(LocalDate dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}

	public void setMiejsceUrodzenia(String miejsceUrodzenia) throws StringException {
		if(miejsceUrodzenia == null || miejsceUrodzenia.equals(""))
			throw new StringException("Miejsce urodzenia nie moze byc puste");

		if(!miejsceUrodzenia.matches("[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*"))
			throw new StringException("Miejsce urodzenia zawiera cyfry badz znaki specjalne");

		this.miejsceUrodzenia = miejsceUrodzenia;
	}

	public void setDaneAdresowe(DaneAdresowe daneAdresowe) {
		this.daneAdresowe = daneAdresowe;
	}

	private String drugieImie;
	private String nazwisko;
	private boolean plec;
	private String pesel;
	private LocalDate dataUrodzenia;
	private String miejsceUrodzenia;
	private DaneAdresowe daneAdresowe;


	public DaneOsobowe(String imie, String drugieImie, String nazwisko, boolean plec, String pesel, LocalDate dataUrodzenia, String miejsceUrodzenia, DaneAdresowe daneAdresowe) throws PeselException, StringException {
		setImie(imie);
		setDrugieImie(drugieImie);
		setNazwisko(nazwisko);
		this.plec = plec;
		setPesel(pesel);
		this.dataUrodzenia = dataUrodzenia;
		this.daneAdresowe = daneAdresowe;
	}

	public DaneOsobowe(String[] daneOsobowe, String[] daneAdresowe) throws StringException, PeselException, KodPocztowyException {
		setImie(daneOsobowe[0]);
		setDrugieImie(daneOsobowe[1]);
		setNazwisko(daneOsobowe[2]);
		this.plec = Boolean.parseBoolean((daneOsobowe[3]));

		setPesel(daneOsobowe[4]);
		this.dataUrodzenia = LocalDate.parse(daneOsobowe[5]);
		this.daneAdresowe = new DaneAdresowe(daneAdresowe);
	}
	public String getPESEL() {
		return this.pesel;
	}


}