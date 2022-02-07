import classes.LekarzArchiwum;
import classes.LekarzStatus;
import classes.LekarzStatystyki;
import classes.LekarzWykres;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class LekarzDAO {
    private String userName;
    private String userPassword;
    private DatabaseConnection databaseConnection;



    public LekarzDAO(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.databaseConnection = new DatabaseConnection("lekarz", "lekarz1");
        databaseConnection.getConnection();
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



    public LekarzDAO(String userName, String userPassword, DatabaseConnection databaseConnection) {
        this.userName = userName;
        this.userPassword = userPassword;

    }

    public LekarzDAO(){

    }


    /**
     * method that maps results from sql to ObservableList
     * @param resultSet
     * @return ObservableList LekarzStatus
     * @throws SQLException
     */
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

    /**
     * method that maps sql columns referred to archive data
     * @param resultSet
     * @return ObservableList LekarzArchiwum
     * @throws SQLException
     */
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

    /**
     * method that maps statistics from sql
     * @param resultSet
     * @return ObservableList LekarzStatystyki
     * @throws SQLException
     */
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

    /**
     *method that maps sql result to LekarzWykres field
     * @param resultSet
     * @return int count of vaccinations
     * @throws SQLException
     */
    private int getLekarzWykres(ResultSet resultSet) throws SQLException {
        int lekarzWykres = 0;
        LekarzWykres lw = new LekarzWykres();
        while (resultSet.next()){
            lw.setIlosc(resultSet.getInt(1));
        }
        return lw.getIlosc();
    }

    /**
     *method that get data from dostepne_lekarz view and adds it to ObservableList
     * @param dataOD
     * @param dataDO
     * @return ObservableList LekarzArchiwum
     */
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

    /**
     *method that get data from dostepne_lekarz view and adds it to ObservableList
     * @param pesel
     * @return ObservableList  lekarzStatus
     */

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

    /**
     * method that operates with archiwum and typy_szczepien tables
     * @return ObservableList LekarzStatystyki
     * @throws SQLException
     */
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

    /**
     * method that updates values in table szczepienia
     * @param status
     * @param nazwa
     * @param data
     * @param godzina
     * @throws SQLException
     */
    public void lekarzUpdate(String status, String nazwa, Date data, Time godzina ) throws SQLException {
        this.databaseConnection.getConnection();
        Statement statement = this.databaseConnection.getDatabaseLink().createStatement();
        String update = "update szczepienia set status = '" + status + "'  where " +
                " id_typ = '" + nazwa + "' and data = '" + data + "' and godzina = '" + godzina + "';";
        statement.execute(update);
        Statement statement1 = databaseConnection.getDatabaseLink().createStatement();
        String delete = "delete from szczepienia where status = '" + status + "'  and " +
                               " id_typ = '" + nazwa + "' and data = '" + data + "' and godzina = '" + godzina + "';";
        statement1.execute(delete);
    }

    /**
     * method that returns number of vaccinations per month
     * @param nazwa
     * @param rok
     * @param miesiac
     * @return int value used in chart
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public int szczepieniaMiesiac(String nazwa, int rok, int miesiac) throws SQLException, ClassNotFoundException {
        this.databaseConnection.getConnection();
        int lekarzWykres = 0;
        String szczepieniaIlosc = "select count(id_szczepienia) as ilosc from archiwum where id_typ = '"
                + nazwa + "' and YEAR(data) = " + rok +
                " and MONTH(data) = " + miesiac + " and status = 'zrealizowane';";
        ResultSet resultSet = this.databaseConnection.dbExecuteQuery(szczepieniaIlosc);
        lekarzWykres = this.getLekarzWykres(resultSet);
        return lekarzWykres;
    }
}
