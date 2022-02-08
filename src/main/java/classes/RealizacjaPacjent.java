package classes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.sql.Time;
/**
 * class to map data from view realizacja_pacjent to GUI table and procedures in PacjentDAO
 * @author Martyna Grygiel
 * @version 1.0
 * @since 03.02.2022
 */
public class RealizacjaPacjent {

    private StringProperty nazwaRealizacja = new SimpleStringProperty();
    private StringProperty chorobaRealizacja = new SimpleStringProperty();
    private ObjectProperty<Date> dataRealizacja = new SimpleObjectProperty<>();
    private ObjectProperty<Time> godzinaRealizacja = new SimpleObjectProperty<>();


    public RealizacjaPacjent() {
    }

    public String getNazwaRealizacja() {
        return nazwaRealizacja.get();
    }

    public StringProperty nazwaRealizacjaProperty() {
        return nazwaRealizacja;
    }

    public void setNazwaRealizacja(String nazwaRelizacja) {
        this.nazwaRealizacja.set(nazwaRelizacja);
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

    public Date getDataRealizacja() {
        return dataRealizacja.get();
    }

    public ObjectProperty<Date> dataRealizacjaProperty() {
        return dataRealizacja;
    }

    public void setDataRealizacja(Date dataRealizacja) {
        this.dataRealizacja.set(dataRealizacja);
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
