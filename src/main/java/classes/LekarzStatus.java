package classes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.sql.Time;

public class LekarzStatus {
    private StringProperty peselLekarzStatus = new SimpleStringProperty();
    private StringProperty nazwaLekarzStatus = new SimpleStringProperty();
    private StringProperty chorobaLekarzStatus= new SimpleStringProperty();
    private ObjectProperty<Date> dataLekarzRealizacja = new SimpleObjectProperty<>();
    private ObjectProperty<Time> godzinaLekarzRealizacja = new SimpleObjectProperty<>();

    public LekarzStatus(){

    }

    public String getPeselLekarzStatus() {
        return peselLekarzStatus.get();
    }

    public StringProperty peselLekarzStatusProperty(String pesel) {
        return peselLekarzStatus;
    }

    public void setPeselLekarzStatus(String peselLekarzStatus) {
        this.peselLekarzStatus.set(peselLekarzStatus);
    }

    public String getNazwaLekarzStatus() {
        return nazwaLekarzStatus.get();
    }

    public StringProperty nazwaLekarzStatusProperty(String nazwa) {
        return nazwaLekarzStatus;
    }

    public void setNazwaLekarzStatus(String nazwaLekarzStatus) {
        this.nazwaLekarzStatus.set(nazwaLekarzStatus);
    }

    public String getChorobaLekarzStatus(Time godzina) {
        return chorobaLekarzStatus.get();
    }

    public StringProperty chorobaLekarzStatusProperty(String choroba) {
        return chorobaLekarzStatus;
    }

    public void setChorobaLekarzStatus(String chorobaLekarzStatus) {
        this.chorobaLekarzStatus.set(chorobaLekarzStatus);
    }

    public Date getDataLekarzRealizacja() {
        return dataLekarzRealizacja.get();
    }

    public ObjectProperty<Date> dataLekarzRealizacjaProperty(Date data) {
        return dataLekarzRealizacja;
    }

    public void setDataLekarzRealizacja(Date dataLekarzRealizacja) {
        this.dataLekarzRealizacja.set(dataLekarzRealizacja);
    }

    public Time getGodzinaLekarzRealizacja(Time godzinaLekarzRealizacja) {
        return this.godzinaLekarzRealizacja.get();
    }

    public ObjectProperty<Time> godzinaLekarzRealizacjaProperty() {
        return godzinaLekarzRealizacja;
    }

    public void setGodzinaLekarzRealizacja(Time godzinaLekarzRealizacja) {
        this.godzinaLekarzRealizacja.set(godzinaLekarzRealizacja);
    }
}
