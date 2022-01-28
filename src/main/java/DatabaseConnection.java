import javafx.scene.control.TextArea;

import java.sql.*;

public class DatabaseConnection {
    public Connection databaseLink;
    private String databaseUser;
    private String databasePassword;

    public DatabaseConnection( String databaseUser, String databasePassword) {
        this.databaseUser = databaseUser;
        this.databasePassword = databasePassword;
    }

    public Connection getDatabaseLink() {
        return databaseLink;
    }

    public void setDatabaseLink(Connection databaseLink) {
        this.databaseLink = databaseLink;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public void getConnection(){

        //String url = createURL();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.databaseLink = DriverManager.getConnection(this.createURL());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void dbDisconnect() throws SQLException {
        try {
            if (this.databaseLink != null && !this.databaseLink.isClosed()) {
                this.databaseLink.close();
            }

        } catch (Exception var2) {
            throw var2;
        }
    }

    private String createURL() {
        StringBuilder urlSB = new StringBuilder("jdbc:mysql://");
        urlSB.append("localhost:3306/");
        urlSB.append("punkt_szczepien?");
        urlSB.append("useUnicode=true&characterEncoding=utf-8");
        urlSB.append("&user=");
        urlSB.append(this.databaseUser);
        urlSB.append("&password=");
        urlSB.append(this.databasePassword);
        urlSB.append("&serverTimezone=CET");
        return urlSB.toString();
    }

    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        CachedRowSetWrapper crs;
        try {
            this.dbDisconnect();
            stmt = this.databaseLink.prepareStatement(queryStmt);
            resultSet = stmt.executeQuery(queryStmt);
            crs = new CachedRowSetWrapper();
            crs.populate(resultSet);

        } catch (SQLException var9) {

            throw var9;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            this.dbDisconnect();
        }

        return crs;
    }

    public void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;

        try {
            this.getConnection();
            stmt = this.databaseLink.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException var7) {
            throw var7;
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            this.dbDisconnect();
        }

    }

}
