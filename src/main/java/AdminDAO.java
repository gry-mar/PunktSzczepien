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
}
