package classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LekarzStatystyki {
    private StringProperty chorobaLekarzStatystyki = new SimpleStringProperty();
    private StringProperty nazwaLekarzStatystyki = new SimpleStringProperty();
    private IntegerProperty iloscWykonanychLekarzStatystyki = new SimpleIntegerProperty();

    public LekarzStatystyki() {
    }

    public String getChorobaLekarzStatystyki() {
        return chorobaLekarzStatystyki.get();
    }

    public StringProperty chorobaLekarzStatystykiProperty() {
        return chorobaLekarzStatystyki;
    }

    public void setChorobaLekarzStatystyki(String chorobaLekarzStatystyki) {
        this.chorobaLekarzStatystyki.set(chorobaLekarzStatystyki);
    }

    public Integer getIloscWykonanychLekarzStatystyki() {
        return iloscWykonanychLekarzStatystyki.get();
    }

    public IntegerProperty iloscWykonanychLekarzStatystykiProperty() {
        return iloscWykonanychLekarzStatystyki;
    }

    public void setIloscWykonanychLekarzStatystyki(Integer iloscWykonanychLekarzStatystyki) {
        this.iloscWykonanychLekarzStatystyki.set(iloscWykonanychLekarzStatystyki);
    }

    public String getNazwaLekarzStatystyki() {
        return nazwaLekarzStatystyki.get();
    }

    public StringProperty nazwaLekarzStatystykiProperty() {
        return nazwaLekarzStatystyki;
    }

    public void setNazwaLekarzStatystyki(String nazwaLekarzStatystyki) {
        this.nazwaLekarzStatystyki.set(nazwaLekarzStatystyki);
    }
}
