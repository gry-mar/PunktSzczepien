package classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * class to map data about doctors in admin window
 * @author Martyna Grygiel
 * @version 1.0
 * @since 03.02.2022
 */
public class Lekarz {


    private IntegerProperty nrPwz = new SimpleIntegerProperty();
    private StringProperty imie = new SimpleStringProperty();
    private StringProperty nazwisko = new SimpleStringProperty();
    private StringProperty loginLekarz = new SimpleStringProperty();


    public Lekarz(IntegerProperty nrPwz, StringProperty imie, StringProperty nazwisko, StringProperty loginLekarz) {
        this.nrPwz = nrPwz;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.loginLekarz = loginLekarz;
    }

    public Lekarz() {
    }

    public int getNrPwz() {
        return nrPwz.get();
    }

    public IntegerProperty nrPwzProperty() {
        return nrPwz;
    }

    public void setNrPwz(int nrPwz) {
        this.nrPwz.set(nrPwz);
    }

    public String getImie() {
        return imie.get();
    }

    public StringProperty imieProperty() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public String getLoginLekarz() {
        return loginLekarz.get();
    }

    public StringProperty loginLekarzProperty() {
        return loginLekarz;
    }

    public void setLoginLekarz(String loginLekarz) {
        this.loginLekarz.set(loginLekarz);
    }
}
