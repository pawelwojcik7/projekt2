package is.database;

import is.format.database.DataBaseInputFormat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseRepository {

    //language=SQL
    private final static String SELECT_ALL_QUERY = "SELECT * FROM laptops";
    private final static String INSERT_QUERY = "INSERT INTO laptops (indexNumber, producer, diagonal, resolution, " +
            "screenType, isTouchable, processor, coreNumber, frequency, RAM, diskSize, diskType, graphicCard, " +
            "graphicCardMemory, operatingSystem, opticalDrive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final Connector connector;

    public DataBaseRepository(Connector connector) {
        this.connector = connector;
    }

    public List<DataBaseInputFormat> getDatabaseInputFormats() throws SQLException {
        List<DataBaseInputFormat> dataBaseInputFormats = new ArrayList<>();
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement(SELECT_ALL_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            DataBaseInputFormat computerInfo = new DataBaseInputFormat();
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
            dataBaseInputFormats.add(computerInfo);
        }
        return dataBaseInputFormats;
    }

    public void saveDataBaseInputFormat(DataBaseInputFormat dataBaseInputFormat) throws SQLException {
        ;
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement(INSERT_QUERY);
        preparedStatement.setString(1, dataBaseInputFormat.getIndex());
        preparedStatement.setString(2, dataBaseInputFormat.getProducer());
        preparedStatement.setString(3, dataBaseInputFormat.getDiagonal());
        preparedStatement.setString(4, dataBaseInputFormat.getResolution());
        preparedStatement.setString(5, dataBaseInputFormat.getScreenType());
        preparedStatement.setString(6, dataBaseInputFormat.getIsTouchable());
        preparedStatement.setString(7, dataBaseInputFormat.getProcessor());
        preparedStatement.setString(8, dataBaseInputFormat.getCoreNumber());
        preparedStatement.setString(9, dataBaseInputFormat.getFrequency());
        preparedStatement.setString(10, dataBaseInputFormat.getRAM());
        preparedStatement.setString(11, dataBaseInputFormat.getDiskSize());
        preparedStatement.setString(12, dataBaseInputFormat.getDiskType());
        preparedStatement.setString(13, dataBaseInputFormat.getGraphicCard());
        preparedStatement.setString(14, dataBaseInputFormat.getGraphicCardMemory());
        preparedStatement.setString(15, dataBaseInputFormat.getOperatingSystem());
        preparedStatement.setString(16, dataBaseInputFormat.getOpticalDrive());
        preparedStatement.executeUpdate();
    }

}
