package classes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class DostepneSzczepienia {
    private StringProperty nazwaDostepne = new SimpleStringProperty();
    private StringProperty chorobaDostepne = new SimpleStringProperty();
    private ObjectProperty<Date> dataDostepne = new SimpleObjectProperty<>();
    private  ObjectProperty<Time> godzinaDostepne = new SimpleObjectProperty<>();

    public DostepneSzczepienia() {
    }

    public String getNazwaDostepne() {
        return nazwaDostepne.get();
    }

    public StringProperty nazwaDostepneProperty() {
        return nazwaDostepne;
    }

    public void setNazwaDostepne(String nazwaDostepne) {
        this.nazwaDostepne.set(nazwaDostepne);
    }

    public String getChorobaDostepne() {
        return chorobaDostepne.get();
    }

    public StringProperty chorobaDostepneProperty() {
        return chorobaDostepne;
    }

    public void setChorobaDostepne(String chorobaDostepne) {
        this.chorobaDostepne.set(chorobaDostepne);
    }

    public Date getDataDostepne() {
        return dataDostepne.get();
    }

    public ObjectProperty<Date> dataDostepneProperty() {
        return dataDostepne;
    }

    public void setDataDostepne(Date dataDostepne) {
        this.dataDostepne.set(dataDostepne);
    }

    public Time getGodzinaDostepne() {
        return godzinaDostepne.get();
    }

    public ObjectProperty<Time> godzinaDostepneProperty() {
        return godzinaDostepne;
    }

    public void setGodzinaDostepne(Time godzinaDostepne) {
        this.godzinaDostepne.set(godzinaDostepne);
    }




}
