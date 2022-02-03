package classes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.sql.Time;

public class LekarzArchiwum {
    private StringProperty nazwaLekarzArchiwum = new SimpleStringProperty();
    private StringProperty chorobaLekarzArchiwum = new SimpleStringProperty();
    private ObjectProperty<Date> dataLekarzArchiwum = new SimpleObjectProperty<>();
    private ObjectProperty<Time> godzinaLekarzArchiwum = new SimpleObjectProperty<>();

    public LekarzArchiwum() {

    }

    public String getNazwaLekarzArchiwum() {
        return nazwaLekarzArchiwum.get();
    }

    public StringProperty nazwaLekarzArchiwumProperty(String nazwa) {
        return nazwaLekarzArchiwum;
    }

    public void setNazwaLekarzArchiwum(String nazwaLekarzArchiwum) {
        this.nazwaLekarzArchiwum.set(nazwaLekarzArchiwum);
    }

    public String getChorobaLekarzArchiwum() {
        return chorobaLekarzArchiwum.get();
    }

    public StringProperty chorobaLekarzArchiwumProperty(String choroba) {
        return chorobaLekarzArchiwum;
    }

    public void setChorobaLekarzArchiwum(String chorobaLekarzArchiwum) {
        this.chorobaLekarzArchiwum.set(chorobaLekarzArchiwum);
    }

    public Date getDataLekarzArchiwum() {
        return dataLekarzArchiwum.get();
    }

    public ObjectProperty<Date> dataLekarzArchiwumProperty(Date data) {
        return dataLekarzArchiwum;
    }

    public void setDataLekarzArchiwum(Date dataLekarzArchiwum) {
        this.dataLekarzArchiwum.set(dataLekarzArchiwum);
    }

    public Time getGodzinaLekarzArchiwum() {
        return godzinaLekarzArchiwum.get();
    }

    public ObjectProperty<Time> godzinaLekarzArchiwumProperty() {
        return godzinaLekarzArchiwum;
    }

    public void setGodzinaLekarzArchiwum(Time godzinaLekarzArchiwum) {
        this.godzinaLekarzArchiwum.set(godzinaLekarzArchiwum);
    }
    public void getGodzinaLekarzArchiwum(Time godzina)  {
        this.godzinaLekarzArchiwum.set(godzina);
    }
}
