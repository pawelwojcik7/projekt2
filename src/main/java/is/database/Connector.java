package is.database;

import lombok.Getter;

import java.sql.*;

public class Connector {

    //language=SQL
    private final static String CREATE_LAPTOPS_TABLE_QUERY = "CREATE TABLE laptops ("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "indexNumber VARCHAR(20) NOT NULL,"
            + "producer VARCHAR(50),"
            + "diagonal VARCHAR(20),"
            + "resolution VARCHAR(20),"
            + "screenType VARCHAR(50),"
            + "isTouchable VARCHAR(5),"
            + "processor VARCHAR(50),"
            + "coreNumber INT,"
            + "frequency DOUBLE,"
            + "RAM VARCHAR(20),"
            + "diskSize VARCHAR(20),"
            + "diskType VARCHAR(20),"
            + "graphicCard VARCHAR(50),"
            + "graphicCardMemory VARCHAR(20),"
            + "operatingSystem VARCHAR(50),"
            + "opticalDrive VARCHAR(50)"
            + ");";
    private static final String URL = "jdbc:mysql://localhost:3306/integracja";
    private final static String USER = "root";
    private final static String PASSWORD = "";

    @Getter
    private final Connection connection;

    public Connector() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void createLaptopsTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_LAPTOPS_TABLE_QUERY);
    }

    public boolean isTableExist(String tableName) throws SQLException {
        return ifTableExists(tableName);
    }

    private boolean ifTableExists(String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, tableName, null);
        while (resultSet.next()) {
            String name = resultSet.getString("TABLE_NAME");
            if (name != null && name.equals(tableName)) {
                return true;
            }
        }
        return false;
    }

}
