package DEL;

import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.PeselException;
import DEL.Exceptions.StringException;
import fit.ColumnFixture;

import java.util.IllegalFormatCodePointException;
import java.util.Set;

public class TestDodajPracownika extends ColumnFixture {
    String dane[];

    public boolean dodajPracownika(){
        int liczbaPracownikowPrzed = liczbaPracownikow();
        try {

            for(int i = 0; i < Integer.parseInt(dane[0]); i++) {
                SetUp.aplikacja.dodajPracownika();
            }


            return liczbaPracownikowPrzed != liczbaPracownikow();

        } catch (IllegalFormatCodePointException e) {

        };

        return false;
    }

    public int liczbaPracownikow(){
        return SetUp.aplikacja.getPracownicy().size();
    }
}


