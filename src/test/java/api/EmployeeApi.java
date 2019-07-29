package api;

import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class EmployeeApi {

    protected static String Url = System.getenv("APP_ENDPOINT");

    private RequestSpecification request;
    private Response response;
    private ValidatableResponse validatableResponse;
    public DataStore scenarioStore = DataStoreFactory.getScenarioDataStore();

    public EmployeeApi()
    {

    }

    public RequestSpecification getRequest(){
        return request;
    }

    public void setRequest(RequestSpecification request){
        this.request = request;
    }


    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public ValidatableResponse getValidatableResponse() {
        return validatableResponse;
    }

    public void setValidatableResponse(ValidatableResponse validatableResponse) {
        this.validatableResponse = validatableResponse;
    }

    public HashMap convertTableToAHashMap(Table table){

        HashMap<String, String> paramsHash = new HashMap<>();

        for (TableRow row : table.getTableRows()) {
            paramsHash.put(row.getCell("Key"), row.getCell("Value"));
        }

        return paramsHash;

    }

    public Map convertTableToAJsonMap(Table table){

        Map<String, Object> jsonAsMap = new HashMap<>();

        for (TableRow row : table.getTableRows()) {
            jsonAsMap.put(row.getCell("Key"), row.getCell("Value"));
        }

        return jsonAsMap;

    }

    public void validateResponseEqualTo(ValidatableResponse validatableResponse, Table table){

        for (TableRow row : table.getTableRows()) {

            if(StringUtils.isNumeric(row.getCell("Value"))){
                validatableResponse.body(row.getCell("Key"), equalTo(Integer.parseInt(row.getCell("Value"))));
            }

            else{
                validatableResponse.body(row.getCell("Key"), equalTo(row.getCell("Value")));
            }

        }

    }

    public void validateResponseContainsInAnyOrder(ValidatableResponse validatableResponse, Table table){

        for (TableRow row : table.getTableRows()) {
            System.out.println("OKO"+row.getCell("Value"));

            if(StringUtils.isNumeric(row.getCell("Value"))){
                validatableResponse.body(row.getCell("Key"), containsInAnyOrder(Integer.parseInt(row.getCell("Value"))));
            }
            else{
                validatableResponse.body(row.getCell("Key"), containsInAnyOrder(row.getCell("Value")));

            }
        }

    }


}
