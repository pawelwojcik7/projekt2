package is.web;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface LaptopService {

    @WebMethod
    @WebResult(name = "producer")
    List<String> getDistinctProducers();

    @WebMethod
    @WebResult(name = "recordCount")
    int getRecordCountByProducer(@WebParam(name = "producer") String producer);
}
