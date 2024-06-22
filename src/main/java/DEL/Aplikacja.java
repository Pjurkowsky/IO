package DEL;

import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.PeselException;
import DEL.Exceptions.StringException;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Aplikacja {

	private ArrayList<Petent> petenci = new ArrayList<>();

	public ArrayList<Petent> getPetenci() {
		return petenci;
	}

	public ArrayList<Wniosek> getWnioski() {
		return wnioski;
	}

	public ArrayList<Pracownik> getPracownicy() {
		return pracownicy;
	}

	public ArrayList<Petent> getRejestr() {
		return rejestr;
	}

	private ArrayList<Wniosek> wnioski = new ArrayList<>();
	private ArrayList<Pracownik> pracownicy = new ArrayList<>();
	private ArrayList<Petent> rejestr = new ArrayList<>();

	public static void main(String[] args) throws PeselException, StringException, KodPocztowyException {
		Aplikacja aplikacja = new Aplikacja();
		aplikacja.dodajPracownika();
		aplikacja.dodajPracownika();
		aplikacja.dodajPetenta(new DaneOsobowe("Lukasz",
				"Andrzej",
				"Wdowiak",
				true,
				"12345678901",
				LocalDate.of(2001,8, 13),
				"Police",
				new DaneAdresowe("Testowa",
						"69D",
						"Police",
						"69-420")), new Certyfikat(1,
				TypCertyfikatu.certyfikatUrodzenia,
				LocalDate.of(2001,8, 15)), null);
		aplikacja.dodajPetenta(new DaneOsobowe("Szymon",
				"Simon",
				"Adamczak",
				false,
				"12365678601",
				LocalDate.of(2002,9, 18),
				"Torun",
				new DaneAdresowe("Urzednicza",
						"2",
						"Torun",
						"21-370")), new Certyfikat(2,
				TypCertyfikatu.certyfikatUrodzenia,
				LocalDate.of(2002,9, 25)), null);
		for (Wniosek wniosek : aplikacja.wnioski) {
			aplikacja.przydzielPracownika(wniosek);
		}
		aplikacja.rozpatrzWniosek(aplikacja.wnioski.get(0));
		aplikacja.odrzucWniosek(aplikacja.wnioski.get(1));
		aplikacja.wniosekPowiadom(aplikacja.wnioski.get(0));
		aplikacja.wniosekPowiadom(aplikacja.wnioski.get(1));

		System.out.println("\nRejestr");
		for (Petent petent: aplikacja.rejestr) {
			aplikacja.wyswietlDanePetenta(petent);
		}
	}

	public void dodajPetenta(DaneOsobowe daneOsobowe, Certyfikat certyfikatUrodzenia, Certyfikat certyfikatMalzenstwa) {
		Petent petent = new Petent(daneOsobowe, new DaneLogowania(daneOsobowe.getPESEL(), wygenerujHaslo()));
		petenci.add(petent);
		wnioski.add(zlozWniosekOWpis(petent, certyfikatUrodzenia, certyfikatMalzenstwa));
	}

	public void dodajPracownika() {
		pracownicy.add(new Pracownik());
		System.out.println("Pracownik zostal pomyslnie dodany");
	}

	public void usunPracownika(Pracownik pracownik) {
		pracownicy.remove(pracownik);
		System.out.println("Pracownik zostal pomyslnie usuniety");
	}


	public Wniosek zlozWniosekOWpis(Petent petent, Certyfikat certyfikatUrodzenia, Certyfikat certyfikatMalzenstwa) {
		return new Wniosek(petent, LocalDateTime.now(), TypWniosku.wpisDoRejestru, certyfikatUrodzenia, certyfikatMalzenstwa);
	}

	public Wniosek zlozWniosekOEdycje(Petent petent,DaneOsobowe daneOsobowe, Certyfikat certyfikatMalzenstwa, Certyfikat certyfikatZgonu) {
		return new Wniosek(petent, LocalDateTime.now(), TypWniosku.edycjaDanych, daneOsobowe, certyfikatMalzenstwa, certyfikatZgonu);
	}
	public void przydzielPracownika(Wniosek wniosek) {
		if(wniosek.getPracownik()==null){
			for (Pracownik pracownik: pracownicy) {
				if(!pracownik.getStanPracy()){
					wniosek.setPracownik(pracownik);
					pracownik.setStanPracy(true);
					break;
				}
			}
		}
	}

	public void rozpatrzWniosek(Wniosek wniosek){
		Pracownik pracownik = wniosek.getPracownik();
		wniosek.setStatus(StatusWniosku.zatwierdzony);
		System.out.println("Wniosek (id:" + wniosek.getId()+") "+"zatwierdzony przez pracownika o numerze: " + pracownik.getId());
		dodajDoRejestru(wniosek.getPetent());
		pracownik.setStanPracy(false);
	}

	public void odrzucWniosek(Wniosek wniosek){
		Pracownik pracownik = wniosek.getPracownik();
		wniosek.setStatus(StatusWniosku.odrzucony);
		System.out.println("Wniosek (id:" + wniosek.getId()+") "+ "odrzucony przez pracownika o numerze: " + pracownik.getId());
		pracownik.setStanPracy(false);
	}

	public void wniosekPowiadom(Wniosek wniosek) {
		System.out.print("Status wniosku (id:" + wniosek.getId()+"): ");
		if(wniosek.getStatus()==StatusWniosku.rozpatrywany){
			System.out.println("rozpatrywany");
		} else if (wniosek.getStatus()==StatusWniosku.odrzucony) {
			System.out.println("odrzucony");
		}else if(wniosek.getStatus()==StatusWniosku.zatwierdzony){
			System.out.println("zatwierdzony");
		}else{
			System.out.println("Brak informacji o wniosku");
		}
	}


	public void wyswietlDanePetenta(Petent petent) {
		DaneOsobowe daneOsobowe = petent.getDaneOsobowe();
		DaneAdresowe daneAdresowe = daneOsobowe.getDaneAdresowe();
		System.out.println("Imie: " + daneOsobowe.getImie());
		System.out.println("Drugie imie: " + daneOsobowe.getDrugieImie());
		System.out.println("Nazwisko: " + daneOsobowe.getNazwisko());
		System.out.println("Plec: " + daneOsobowe.getPlec());
		System.out.println("Data urodzenia: " + daneOsobowe.getDataUrodzenia());
		System.out.println("Dane Adresowe: ");
		System.out.print("Ulica: "+daneAdresowe.getUlica()+" ");
		System.out.println(daneAdresowe.getNumerMieszkania());
		System.out.print("Miasto: "+daneAdresowe.getMiasto()+" ");
		System.out.println(daneAdresowe.getKodPocztowy());
	}


	public void dodajDoRejestru(Petent petent) {
		for (Wniosek wniosek: wnioski) {
			if(wniosek.getPetent()==petent){
				if(wniosek.getTypWniosku()==TypWniosku.wpisDoRejestru){
					if(wniosek.getStatus()==StatusWniosku.zatwierdzony){
						rejestr.add(petent);
						System.out.println("Pomyslnie dodano petenta do rejestru :)");
					} else if (wniosek.getStatus()==StatusWniosku.odrzucony) {
						System.out.println("Nie dodano petenta do rejestru :(");
						System.out.println("Powod: " + wniosek.getKomentarzZwrotny());
					}else {
						System.out.println("Wniosek nadal w trakcie rozpatrywania");
						System.out.println(wniosek.getStatus());
						System.out.println(wniosek.getId());
						System.out.println(wniosek.getPetent().getDaneOsobowe().getImie());
					}
				}
			}
		}
	}


	public void usunZRejestru(Petent petent) {
		rejestr.remove(petent);
		System.out.println("Petent zostal pomyslnie usuniety");
	}


	public void zmienDanePetenta(Petent petent) {
		for (Wniosek wniosek: wnioski) {
			if(wniosek.getPetent()==petent){
				if(wniosek.getTypWniosku()==TypWniosku.edycjaDanych){
					if(wniosek.getStatus()==StatusWniosku.zatwierdzony){
						petent.setDaneOsobowe(wniosek.getNoweDaneOsobowe());
						System.out.println("Pomyslnie zmieniono dane petenta w rejestrze :)");
					} else if (wniosek.getStatus()==StatusWniosku.odrzucony) {
						System.out.println("Nie udalo sie :(");
						System.out.println("Powod: " + wniosek.getKomentarzZwrotny());
					}else {
						System.out.println("Wniosek nadal w trakcie rozpatrywania");
					}
				}
			}
		}
	}

	public String wygenerujHaslo() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

		int passwordLength = 12;

		StringBuilder password = new StringBuilder(passwordLength);

		SecureRandom random = new SecureRandom();

		for (int i = 0; i < passwordLength; i++) {
			int randomIndex = random.nextInt(characters.length());
			char randomChar = characters.charAt(randomIndex);
			password.append(randomChar);
		}

		return password.toString();
	}

}