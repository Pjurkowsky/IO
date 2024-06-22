package DEL;

import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.PeselException;
import DEL.Exceptions.StringException;
import fit.ColumnFixture;

import java.util.IllegalFormatCodePointException;
import java.util.Set;

public class TestDodajPetenta extends ColumnFixture {
String dane[];

public boolean dodajPetenta(){
    int liczbaPetentowPrzed = liczbaPetentow();
    try {
        String [] danePetenta = {"Lukasz", "Marcin", "Wdowiak", "false", "12345678901", "2002-12-12", "Szczecin"};
        String [] daneAdresowe = {"Urzednicza", "2", "Torun", "21-370"};
       DaneOsobowe daneOsobowe =  new DaneOsobowe(danePetenta, daneAdresowe);

       for(int i = 0; i < Integer.parseInt(dane[0]); i++) {
           SetUp.aplikacja.dodajPetenta(daneOsobowe, null, null);
       }


    return liczbaPetentowPrzed!= liczbaPetentow();

    } catch (StringException e) {
        throw new RuntimeException(e);
    } catch (KodPocztowyException e) {
        throw new RuntimeException(e);
    } catch (PeselException e) {
        throw new RuntimeException(e);

    } catch (IllegalFormatCodePointException e) {

    };

    return false;
}

public int liczbaPetentow(){
    return SetUp.aplikacja.getPetenci().size();
}

public int liczbaWnioskow() {
    return SetUp.aplikacja.getWnioski().size();
}


}



