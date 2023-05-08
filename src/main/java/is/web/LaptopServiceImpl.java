package is.web;

import is.database.Connector;
import is.database.DataBaseRepository;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "com.example.soap.LaptopService")
public class LaptopServiceImpl implements LaptopService {
    private final DataBaseRepository dataBaseRepository;

    public LaptopServiceImpl() {
        Connector connector = null;
        try {
            connector = new Connector();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        this.dataBaseRepository = new DataBaseRepository(connector);
    }

    @Override
    public List<String> getDistinctProducers() {
        try {
            return dataBaseRepository.getDistinctProducers();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching distinct producers");
        }
    }

    @Override
    public int getRecordCountByProducer(String producer) {
        try {
            return dataBaseRepository.getRecordCountByProducer(producer);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching record count by producer");
        }
    }
}
