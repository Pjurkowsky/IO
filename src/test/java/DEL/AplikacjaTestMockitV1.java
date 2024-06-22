package DEL;

import DEL.*;
import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.PeselException;
import DEL.Exceptions.StringException;
import mockit.VerificationsInOrder;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Entity")
public class AplikacjaTestMockitV1 {

    static Aplikacja aplikacja;
    @BeforeAll
    public static void SetUp(){
        aplikacja = new Aplikacja();
    }

    @Injectable Pracownik mockedPracownik;
    @Mocked Wniosek anyWniosek;


    @Test
    public void testRozpatrzWniosek() throws StringException, KodPocztowyException, PeselException {
        aplikacja.dodajPracownika();
        aplikacja.dodajPetenta(new DaneOsobowe("Test", "Test", "Test", true, "12345678901",
                        LocalDate.of(2000, 1, 1), "TestCity", new DaneAdresowe("Teststreet", "1", "Testcity", "12-345")),
                new Certyfikat(1, TypCertyfikatu.certyfikatUrodzenia, LocalDate.now()), null);
        aplikacja.rozpatrzWniosek(anyWniosek);
        new Expectations() {{
            anyWniosek.getPracownik(); result = mockedPracownik;

            anyWniosek.getStatus(); result = StatusWniosku.zatwierdzony;

            //anyWniosek.getId(); result = 1;

            //mockedPracownik.getId(); result = 1;
        }};

        aplikacja.rozpatrzWniosek(anyWniosek);

        assertEquals(StatusWniosku.zatwierdzony, anyWniosek.getStatus());
        assertFalse(mockedPracownik.getStanPracy());

        new VerificationsInOrder() {
            {
                anyWniosek.getPracownik(); maxTimes = 1;

                anyWniosek.getStatus(); maxTimes = 1;

                //anyWniosek.getId(); maxTimes = 1;

                //mockedPracownik.getId(); maxTimes = 1;
            }
        };


    }


    @Test
    public void testOdrzucWniosek() throws StringException, KodPocztowyException, PeselException {
        aplikacja.dodajPracownika();
        aplikacja.dodajPetenta(new DaneOsobowe("Test", "Test", "Test", true, "12345678901",
                        LocalDate.of(2000, 1, 1), "TestCity", new DaneAdresowe("Teststreet", "1", "Testcity", "12-345")),
                new Certyfikat(1, TypCertyfikatu.certyfikatUrodzenia, LocalDate.now()), null);
        aplikacja.odrzucWniosek(anyWniosek);

        new Expectations() {{
            anyWniosek.getPracownik(); result = mockedPracownik;

            anyWniosek.getStatus(); result = StatusWniosku.odrzucony;

            //anyWniosek.getId(); result = 1;

            //mockedPracownik.getId(); result = 1;
        }};

        aplikacja.rozpatrzWniosek(anyWniosek);

        assertEquals(StatusWniosku.odrzucony, anyWniosek.getStatus());
        assertFalse(mockedPracownik.getStanPracy());

        new VerificationsInOrder() {
            {
                anyWniosek.getPracownik(); maxTimes = 1;

                anyWniosek.getStatus(); maxTimes = 1;

                //anyWniosek.getId(); maxTimes = 1;

                //mockedPracownik.getId(); maxTimes = 1;
            }
        };

    }

    @Injectable
    DaneOsobowe daneOsobowe;
    @Mocked
    Petent petent;

    @Test
    public void testDodajPetenta() throws StringException, KodPocztowyException, PeselException {
        aplikacja.dodajPetenta(daneOsobowe,  new Certyfikat(1, TypCertyfikatu.certyfikatUrodzenia, LocalDate.now()), null);
        new Expectations() {
            {
                aplikacja.getPetenci().size(); result = 1;
                petent.getDaneOsobowe(); result = daneOsobowe;

            }
        };

        assertEquals(1,aplikacja.getPetenci().size());
        assertEquals(daneOsobowe,petent.getDaneOsobowe());

    }


    @Mocked
    Pracownik pracownik;

    @Test
    public void testDodajPracownika() throws StringException, KodPocztowyException, PeselException {
        aplikacja.dodajPracownika();
        new Expectations() {
            {
                aplikacja.getPracownicy().size(); result = 1;
                pracownik.getId(); result = 0;;
            }
        };
        assertEquals(1,aplikacja.getPracownicy().size());
        assertEquals(0, pracownik.getId());

    }

}