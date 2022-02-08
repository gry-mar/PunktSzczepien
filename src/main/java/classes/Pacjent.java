package classes;

import javafx.beans.property.*;
/**
 * class to map data from pacjenci table
 * @author Zofia Dobrowolska
 * @version 1.0
 * @since 03.02.2022
 */
public class Pacjent {

    private StringProperty pesel;
    private StringProperty imie;
    private StringProperty nazwisko;
    private StringProperty telefon;
    private StringProperty loginPac;


    public Pacjent() {
        this.pesel = new SimpleStringProperty();
        this.imie = new SimpleStringProperty();
        this.nazwisko = new SimpleStringProperty();
        this.telefon = new SimpleStringProperty();
        this.loginPac = new SimpleStringProperty();
    }

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
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

    public String getTelefon() {
        return telefon.get();
    }

    public StringProperty telefonProperty() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }

    public String getLoginPac() {
        return loginPac.get();
    }

    public StringProperty loginPacProperty() {
        return loginPac;
    }

    public void setLoginPac(String loginPac) {
        this.loginPac.set(loginPac);
    }
}
