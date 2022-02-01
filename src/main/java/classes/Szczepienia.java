package classes;

import javafx.beans.property.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Szczepienia {
//    private String idSzczepienia;
//    private LocalDate date;
//    private LocalDateTime time;
//    private int peselPacjent;
//    private int nrPwz;
//    private String idTyp;
//    private Status status;

    private StringProperty idSczepienia = new SimpleStringProperty();
    private ObjectProperty<Date> data = new SimpleObjectProperty<>();
    private ObjectProperty<Time> godzina = new SimpleObjectProperty<>();
    private IntegerProperty peselPacjent = new SimpleIntegerProperty();
    private IntegerProperty nrPwzLekarz = new SimpleIntegerProperty();
    private StringProperty idTyp = new SimpleStringProperty();
    private ObjectProperty<Status> status = new SimpleObjectProperty<>();





}
