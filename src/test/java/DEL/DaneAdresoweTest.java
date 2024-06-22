package DEL;

import DEL.DaneAdresowe;
import DEL.DaneOsobowe;
import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.StringException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("Entity")
public class DaneAdresoweTest{
    @Order(2)
    @Tag("Omin")
    @Test
    public void testUlicaIfNullOrEmpty(){

        StringException thrown = assertThrows(StringException.class, ()->{
            new DaneAdresowe(null,
                    "2",
                    "Torun",
                    "21-370");
        });
        assertEquals("Nazwa ulicy nie moze byc pusta", thrown.getMessage());}

    @Order(1)
    @Tag("Omin")
    @Test
    public void testMiastoIfNullOrEmpty() {
        StringException thrown = assertThrows(StringException.class, ()->{
            new DaneAdresowe("Olbinska",
                    "2",
                    "",
                    "21-370");
        });
        assertEquals("Nazwa miasta nie moze byc pusta", thrown.getMessage());
    }
    @Order(0)
    @Test
    public void testKodPocztowyIfNullOrEmpty() {
        KodPocztowyException thrown = assertThrows(KodPocztowyException.class, ()->{
            new DaneAdresowe("Da",
                    "2",
                    "Asd",
                    "");
        });
        assertEquals("Kod pocztowy nie moze byc pusty", thrown.getMessage());
    }
    @Order(3)
    @Test
    public void testKodPocztowyIfCorrect() {
        KodPocztowyException thrown = assertThrows(KodPocztowyException.class, ()->{
            new DaneAdresowe("Da",
                    "2",
                    "Dd",
                    "21370");
        });
        assertEquals("Kod pocztowy musi byc w poprawnym formacie xx-xxx", thrown.getMessage());
    }
    @Order(4)
    @Test
    public void testUlicaIfItContainsNumbersOrSpecialChars() {
        StringException thrown = assertThrows(StringException.class, ()->{
            new DaneAdresowe("Ciul2",
                    "2",
                    "Torun",
                    "21-370");
        });
        assertEquals("Nazwa ulicy nie moze zawierac znakow specjalnych lub liczb", thrown.getMessage());
    }
    @Order(5)
    @Test
    public void testMiastoIfItContainsNumbersOrSpecialChars() {
        StringException thrown = assertThrows(StringException.class, ()->{
            new DaneAdresowe("Ciul",
                    "2",
                    "Tor32un",
                    "21-370");
        });
        assertEquals("Nazwa miasta nie moze zawierac znakow specjalnych lub liczb", thrown.getMessage());
    }
}
