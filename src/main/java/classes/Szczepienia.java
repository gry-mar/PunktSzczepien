package classes;

import com.mysql.cj.conf.EnumProperty;
import javafx.beans.property.*;

import java.sql.Date;
import java.sql.Time;


public class Szczepienia {

    private ObjectProperty<Date> data = new SimpleObjectProperty<>();
    private ObjectProperty<Time> godzina = new SimpleObjectProperty<>();
    private IntegerProperty peselPacjent = new SimpleIntegerProperty();
    private IntegerProperty nrPwzLekarz = new SimpleIntegerProperty();
    private StringProperty idTyp = new SimpleStringProperty();
    private ObjectProperty<Status> status = new SimpleObjectProperty<>();

    public Szczepienia() {
    }

    public Szczepienia(ObjectProperty<Date> data, ObjectProperty<Time> godzina, IntegerProperty peselPacjent, IntegerProperty nrPwzLekarz, StringProperty idTyp, ObjectProperty<Status> status) {

        this.data = data;
        this.godzina = godzina;
        this.peselPacjent = peselPacjent;
        this.nrPwzLekarz = nrPwzLekarz;
        this.idTyp = idTyp;
        this.status = status;
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

    public int getPeselPacjent() {
        return peselPacjent.get();
    }

    public IntegerProperty peselPacjentProperty() {
        return peselPacjent;
    }

    public void setPeselPacjent(int peselPacjent) {
        this.peselPacjent.set(peselPacjent);
    }

    public int getNrPwzLekarz() {
        return nrPwzLekarz.get();
    }

    public IntegerProperty nrPwzLekarzProperty() {
        return nrPwzLekarz;
    }

    public void setNrPwzLekarz(int nrPwzLekarz) {
        this.nrPwzLekarz.set(nrPwzLekarz);
    }

    public String getIdTyp() {
        return idTyp.get();
    }

    public StringProperty idTypProperty() {
        return idTyp;
    }

    public void setIdTyp(String idTyp) {
        this.idTyp.set(idTyp);
    }

    public Status getStatus() {
        return status.get();
    }

    public ObjectProperty<Status> statusProperty() {
        return status;
    }

    public void setStatus(Status status) {
        this.status.set(status);
    }
}
