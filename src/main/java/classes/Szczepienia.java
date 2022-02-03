package classes;

import com.mysql.cj.conf.EnumProperty;
import javafx.beans.property.*;

import java.sql.Date;
import java.sql.Time;


public class Szczepienia {

    private ObjectProperty<Date> data = new SimpleObjectProperty<>();
    private ObjectProperty<Time> godzina = new SimpleObjectProperty<>();
    private StringProperty peselPacjent = new SimpleStringProperty();
    private IntegerProperty nrPwzLekarz = new SimpleIntegerProperty();
    private StringProperty idTyp = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();

    public Szczepienia() {
    }

    public Szczepienia(ObjectProperty<Date> data, ObjectProperty<Time> godzina, StringProperty peselPacjent,
                       IntegerProperty nrPwzLekarz, StringProperty idTyp, StringProperty status) {
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

    public String getPeselPacjent() {
        return peselPacjent.get();
    }

    public StringProperty peselPacjentProperty() {
        return peselPacjent;
    }

    public void setPeselPacjent(String peselPacjent) {
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

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
