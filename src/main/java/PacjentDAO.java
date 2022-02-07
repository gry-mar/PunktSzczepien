import classes.ArchiwumPacjent;
import classes.DostepneSzczepienia;
import classes.RealizacjaPacjent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class PacjentDAO {

    /**
     * PacjentDAO class is responsible for database operations for Pacjent view
     * for example executing procedures and queries
     * @author Martyna Grygiel
     * @version 1.0
     * @since 04.02.2022
     */

    private String userName;
    private String userPassword;
    private DatabaseConnection databaseConnection;
    private String pesel;

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

    /**
     * method to get pesel by executing sql query
     * @return String pesel
     */
    public String getPesel() {
        String selectStmt = "SELECT pesel FROM pacjenci WHERE login_pac = " + "'" + userName + "';";
        String pesel = "";
        try {
            ResultSet rs = this.databaseConnection.dbExecuteQuery(selectStmt);
            while (rs.next()) {
                pesel = rs.getString(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return pesel;
    }


    public PacjentDAO(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.databaseConnection = new DatabaseConnection("pacjent", "pacjent1");
        databaseConnection.getConnection();
        this.pesel = getPesel();

    }

    public PacjentDAO() {
    }

    /**
     * method to map sql columns to ArchiwumPacjent
     * @param rs
     * @return ObservableList of objects ArchiwumPacjent
     * @throws SQLException
     */
    private ObservableList<ArchiwumPacjent> getAtchiwumList(ResultSet rs) throws SQLException {
        ObservableList archiwumList = FXCollections.observableArrayList();
        while (rs.next()) {
            ArchiwumPacjent a = new ArchiwumPacjent();
            a.setNazwa(rs.getString("nazwa"));
            a.setChoroba(rs.getString("choroba"));
            a.setData(rs.getDate("data"));
            a.setGodzina(rs.getTime("godzina"));
            archiwumList.add(a);
        }
        return archiwumList;
    }
    /**
     * method to map sql columns to DostepneSzczepienia
     * @param rs
     * @return ObservableList of objects DosepneSzczepienia
     * @throws SQLException
     */

    private ObservableList<DostepneSzczepienia> datDostepne(ResultSet rs) throws SQLException {
        ObservableList<DostepneSzczepienia> dostepne = FXCollections.observableArrayList();
        while (rs.next()) {
            DostepneSzczepienia d = new DostepneSzczepienia();
            d.setNazwaDostepne(rs.getString("nazwa"));
            d.setChorobaDostepne(rs.getString("choroba"));
            d.setDataDostepne(rs.getDate("data"));
            d.setGodzinaDostepne(rs.getTime("godzina"));
            dostepne.add(d);
        }
        return dostepne;
    }

    /**
     * method to map sql columns  to RealizacjaPacjent
     * @param rs
     * @return ObservableList of RealizacjaPacjent
     * @throws SQLException
     */

    private ObservableList<RealizacjaPacjent> datRealizacja(ResultSet rs) throws SQLException {
        ObservableList<RealizacjaPacjent> realizacja = FXCollections.observableArrayList();
        while (rs.next()) {
            RealizacjaPacjent r = new RealizacjaPacjent();
            r.setNazwaRealizacja(rs.getString("nazwa"));
            r.setChorobaRealizacja(rs.getString("choroba"));
            r.setDataRealizacja(rs.getDate("data"));
            r.setGodzinaRealizacja(rs.getTime("godzina"));
            realizacja.add(r);
        }
        return realizacja;
    }

    /**
     * selects data from archiwum_pacjent in specified date interval and returns it as a list
     * @param dataOd
     * @param dataDo
     * @return ObservableList of ArchiwumPacjent
     */
    public ObservableList<ArchiwumPacjent> showSpecifiedFromArchiwum(Date dataOd, Date dataDo) {
        String selectStmt = "SELECT nazwa, choroba, data, godzina FROM archiwum_pacjent WHERE pesel = '" + this.getPesel() + "' AND data BETWEEN '" + dataOd + "' AND '" + dataDo + "';";
        ObservableList<ArchiwumPacjent> archiwum = FXCollections.observableArrayList();
        try {
            ResultSet rs = this.databaseConnection.dbExecuteQuery(selectStmt);
            archiwum = this.getAtchiwumList(rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return archiwum;
    }

    /**
     * selects data from view dostepne_szczepienia and returns it as a list
     * @return Observable List of DostepneSzczepienia objects
     */
    public ObservableList<DostepneSzczepienia> showAllDostepne() {
        String selectStmt = "SELECT nazwa, choroba, data, godzina FROM dostepne_szczepienia;";
        ObservableList<DostepneSzczepienia> dostepne = FXCollections.observableArrayList();
        try {
            ResultSet rs = this.databaseConnection.dbExecuteQuery(selectStmt);
            dostepne = this.datDostepne(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dostepne;
    }

    /**
     * method that selects values from view realizacja_szczepienia
     * @return Observable list of RealizaPacjent objects
     */
    public ObservableList<RealizacjaPacjent> showAllRealizacja() {
        String selectStmt = "SELECT nazwa, choroba, data, godzina FROM realizacja_szczepienia WHERE pesel = '"+this.getPesel()+"';";
        ObservableList<RealizacjaPacjent> realizacja = FXCollections.observableArrayList();
        try {
            ResultSet rs = this.databaseConnection.dbExecuteQuery(selectStmt);
            realizacja = this.datRealizacja(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return realizacja;
    }

    /**
     * method that calls sql procedure zapisywanko
     * @param dataZapisu
     * @param godzinaZapisu
     * @param choroba
     * @return boolean according to success of operation
     */

    public boolean zapisPacjenta(Date dataZapisu, Time godzinaZapisu, String choroba) {
        boolean czyZapisano = false;
        try {
            databaseConnection.getConnection();

            CallableStatement cstm = databaseConnection.getDatabaseLink().prepareCall("{call zapisywanko(?,?,?,?,?)}");
            cstm.setString(1, getPesel());
            cstm.setDate(2, dataZapisu);
            cstm.setTime(3, godzinaZapisu);
            cstm.setString(4, choroba);
            cstm.registerOutParameter(5, Types.VARCHAR);
            cstm.execute();
            String zapisMsg = cstm.getString(5);
            if(zapisMsg.equals("tak")){
                czyZapisano = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return czyZapisano;
    }

    /**
     * method that calls sql procedure zmianaTerminu
     * @param dataZ
     * @param godzinaZ
     * @param dataNa
     * @param godzinaNa
     * @param choroba
     * @return boolean according to success of operation
     */
    public boolean zmianaTeminuPacjenta(Date dataZ, Time godzinaZ, Date dataNa, Time godzinaNa, String choroba) {
        boolean czyZapisano = false;
        try {
            databaseConnection.getConnection();

            CallableStatement cstm = databaseConnection.getDatabaseLink().prepareCall("{call zmianaTerminu(?,?,?,?,?,?,?)}");
            cstm.setDate(1,dataZ);
            cstm.setTime(2,godzinaZ);
            cstm.setDate(3,dataNa);
            cstm.setTime(4,godzinaNa);
            cstm.setString(5,choroba);
            cstm.setString(6,getPesel());
            cstm.registerOutParameter(7,Types.VARCHAR);
            cstm.execute();
            String zapis = cstm.getString(7);
            if(zapis.equals("tak")){
                czyZapisano = true;
            }else{
                czyZapisano = false;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return czyZapisano;


    }
}