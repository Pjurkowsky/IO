package DEL;

import DEL.DaneAdresowe;
import DEL.DaneOsobowe;
import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.PeselException;
import DEL.Exceptions.StringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import DEL.Dane;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@Tag("Entity")
public class DaneOsoboweTest {
    Dane dane;
    DaneOsobowe daneOsobowe;

    @BeforeEach
    public void setUp(){
        dane= new Dane(); }

    @Test
    public void testSetPeselIfContainsNotOnlyNumbers(){
        PeselException thrown = assertThrows(PeselException.class, ()->{
            new DaneOsobowe(dane.daneOsobowe[6],dane.daneAdresowe[0]);
        });

        assertEquals("Pesel contains not only numbers", thrown.getMessage());

    }
    @Test
    public void testSetPeselIfItsTooLong(){
        PeselException thrown = assertThrows(PeselException.class, ()->{
            new DaneOsobowe(dane.daneOsobowe[7],dane.daneAdresowe[0]);
        });

        assertEquals("Pesel is too long", thrown.getMessage());
    }

    @Test
    public void testSetImieIfItContainsNumbersOrSpecialChars() {

        StringException thrown = assertThrows(StringException.class, ()->{
            new DaneOsobowe(dane.daneOsobowe[2],dane.daneAdresowe[0]);
        });
        assertEquals("Imie zawiera cyfry badz znaki specjalne", thrown.getMessage());
    }


    @Test
    public void testSetDrugieImieIfItContainsNumbersOrSpecialChars() {

        StringException thrown = assertThrows(StringException.class, ()->{
            new DaneOsobowe(dane.daneOsobowe[3],dane.daneAdresowe[0]);
        });
        assertEquals("Drugie Imie zawiera cyfry badz znaki specjalne", thrown.getMessage());
    }


    @Test
    public void testSetImieIfItsNullOrEmpty() {
        StringException thrown = assertThrows(StringException.class, ()->{
            new DaneOsobowe(dane.daneOsobowe[1],dane.daneAdresowe[0]);
        });
        assertEquals("Imie nie moze byc puste", thrown.getMessage());
    }


    @Test
    public void testGender() throws StringException, PeselException, KodPocztowyException {
        DaneOsobowe daneOsobowe = new DaneOsobowe(dane.daneOsobowe[0],dane.daneAdresowe[0]);
        assertFalse(daneOsobowe.getPlec());
    }

    @Test
    public void testSetNazwiskoIfItContainsNumbersOrSpecialChars() {

        StringException thrown = assertThrows(StringException.class, ()->{
            new DaneOsobowe(dane.daneOsobowe[5],dane.daneAdresowe[0]);
        });
        assertEquals("Nazwisko zawiera cyfry badz znaki specjalne", thrown.getMessage());
    }

    @Test
    public void testSetNazwiskoIfItsNullOrEmpty() {
        StringException thrown = assertThrows(StringException.class, ()->{
            new DaneOsobowe(dane.daneOsobowe[4],dane.daneAdresowe[0]);
        });
        assertEquals("Nazwisko nie moze byc puste", thrown.getMessage());
    }

}
