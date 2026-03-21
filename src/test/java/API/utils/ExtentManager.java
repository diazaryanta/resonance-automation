package API.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/API_Report.html");

            spark.config().setReportName("API Automation Test Report");
            spark.config().setDocumentTitle("API Test Result");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Diaz");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Module", "API Backend");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Operating System", "MacOS");
            extent.setSystemInfo("Version Details",
                         "- RestAssured: v5.3.0 <br>" +
                            "- Chrome: v146.0.7680.80 <br>" +
                            "- Postman for Mac: v12.2.4 <br>" +
                            "- MacOS Tahoe: v26.3.1 <br>" +
                            "- Java: v21 <br>" +
                            "- App Version: 1.0.0");
            extent.setSystemInfo("API Base URL", "https://resonance.dibimbing.id/api/rest/ticketById");
            extent.setSystemInfo("API Documentation", "https://resonance.dibimbing.id/docs");
        }
        return extent;
    }
}