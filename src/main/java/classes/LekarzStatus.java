package classes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.sql.Time;

public class LekarzStatus {
    /**
     * class to map data to GUI in lekrzWindowController
     * @author Zofia Dobrowolska
     * @version 1.0
     * @since 03.02.2022
     */
    private StringProperty peselLekarzStatus = new SimpleStringProperty();
    private StringProperty nazwaLekarzStatus = new SimpleStringProperty();
    private StringProperty chorobaLekarzStatus= new SimpleStringProperty();
    private ObjectProperty<Date> dataLekarzStatus = new SimpleObjectProperty<>();
    private ObjectProperty<Time> godzinaLekarzStatus = new SimpleObjectProperty<>();

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

    public String getChorobaLekarzStatus() {
        return chorobaLekarzStatus.get();
    }

    public StringProperty chorobaLekarzStatusProperty(String choroba) {
        return chorobaLekarzStatus;
    }

    public void setChorobaLekarzStatus(String chorobaLekarzStatus) {
        this.chorobaLekarzStatus.set(chorobaLekarzStatus);
    }

    public Date getDataLekarzStatus() {
        return dataLekarzStatus.get();
    }

    public ObjectProperty<Date> dataLekarzStatusProperty(Date data) {
        return dataLekarzStatus;
    }

    public void setDataLekarzStatus(Date dataLekarzStatus) {
        this.dataLekarzStatus.set(dataLekarzStatus);
    }

    public Time getGodzinaLekarzStatus() {
        return godzinaLekarzStatus.get();
    }

    public ObjectProperty<Time> godzinaLekarzStatusProperty(Time godzina) {
        return godzinaLekarzStatus;
    }

    public void setGodzinaLekarzStatus(Time godzinaLekarzStatus) {
        this.godzinaLekarzStatus.set(godzinaLekarzStatus);
    }

    public void getGodzinaLekarzStatus(Time godzina)  {
        this.godzinaLekarzStatus.set(godzina);
    }
}
