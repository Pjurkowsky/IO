package DEL;
import mockit.Expectations;
import mockit.VerificationsInOrder;
import mockit.Mocked;
import mockit.Mock;
import mockit.VerificationsInOrder.*;
import mockit.Verifications;
import mockit.Injectable;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import DEL.Petent;
import DEL.DaneAdresowe;
import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.PeselException;
import DEL.Exceptions.StringException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Tag("Entity")
public class aplikacjaTestMockit {

    static Aplikacja aplikacja;
    @BeforeAll
    public static void SetUp(){
        aplikacja = new Aplikacja();
    }
    @Mocked
    Petent petent;

    @Test
    public void wniosekOEdycjeTest() throws StringException, KodPocztowyException, PeselException{
        Wniosek wniosek1=null, wniosek2=null;
        DaneAdresowe daneAdresowe = new DaneAdresowe("Olbinska", "12", "Zbaszyn", "63-740");
        DaneLogowania daneLogowania = new DaneLogowania("nazwa", "haslo");
        DaneOsobowe daneOsobowe = new DaneOsobowe("Lukasz", "Marcin", "Wdowiak", false, "12345678901", LocalDate.parse("2002-12-12"), "Szczecin", daneAdresowe);

        Wniosek wnioski[]={wniosek1, wniosek2};

            for (int i=0; i < 2; i++) {
                wnioski[i] = aplikacja.zlozWniosekOEdycje(petent, daneOsobowe, null, null);
                if(i<2)
                    assertEquals(petent, wnioski[i].getPetent());
                else
                    assertEquals(petent, wnioski[i-1].getPetent());
            }


        /*new VerificationsInOrder() {
            {
                petent.equals(any);
                times = 1; // Specify the number of times the verification should occur
                // aplikacja.zlozWniosekOEdycje(withInstanceOf(Petent.class), withInstanceOf(DaneOsobowe.class), null, null);
               // maxTimes = 2;
            }
        };*/
    }


@Injectable
    Petent petent1, petent2;

    @Test
    public void wniosekOWpisTest(){
        Wniosek wniosek1 = null, wniosek2 = null;
        Petent petenci[] = {petent1, petent2};
        Wniosek wnioski[] = {wniosek1, wniosek2};

        for (int i = 0; i < 2; i++) {
            wnioski[i] = aplikacja.zlozWniosekOWpis(petenci[i], null, null);
            assertEquals(petenci[i], wnioski[i].getPetent());
        }


        /*new VerificationsInOrder() {
            {
                petent1.equals(any);
                times = 2;

                petent2.equals(any);
                times = 3;
            }
        };*/

    }
}





