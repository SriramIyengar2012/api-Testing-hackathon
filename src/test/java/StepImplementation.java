import Utilities.LoggingResult;
import com.thoughtworks.gauge.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import api.ApiFactory;

import static io.restassured.RestAssured.given;

//import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation {

    ValidatableResponse jsonResponse;


    @Step("Given a <EMPLOYEE_API>")
    public void navigateToEmployeeApplication(String apiendpoint) {
        System.out.println("IN");
        ApiFactory.empAPI.scenarioStore.put("apiendpoint", System.getenv(apiendpoint));
        RestAssured.baseURI = System.getenv(apiendpoint);
        RequestSpecification request = given();
        ApiFactory.empAPI.setRequest(request);

    }

    @Step("When a user makes a GET request")
    public void submiotGetRequest()
    {
        Response response = ApiFactory.empAPI.getRequest().when().get(ApiFactory.empAPI.scenarioStore.get("apiendpoint").toString());
        ApiFactory.empAPI.setResponse(response);
        ApiFactory.empAPI.scenarioStore.put("response", response);
        Gauge.writeMessage(response.prettyPrint());
    }


    @Step("Then the status code from <apiName> should be <statusCode>")
    public void statusCodeShouldBe(String apiName, int statusCode) {

        // Fetching Values from Data store
        Response response = (Response) ApiFactory.empAPI.scenarioStore.get("response");
        jsonResponse = response.then().statusCode(statusCode);

    }


    @Step("And response includes the following <table>")
    public void responseIncludesInAnyOrder(Table table) {
        ApiFactory.empAPI.validateResponseEqualTo(jsonResponse, table);
        LoggingResult.logSuccessfulScenario();


    }


}
