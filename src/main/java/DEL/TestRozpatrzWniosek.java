package DEL;

import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.PeselException;
import DEL.Exceptions.StringException;
import fit.ColumnFixture;

import java.time.LocalDateTime;
import java.util.Set;

public class TestRozpatrzWniosek extends ColumnFixture {

    String dane[];
    public String rozpatrzWniosek() throws StringException, KodPocztowyException, PeselException {
        SetUp.aplikacja.dodajPracownika();

        DaneOsobowe daneOsobowe = new DaneOsobowe(dane[0], dane[1], dane[2],dane[3],dane[4],dane[5],dane[6], dane[7], dane[8], dane[9]);

        SetUp.aplikacja.dodajPetenta(daneOsobowe,null,null);

      Wniosek wniosek = SetUp.aplikacja.getWnioski().get(0);

        SetUp.aplikacja.przydzielPracownika(wniosek);

        SetUp.aplikacja.rozpatrzWniosek(wniosek);


        System.out.println(wniosek.getId());
        System.out.println(wniosek.getTypWniosku());
        System.out.println(wniosek.getStatus());
        System.out.println(SetUp.aplikacja.getRejestr().size());


        return wniosek.getStatus().toString();

    }

    public boolean getStanPracy() {
        return SetUp.aplikacja.getPracownicy().get(0).getStanPracy();
    }

    public int getResidents() {

        int size = SetUp.aplikacja.getRejestr().size();
        SetUp.aplikacja.getRejestr().clear();
        return size;
    }

}
