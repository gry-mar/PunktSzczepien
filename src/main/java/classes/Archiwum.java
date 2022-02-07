package classes;
import com.mysql.cj.conf.EnumProperty;
import javafx.beans.property.*;

import java.sql.Time;
import java.util.Date;

public class Archiwum {

    /**
     * class to map data from archiwum table
     * @author Martyna Grygiel, Zofia Dobrowolska
     * @version 1.0
     * @since 03.02.2022
     */
    private StringProperty idSzczepienia;
    private ObjectProperty<Date> date;
    private ObjectProperty<Time> godzina;
    private StringProperty peselPacjent;
    private IntegerProperty lekarzNrPOZ;
    private EnumProperty status;
    private StringProperty idTyp;

    public Archiwum(StringProperty idSzczepienia, ObjectProperty<Date> date, ObjectProperty<Time> godzina, StringProperty peselPacjent,
                    IntegerProperty lekarzNrPOZ, EnumProperty status, StringProperty idTyp) {
        this.idSzczepienia = idSzczepienia;
        this.date = date;
        this.godzina = godzina;
        this.peselPacjent = peselPacjent;
        this.lekarzNrPOZ = lekarzNrPOZ;
        this.status = status;
        this.idTyp = idTyp;
    }

    public String getIdSzczepienia() {
        return idSzczepienia.get();
    }

    public StringProperty idSzczepieniaProperty() {
        return idSzczepienia;
    }

    public void setIdSzczepienia(String idSzczepienia) {
        this.idSzczepienia.set(idSzczepienia);
    }

    public ObjectProperty<Date> getDate() {
        return date;
    }

    public void setDate(ObjectProperty<Date> date) {
        this.date = date;
    }

    public ObjectProperty<Time> getGodzina() {
        return godzina;
    }

    public void setGodzina(ObjectProperty<Time> godzina) {
        this.godzina = godzina;
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

    public int getLekarzNrPOZ() {
        return lekarzNrPOZ.get();
    }

    public IntegerProperty lekarzNrPOZProperty() {
        return lekarzNrPOZ;
    }

    public void setLekarzNrPOZ(int lekarzNrPOZ) {
        this.lekarzNrPOZ.set(lekarzNrPOZ);
    }

    public EnumProperty getStatus() {
        return status;
    }

    public void setStatus(EnumProperty status) {
        this.status = status;
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
}
