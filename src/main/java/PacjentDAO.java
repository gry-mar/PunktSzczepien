import classes.ArchiwumPacjent;
import classes.DostepneSzczepienia;
import classes.RealizacjaPacjent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class PacjentDAO {

    private String userName;
    private String userPassword;
    private String pesel;
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

    public String getPesel() {
        String selectStmt = "SELECT pesel FROM pacjenci WHERE login_pac = "+"'"+userName+"';";
        try{
            ResultSet rs = this.databaseConnection.dbExecuteQuery(selectStmt);
            while(rs.next()){
                pesel = rs.getString(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public PacjentDAO(String userName, String userPassword, DatabaseConnection databaseConnection) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.databaseConnection = new DatabaseConnection("admin_punktu","admin1");
        this.pesel = getPesel();
    }

    public PacjentDAO() {
    }

    private ObservableList<ArchiwumPacjent> getAtchiwumList(ResultSet rs) throws SQLException{
        ObservableList archiwumList = FXCollections.observableArrayList();
        while(rs.next()){
            ArchiwumPacjent a = new ArchiwumPacjent();
            a.setNazwa(rs.getString("nazwa"));
            a.setChoroba(rs.getString("choroba"));
            a.setData(rs.getDate("data"));
            a.setGodzina(rs.getTime("godzina"));
            archiwumList.add(a);
        }
        return archiwumList;
    }


    private ObservableList<DostepneSzczepienia> datDostepne(ResultSet rs) throws SQLException {
        ObservableList<DostepneSzczepienia> dostepne = FXCollections.observableArrayList();
        while(rs.next()){
            DostepneSzczepienia d = new DostepneSzczepienia();
            d.setNazwaDostepne(rs.getString("nazwa"));
            d.setChorobaDostepne(rs.getString("choroba"));
            d.setDataDostepne(rs.getDate("data"));
            d.setGodzinaDostepne(rs.getTime("godzina"));
            dostepne.add(d);
        }
        return dostepne;
    }

    private ObservableList<RealizacjaPacjent> datRealizacja(ResultSet rs) throws SQLException {
        ObservableList<RealizacjaPacjent> realizacja = FXCollections.observableArrayList();
        while(rs.next()){
            RealizacjaPacjent r = new RealizacjaPacjent();
            r.setNazwaRelizacja(rs.getString("nazwa"));
            r.setChorobaRealizacja(rs.getString("choroba"));
            r.setDataRalizacja(rs.getDate("data"));
            r.setGodzinaRealizacja(rs.getTime("godzina"));
            realizacja.add(r);
        }
        return realizacja;
    }

    public ObservableList<ArchiwumPacjent> showSpecifiedFromArchiwum(Date dataOd, Date dataDo){
        String selectStmt = "SELECT nazwa, choroba, data, godzina FROM archiwum_pacjent WHERE pesel = '"+pesel+"' AND data BETWEEN '"+dataOd + "' AND '"+dataDo+"';";
        ObservableList<ArchiwumPacjent> archiwum = FXCollections.observableArrayList();
        try{
            ResultSet rs = this.databaseConnection.dbExecuteQuery(selectStmt);
             archiwum = this.getAtchiwumList(rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return archiwum;
    }

    public ObservableList<DostepneSzczepienia> showAllDostepne(){
        String selectStmt = "SELECT nazwa, choroba, data, godzina FROM dostepne_szczepienia;";
        ObservableList<DostepneSzczepienia> dostepne = FXCollections.observableArrayList();
        try{
            ResultSet rs = this.databaseConnection.dbExecuteQuery(selectStmt);
            dostepne = this.datDostepne(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dostepne;
    }

    public ObservableList<RealizacjaPacjent> showAllRealizacja(){
        String selectStmt = "SELECT nazwa, choroba, data, godzina FROM realizacja_szczepienia";
        ObservableList<RealizacjaPacjent> realizacja = FXCollections.observableArrayList();
        try{
            ResultSet rs = this.databaseConnection.dbExecuteQuery(selectStmt);
            realizacja = this.datRealizacja(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return realizacja;
    }


    public boolean zapisPacjenta(Date dataZapisu, Time godzinaZapisu, String choroba){
        boolean czyZapisano = false;
        try {
           databaseConnection.getConnection();

            CallableStatement cstm = databaseConnection.getDatabaseLink().prepareCall("{call zapis_na_szczepienie(?,?,?,?,?)}");
            cstm.setString(1,getPesel());
            cstm.setDate(2,dataZapisu);
            cstm.setTime(3, godzinaZapisu);
            cstm.setString(4,choroba);
            cstm.registerOutParameter(5, Types.BOOLEAN);
            cstm.executeUpdate();
            czyZapisano = (Boolean) cstm.getBoolean(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(czyZapisano);
        System.out.println(getPesel());

        return czyZapisano;
    }





}
