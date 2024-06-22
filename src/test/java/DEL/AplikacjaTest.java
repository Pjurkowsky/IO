package DEL;

import DEL.*;
import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.PeselException;
import DEL.Exceptions.StringException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@Tag("Entity")
public class AplikacjaTest{

    static Aplikacja aplikacja;
    static Dane dane;
    @BeforeAll
    public static void SetUp(){
        dane = new Dane();
        aplikacja = new Aplikacja();
    }

    static Stream<Integer> intProvider() {
        return  Stream.of(1,2,3,4,5);
    }

    @ParameterizedTest
    @MethodSource("intProvider")
    public void testDodajPetenta(int i) throws StringException, KodPocztowyException, PeselException {
        for (int j = 0; j < i ; j++) {
            aplikacja.dodajPetenta(new DaneOsobowe(dane.daneOsobowe[0], dane.daneAdresowe[0]), null, null);
        }

        assertEquals(i, aplikacja.getPetenci().size());
        assertEquals(i, aplikacja.getWnioski().size());

        aplikacja.getPetenci().clear();
        aplikacja.getWnioski().clear();

    }

    @ParameterizedTest
    @CsvSource({"1","2","3","4","5"})
    public void testDodajPracownika(int i) throws StringException, KodPocztowyException, PeselException {
        for (int j = 0; j < i ; j++) {
            aplikacja.dodajPracownika();
        }

        assertEquals(i, aplikacja.getPracownicy().size());

        aplikacja.getPracownicy().clear();
    }

    @Test
    public void testRozpatrzWniosek() throws StringException, KodPocztowyException, PeselException {
        aplikacja.dodajPracownika();
        aplikacja.dodajPetenta(new DaneOsobowe(dane.daneOsobowe[0], dane.daneAdresowe[0]), null, null);
        Petent petent = aplikacja.getPetenci().get(0);

        Wniosek wniosek = new Wniosek(petent, LocalDateTime.now(), TypWniosku.wpisDoRejestru, null, null);

        aplikacja.przydzielPracownika(wniosek);

        assertEquals(StatusWniosku.rozpatrywany, wniosek.getStatus());

        aplikacja.rozpatrzWniosek(wniosek);

        assertEquals(StatusWniosku.zatwierdzony, wniosek.getStatus());
    }

    @Test
    public void testOdrzucWniosek() throws StringException, KodPocztowyException, PeselException {
        aplikacja.dodajPracownika();
        aplikacja.dodajPetenta(new DaneOsobowe(dane.daneOsobowe[0], dane.daneAdresowe[0]), null, null);
        Petent petent = aplikacja.getPetenci().get(0);

        Wniosek wniosek = new Wniosek(petent, LocalDateTime.now(), TypWniosku.wpisDoRejestru, null, null);

        aplikacja.przydzielPracownika(wniosek);

        assertEquals(StatusWniosku.rozpatrywany, wniosek.getStatus());

        aplikacja.odrzucWniosek(wniosek);

        assertEquals(StatusWniosku.odrzucony, wniosek.getStatus());

    }

}
