package classes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.sql.Time;

public class RealizacjaPacjent {
    private StringProperty nazwaRelizacja = new SimpleStringProperty();
    private StringProperty chorobaRealizacja = new SimpleStringProperty();
    private ObjectProperty<Date> dataRalizacja = new SimpleObjectProperty<>();
    private ObjectProperty<Time> godzinaRealizacja = new SimpleObjectProperty<>();


    public RealizacjaPacjent() {
    }

    public String getNazwaRelizacja() {
        return nazwaRelizacja.get();
    }

    public StringProperty nazwaRelizacjaProperty() {
        return nazwaRelizacja;
    }

    public void setNazwaRelizacja(String nazwaRelizacja) {
        this.nazwaRelizacja.set(nazwaRelizacja);
    }

    public String getChorobaRealizacja() {
        return chorobaRealizacja.get();
    }

    public StringProperty chorobaRealizacjaProperty() {
        return chorobaRealizacja;
    }

    public void setChorobaRealizacja(String chorobaRealizacja) {
        this.chorobaRealizacja.set(chorobaRealizacja);
    }

    public Date getDataRalizacja() {
        return dataRalizacja.get();
    }

    public ObjectProperty<Date> dataRalizacjaProperty() {
        return dataRalizacja;
    }

    public void setDataRalizacja(Date dataRalizacja) {
        this.dataRalizacja.set(dataRalizacja);
    }

    public Time getGodzinaRealizacja() {
        return godzinaRealizacja.get();
    }

    public ObjectProperty<Time> godzinaRealizacjaProperty() {
        return godzinaRealizacja;
    }

    public void setGodzinaRealizacja(Time godzinaRealizacja) {
        this.godzinaRealizacja.set(godzinaRealizacja);
    }
}
