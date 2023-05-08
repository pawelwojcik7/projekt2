package is.database;

import is.format.database.DataBaseInputFormat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataBaseRepository {

    //language=SQL
    private final static String SELECT_ALL_QUERY = "SELECT * FROM laptops";

    private final static String INSERT_QUERY = "INSERT INTO laptops (indexNumber, producer, diagonal, resolution, " +
            "screenType, isTouchable, processor, coreNumber, frequency, RAM, diskSize, diskType, graphicCard, " +
            "graphicCardMemory, operatingSystem, opticalDrive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final static String SELECT_DISTINCT_PRODUCERS = "SELECT DISTINCT producer FROM laptops";

    private final static String SELECT_BY_PRODUCER_QUERY = "SELECT * FROM laptops WHERE producer = ?";


    private final Connector connector;

    public DataBaseRepository(Connector connector) {
        this.connector = connector;
    }

    public List<DataBaseInputFormat> getDatabaseInputFormats() throws SQLException {
        List<DataBaseInputFormat> dataBaseInputFormats = new ArrayList<>();
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement(SELECT_ALL_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            DataBaseInputFormat dataBaseInputFormat = new DataBaseInputFormat();
            dataBaseInputFormat.setIndex(resultSet.getString("indexNumber"));
            dataBaseInputFormat.setProducer(resultSet.getString("producer"));
            dataBaseInputFormat.setDiagonal(resultSet.getString("diagonal"));
            dataBaseInputFormat.setResolution(resultSet.getString("resolution"));
            dataBaseInputFormat.setScreenType(resultSet.getString("screenType"));
            dataBaseInputFormat.setIsTouchable(resultSet.getString("isTouchable"));
            dataBaseInputFormat.setProcessor(resultSet.getString("processor"));
            dataBaseInputFormat.setCoreNumber(resultSet.getString("coreNumber"));
            dataBaseInputFormat.setFrequency(resultSet.getString("frequency"));
            dataBaseInputFormat.setRAM(resultSet.getString("RAM"));
            dataBaseInputFormat.setDiskSize(resultSet.getString("diskSize"));
            dataBaseInputFormat.setDiskType(resultSet.getString("diskType"));
            dataBaseInputFormat.setGraphicCard(resultSet.getString("graphicCard"));
            dataBaseInputFormat.setGraphicCardMemory(resultSet.getString("graphicCardMemory"));
            dataBaseInputFormat.setOperatingSystem(resultSet.getString("operatingSystem"));
            dataBaseInputFormat.setOpticalDrive(resultSet.getString("opticalDrive"));
            dataBaseInputFormats.add(dataBaseInputFormat);
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

    public List<String> getAllDistinctProducers() throws SQLException {
        List<String> distinctProducers = new ArrayList<>();

        PreparedStatement preparedStatement = connector.getConnection().prepareStatement(SELECT_DISTINCT_PRODUCERS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            distinctProducers.add(resultSet.getString("producer"));
        }

        return distinctProducers;
    }

    public List<DataBaseInputFormat> getDatabaseInputFormatsByProducer(String producer) throws SQLException {
        List<DataBaseInputFormat> dataBaseInputFormats = new ArrayList<>();
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement(SELECT_BY_PRODUCER_QUERY);
        preparedStatement.setString(1, producer);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            DataBaseInputFormat dataBaseInputFormat = new DataBaseInputFormat();
            dataBaseInputFormat.setIndex(resultSet.getString("indexNumber"));
            dataBaseInputFormat.setProducer(resultSet.getString("producer"));
            dataBaseInputFormat.setDiagonal(resultSet.getString("diagonal"));
            dataBaseInputFormat.setResolution(resultSet.getString("resolution"));
            dataBaseInputFormat.setScreenType(resultSet.getString("screenType"));
            dataBaseInputFormat.setIsTouchable(resultSet.getString("isTouchable"));
            dataBaseInputFormat.setProcessor(resultSet.getString("processor"));
            dataBaseInputFormat.setCoreNumber(resultSet.getString("coreNumber"));
            dataBaseInputFormat.setFrequency(resultSet.getString("frequency"));
            dataBaseInputFormat.setRAM(resultSet.getString("RAM"));
            dataBaseInputFormat.setDiskSize(resultSet.getString("diskSize"));
            dataBaseInputFormat.setDiskType(resultSet.getString("diskType"));
            dataBaseInputFormat.setGraphicCard(resultSet.getString("graphicCard"));
            dataBaseInputFormat.setGraphicCardMemory(resultSet.getString("graphicCardMemory"));
            dataBaseInputFormat.setOperatingSystem(resultSet.getString("operatingSystem"));
            dataBaseInputFormat.setOpticalDrive(resultSet.getString("opticalDrive"));
            dataBaseInputFormats.add(dataBaseInputFormat);
        }

        return dataBaseInputFormats;
    }

}
