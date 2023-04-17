package is.database;
import is.model.ComputerInfo;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class Connector {

    private static final String URL = "jdbc:mysql://localhost:3306/integracja";
    private final static String USER = "root";
    private final static String PASSWORD = "";

    private final Connection connection;

    public Connector() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean isTableExist(String tableName) throws SQLException {
        boolean tableExists = false;
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, tableName, null);
        while (resultSet.next()) {
            String name = resultSet.getString("TABLE_NAME");
            if (name != null && name.equals(tableName)) {
                tableExists = true;
                break;
            }
        }
        return tableExists;
    }

    public void createLaptopsTable() throws SQLException {
        String createTableQuery = "CREATE TABLE laptops ("
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
        Statement statement = connection.createStatement();
        statement.executeUpdate(createTableQuery);
    }

    public List<ComputerInfo> getComputerInfoList() throws SQLException {
        List<ComputerInfo> computerInfoList = new ArrayList<>();
        String selectQuery = "SELECT * FROM laptops";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ComputerInfo computerInfo = new ComputerInfo();
            computerInfo.setIndex(resultSet.getString("indexNumber"));
            computerInfo.setProducer(resultSet.getString("producer"));
            computerInfo.setDiagonal(resultSet.getString("diagonal"));
            computerInfo.setResolution(resultSet.getString("resolution"));
            computerInfo.setScreenType(resultSet.getString("screenType"));
            computerInfo.setIsTouchable(resultSet.getString("isTouchable"));
            computerInfo.setProcessor(resultSet.getString("processor"));
            computerInfo.setCoreNumber(resultSet.getString("coreNumber"));
            computerInfo.setFrequency(resultSet.getString("frequency"));
            computerInfo.setRAM(resultSet.getString("RAM"));
            computerInfo.setDiskSize(resultSet.getString("diskSize"));
            computerInfo.setDiskType(resultSet.getString("diskType"));
            computerInfo.setGraphicCard(resultSet.getString("graphicCard"));
            computerInfo.setGraphicCardMemory(resultSet.getString("graphicCardMemory"));
            computerInfo.setOperatingSystem(resultSet.getString("operatingSystem"));
            computerInfo.setOpticalDrive(resultSet.getString("opticalDrive"));
            computerInfoList.add(computerInfo);
        }
        return computerInfoList;
    }

    public void saveComputerInfo(ComputerInfo computerInfo) throws SQLException {
        String insertQuery = "INSERT INTO laptops (indexNumber, producer, diagonal, resolution, screenType, isTouchable, " +
                "processor, coreNumber, frequency, RAM, diskSize, diskType, graphicCard, graphicCardMemory, operatingSystem, " +
                "opticalDrive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, computerInfo.getIndex());
        preparedStatement.setString(2, computerInfo.getProducer());
        preparedStatement.setString(3, computerInfo.getDiagonal());
        preparedStatement.setString(4, computerInfo.getResolution());
        preparedStatement.setString(5, computerInfo.getScreenType());
        preparedStatement.setString(6, computerInfo.getIsTouchable());
        preparedStatement.setString(7, computerInfo.getProcessor());
        preparedStatement.setString(8, computerInfo.getCoreNumber());
        preparedStatement.setString(9, computerInfo.getFrequency());
        preparedStatement.setString(10, computerInfo.getRAM());
        preparedStatement.setString(11, computerInfo.getDiskSize());
        preparedStatement.setString(12, computerInfo.getDiskType());
        preparedStatement.setString(13, computerInfo.getGraphicCard());
        preparedStatement.setString(14, computerInfo.getGraphicCardMemory());
        preparedStatement.setString(15, computerInfo.getOperatingSystem());
        preparedStatement.setString(16, computerInfo.getOpticalDrive());
        preparedStatement.executeUpdate();
    }


}
