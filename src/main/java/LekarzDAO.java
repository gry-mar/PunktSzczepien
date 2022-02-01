import classes.LekarzArchiwum;
import classes.LekarzStatus;
import classes.LekarzStatystyki;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LekarzDAO {
    private String userName;
    private String userPassword;
    private Integer nrPwz;
    private DatabaseConnection databaseConnection;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public Integer getNrPwz() {
        String selectStms = "select nr_pwz from lekarze where login_lec = '" + userName + "';";
        try{
            ResultSet resultSet = this.databaseConnection.dbExecuteQuery(selectStms);
            while(resultSet.next()){
                nrPwz = Integer.valueOf(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nrPwz;
    }

    public void setNrPwz(Integer nrPwz) {
        this.nrPwz = nrPwz;
    }

    public LekarzDAO(String userName, String userPassword, DatabaseConnection databaseConnection) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.nrPwz = getNrPwz();
        this.databaseConnection = new DatabaseConnection("admin_punktu", "admin1");
    }

    public LekarzDAO(){

    }

    private ObservableList<LekarzStatus> getLekarzStatus(ResultSet resultSet) throws SQLException {
        String selectStms = "select pesel, nazwa, choroba, data, godzina from dostepne_lekarz;";
        ObservableList lekarzStatus = FXCollections.observableArrayList();
        try{
            ResultSet resultSet1 = this.databaseConnection.dbExecuteQuery(selectStms);
            lekarzStatus = this.getLekarzStatus(resultSet1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lekarzStatus;
    }

    private ObservableList<LekarzArchiwum> getLekarzArchiwum(ResultSet resultSet) throws SQLException {
        String selectStms = "select a.nazwa, t.choroba, a.data, a.godzina from archiwum a join typy_szczepien t on a.id_typ = t.nazwa;";
        ObservableList lekarzArchiwum = FXCollections.observableArrayList();
        try{
            ResultSet resultSet2 = this.databaseConnection.dbExecuteQuery(selectStms);
            lekarzArchiwum = this.getLekarzArchiwum(resultSet2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lekarzArchiwum;
    }

    private ObservableList<LekarzStatystyki> getLekarzStatystyki(ResultSet resultSet){
        String selectStmt = "select id_typ, count(id_typ) from archiwum group by id_typ;";
        ObservableList<LekarzStatystyki> lekarzStatystyki = FXCollections.observableArrayList();
        try{
            ResultSet resultSet3 = this.databaseConnection.dbExecuteQuery(selectStmt);
            lekarzStatystyki = this.getLekarzStatystyki(resultSet3);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lekarzStatystyki;
    }

    public ObservableList<LekarzArchiwum> showSpecifiedFromArchiwum(Date dataOD, Date dataDO){
        String selectStmt = "select nazwa, chorob, data, godzina from archiwum where data between" + dataOD + "and" + dataDO + ";";
        ObservableList<LekarzArchiwum> lekarzArchiwum = FXCollections.observableArrayList();
        try{
            ResultSet resultSet = this.databaseConnection.dbExecuteQuery(selectStmt);
            lekarzArchiwum = this.getLekarzArchiwum(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lekarzArchiwum;
    }

    public ObservableList<LekarzStatus> showPacjenta(String pesel){
        String selectStmt = "Select nazwa, choroba, data, godzina from dostepne_lekarz where pesel = " + pesel + ";";
        ObservableList<LekarzStatus> lekarzStatuses = FXCollections.observableArrayList();
        try{
            ResultSet resultSet = this.databaseConnection.dbExecuteQuery(selectStmt);
            lekarzStatuses = this.getLekarzStatus(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lekarzStatuses;
    }
}
