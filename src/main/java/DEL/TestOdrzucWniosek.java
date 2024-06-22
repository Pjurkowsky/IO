package DEL;

import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.PeselException;
import DEL.Exceptions.StringException;
import fit.ColumnFixture;

import java.time.LocalDateTime;

public class TestOdrzucWniosek  extends ColumnFixture {
    String[] dane;

    public String OdrzucWniosek() throws StringException, KodPocztowyException, PeselException {

        SetUp.aplikacja.dodajPracownika();

        DaneOsobowe daneOsobowe = new DaneOsobowe(dane[0], dane[1], dane[2],dane[3],dane[4],dane[5],dane[6], dane[7], dane[8], dane[9]);

        SetUp.aplikacja.dodajPetenta(daneOsobowe,null,null);

        Wniosek wniosek = new Wniosek(SetUp.aplikacja.getPetenci().get(0), LocalDateTime.now(), TypWniosku.wpisDoRejestru, null,null);

        SetUp.aplikacja.przydzielPracownika(wniosek);

        SetUp.aplikacja.odrzucWniosek(wniosek);

        return wniosek.getStatus().toString();
    }
    public boolean getStanPracy() {
        return SetUp.aplikacja.getPracownicy().get(0).getStanPracy();
    }
    public int getResidents() {
        return SetUp.aplikacja.getRejestr().size();
    }
}
