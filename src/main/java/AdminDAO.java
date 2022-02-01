import classes.Lekarz;
import classes.Szczepienia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    private String userNameA;
    private String userPasswordA;
    private DatabaseConnection databaseConnection;

    public AdminDAO(String userNameA, String userPasswordA, DatabaseConnection databaseConnection) {
        this.userNameA = userNameA;
        this.userPasswordA = userPasswordA;
        this.databaseConnection = databaseConnection;
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

    private ObservableList<Szczepienia> getSzczepieniaList(ResultSet rs){
        ObservableList szczepieniaList = FXCollections.observableArrayList();
//        while(rs.next()){
//            Szczepienia s = new Szczepienia();
//            s.setIdSczepienia(rs.getBinaryStream(""));
//        }
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


}
