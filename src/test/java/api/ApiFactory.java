package api;

import com.thoughtworks.gauge.BeforeSuite;

public class ApiFactory {

    public static EmployeeApi empAPI;


    @BeforeSuite
    public void init(){
        empAPI = new EmployeeApi();

    }
}
