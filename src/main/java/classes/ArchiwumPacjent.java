package classes;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


import java.sql.Date;
import java.sql.Time;


public class ArchiwumPacjent {
    /**
     * class to map data from archiwum_pacjent view
     * @author Zofia Dobrowolska
     * @version 1.0
     * @since 03.02.2022
     */
    private StringProperty nazwa = new SimpleStringProperty();
    private StringProperty choroba = new SimpleStringProperty();
    private ObjectProperty<Date> data = new SimpleObjectProperty<>();
    private ObjectProperty<Time> godzina = new SimpleObjectProperty<>();

    public ArchiwumPacjent() {
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public String getChoroba() {
        return choroba.get();
    }

    public StringProperty chorobaProperty() {
        return choroba;
    }

    public void setChoroba(String choroba) {
        this.choroba.set(choroba);
    }

    public Date getData() {
        return data.get();
    }

    public ObjectProperty<Date> dataProperty() {
        return data;
    }

    public void setData(Date data) {
        this.data.set(data);
    }

    public Time getGodzina() {
        return godzina.get();
    }

    public ObjectProperty<Time> godzinaProperty() {
        return godzina;
    }

    public void setGodzina(Time godzina) {
        this.godzina.set(godzina);
    }

}
