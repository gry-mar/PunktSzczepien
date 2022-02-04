import classes.LekarzArchiwum;
import classes.LekarzStatus;
import classes.LekarzStatystyki;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class LekarzDAO {
    private String userName;
    private String userPassword;
    private Integer nrPwz;
    private DatabaseConnection databaseConnection;

    public Integer getNrPwz() {
        String selectStms = "select nr_pwz from lekarze where login_lek = '" + userName + "';";
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

    public LekarzDAO(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.databaseConnection = new DatabaseConnection("admin_punktu", "admin1");
        databaseConnection.getConnection();
        this.nrPwz = getNrPwz();
    }

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

    // status i archiwum

    private ObservableList<LekarzStatus> getLekarzStatus(ResultSet resultSet) throws SQLException {
        ObservableList<LekarzStatus> lekarzStatus = FXCollections.observableArrayList();
        while(resultSet.next()){
            LekarzStatus ls = new LekarzStatus();
            ls.setNazwaLekarzStatus(resultSet.getString("nazwa"));
            ls.setChorobaLekarzStatus(resultSet.getString("choroba"));
            ls.setDataLekarzStatus(resultSet.getDate("data"));
            ls.getGodzinaLekarzStatus(resultSet.getTime("godzina"));
            lekarzStatus.add(ls);
        }
        return lekarzStatus;
    }

    private ObservableList<LekarzArchiwum> getLekarzArchiwum(ResultSet resultSet) throws SQLException {
        ObservableList lekarzArchiwum = FXCollections.observableArrayList();
        while(resultSet.next()){
            LekarzArchiwum la = new LekarzArchiwum();
            la.setNazwaLekarzArchiwum(resultSet.getString("id_typ"));
            la.setChorobaLekarzArchiwum(resultSet.getString("choroba"));
            la.setDataLekarzArchiwum(resultSet.getDate("data"));
            la.setGodzinaLekarzArchiwum(resultSet.getTime("godzina"));
            lekarzArchiwum.add(la);
        }
        return lekarzArchiwum;
    }

    private ObservableList<LekarzStatystyki> getLekarzStatystyki(ResultSet resultSet) throws SQLException {
        ObservableList lekarzStatystyki = FXCollections.observableArrayList();
        while (resultSet.next()){
            LekarzStatystyki lst = new LekarzStatystyki();
            lst.setChorobaLekarzStatystyki(resultSet.getString("choroba"));
            lst.setNazwaLekarzStatystyki(resultSet.getString("id_typ"));
            lst.setIloscWykonanychLekarzStatystyki(resultSet.getInt(3));
            lekarzStatystyki.add(lst);
        }
        return lekarzStatystyki;
    }

    public ObservableList<LekarzArchiwum> showSpecifiedFromArchiwum(Date dataOD, Date dataDO){
        String selectStmt = "select a.id_typ, t.choroba, a.data, a.godzina from archiwum a join typy_szczepien t on a.id_typ = t.nazwa where data between '" + dataOD +"'"+ " and " +"'"+ dataDO + "';";
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
        String selectStmt = "select nazwa, choroba, data, godzina from dostepne_lekarz where pesel = '" + pesel + "';";
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

    public ObservableList<LekarzStatystyki> showLekarzStatystyki() throws SQLException {
        String selectStmt = "select t.choroba, a.id_typ, count(a.id_typ) from archiwum a join " +
                "typy_szczepien t on a.id_typ = t.nazwa where status = 'zrealizowane' group by a.id_typ;";
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

    public void lekarzUpdate(String status, String nazwa, Date data, Time godzina ) throws SQLException {
        Statement statement = databaseConnection.databaseLink.createStatement();
        String update = "update szczepienia set status = '" + status + "'  where " +
                " id_typ = '" + nazwa + "' and data = '" + data + "' and godzina = '" + godzina + "';";
        statement.execute(update);
        Statement statement1 = databaseConnection.databaseLink.createStatement();
        String delete = "delete from szczepienia where status = '" + status + "'  and " +
                               " id_typ = '" + nazwa + "' and data = '" + data + "' and godzina = '" + godzina + "';";
        statement1.execute(delete);
    }
}
