package Utilities;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class LoggingResult {

    /**
     * Setting log4j appender to LOGGER using its category name.
     * See log4j.xml
     */
    private static final Log LOGGER = LogFactory.getLog("your_selenium_testing");

    private static String generateJsonFromLogMap(String result){

        Map<String, String> logMap = new HashMap<>();

        // START_DATE is a preset session value
        // Use @Given step when using JBehave to initialize START_DATE as a session value
        Date startDate = Calendar.getInstance().getTime();
        Date endDate = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        long testDuration=endDate.getTime()-startDate.getTime();

        // setting test outcome result
        logMap.put("result", result);
        logMap.put("sprint", "Sprint 1");
        logMap.put("TestType", "API");

        // time-related values
        logMap.put("timestamp", df.format(endDate));
        logMap.put("duration", String.valueOf(testDuration));

        // system properties, provided via VM options on run, especially from Jenkins
        logMap.put("os", System.getProperty("os")); // vm option -Dos=OS_Name
        logMap.put("build_number", System.getProperty("buildNumber")); // vm option -DbuildNumber=${BUILD_NUMBER}

        JSONObject logEntries = new JSONObject(logMap);
        return logEntries.toString();

    }

    // ON SUCCESS

    public static void logSuccessfulScenario() {

        LOGGER.debug(generateJsonFromLogMap("passed"));
    }

    // ON FAILURE

    public static void logFailedScenario() {

        LOGGER.debug(generateJsonFromLogMap("failed"));
    }
}