package DEL;

import DEL.Exceptions.KodPocztowyException;
import DEL.Exceptions.PeselException;
import DEL.Exceptions.StringException;

import java.time.LocalDate;

public class Dane {


    public String [][] daneAdresowe = new String[][] {  {"Olbinska", "12", "Zbaszyn", "63-740"},
                                                        {"", "12", "Szczecin", "63-700"},
                                                        {"Olbi342nska", "12", "Zbaszyn", "63-740"},
                                                        {"Olbinska", "12", "", "63-740"},
                                                        {"Olbinska", "12", "Zbas342zyn", "63-740"},
                                                        {"Olbinska", "12", "Zbaszyn", ""},
                                                        {"Olbinska", "12", "Zbaszyn", "638740"}};
    public String [][] daneOsobowe = new String[][] {   {"Lukasz", "Marcin", "Wdowiak", "false", "12345678901", "2002-12-12", "Szczecin"},
                                                        {"", "Marcin", "Wdowiak", "false", "12345678901", "2002-12-12", "Szczecin"},
                                                        {"Luk342423asz", "Marcin", "Wdowiak", "false", "12345678901", "2002-12-12", "Szczecin"},
                                                        {"Lukasz", "Mar243423cin", "Wdowiak", "false", "12345678901", "2002-12-12", "Szczecin"},
                                                        {"Lukasz", "Marcin", "", "false", "12345678901", "2002-12-12", "Szczecin"},
                                                        {"Lukasz", "Marcin", "Wdowi342ak", "false", "12345678901", "2002-12-12", "Szczecin"},
                                                        {"Lukasz", "Marcin", "Wdowiak", "false", "1234fd78901", "2002-12-12", "Szczecin"},
                                                        {"Lukasz", "Marcin", "Wdowiak", "false", "123456788789978901", "2002-12-12", "Szczecin"},
                                                        {"Lukasz", "Marcin", "Wdowiak", "false", "12345678901", "2002-12-12", ""},
                                                        {"Lukasz", "Marcin", "Wdowiak", "false", "12345678901", "2002-12-12", "Sz342czecin"}};



}
