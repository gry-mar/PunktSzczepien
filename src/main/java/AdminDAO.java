import classes.Lekarz;
import classes.Szczepienia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AdminDAO {

    private String userNameA;
    private String userPasswordA;
    private DatabaseConnection databaseConnection;

    public AdminDAO(String userNameA, String userPasswordA) {
        this.userNameA = userNameA;
        this.userPasswordA = userPasswordA;
        this.databaseConnection = new DatabaseConnection("admin_punktu", "admin1");
        databaseConnection.getConnection();
    }


    public String getUserNameA() {
        return userNameA;
    }

    public void setUserNameA(String userNameA) {
        this.userNameA = userNameA;
    }

    public String getUserPasswordA() {
        return userPasswordA;
    }

    public void setUserPasswordA(String userPasswordA) {
        this.userPasswordA = userPasswordA;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    private ObservableList<Szczepienia> getSzczepieniaList(ResultSet rs) throws SQLException {
        ObservableList szczepieniaList = FXCollections.observableArrayList();
        while(rs.next()){
            Szczepienia s = new Szczepienia();
            s.setData(rs.getDate("data"));
            s.setGodzina(rs.getTime("godzina"));
            s.setPeselPacjent(rs.getString("pesel_pacjenta"));
            s.setNrPwzLekarz(rs.getInt("lekarz_nr_pwz"));
            s.setIdTyp(rs.getString("id_typ"));
            s.setStatus(rs.getString("status"));
            szczepieniaList.add(s);
        }
        return szczepieniaList;
    }

    private ObservableList<Lekarz> getAllLekarze(ResultSet rs) throws SQLException {
        ObservableList<Lekarz> lekarze = FXCollections.observableArrayList();
        while(rs.next()){
            Lekarz l = new Lekarz();
            l.setNrPwz(rs.getInt("nr_pwz"));
            l.setImie(rs.getString("imie"));
            l.setNazwisko(rs.getString("nazwisko"));
            l.setLoginLekarz(rs.getString("login_lek"));
            lekarze.add(l);
        }
        return lekarze;
    }
    public ObservableList<Lekarz> showAllLekarze(){
        String stmt = "SELECT * from lekarze";
        ObservableList<Lekarz> lekarze = FXCollections.observableArrayList();
        try{
            ResultSet rs = this.databaseConnection.dbExecuteQuery(stmt);
            lekarze = this.getAllLekarze(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lekarze;
    }

    public boolean zapisLekarza(String imie, String nazwisko, int nrPwz, String loginLek, String hasloLek){
        boolean czyDodano = false;
        try{
            databaseConnection.getConnection();
            CallableStatement cstm = databaseConnection.getDatabaseLink()
                    .prepareCall("{call rejestracja_lekarz(?,?,?,?,?,?)}");
            cstm.setString(1,imie);
            cstm.setString(2,nazwisko);
            cstm.setInt(3,nrPwz);
            cstm.setString(4,loginLek);
            cstm.setString(5,hasloLek);
            cstm.registerOutParameter(6, Types.BIT);
            cstm.execute();
            czyDodano = (Boolean) cstm.getBoolean(6);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return czyDodano;
    }

    public ObservableList<Szczepienia> showAllSzczepienia(){
        String stmt = "SELECT data, godzina, pesel_pacjenta, lekarz_nr_pwz, status, id_typ FROM szczepienia;";
        ObservableList<Szczepienia> szczepienia = FXCollections.observableArrayList();
        try{
            ResultSet rs = this.databaseConnection.dbExecuteQuery(stmt);
            szczepienia = this.getSzczepieniaList(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return szczepienia;
    }

public boolean dodajTermin(Date data, Time godzina, String choroba, int nrPwz){
        boolean czyDodano = false;
        try{
            databaseConnection.getConnection();
            CallableStatement cstm = databaseConnection.getDatabaseLink()
                    .prepareCall("{call dodajtermin(?,?,?,?,?)}");
            cstm.setDate(1,data);
            cstm.setTime(2,godzina);
            cstm.setString(3,choroba);
            cstm.setInt(4,nrPwz);
            cstm.registerOutParameter(5,Types.VARCHAR);
            cstm.execute();
            String dodanie = cstm.getString(5);
            if(dodanie.equals("tak")){
                czyDodano = true;
            }else{
                czyDodano = false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return czyDodano;
}


}
