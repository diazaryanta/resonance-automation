package UI.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Mini Project Result");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Diaz");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome" );
            extent.setSystemInfo("Operating System", "MacOS");
            extent.setSystemInfo("Version", "- Postman for Mac: v12.2.4 <br>" + "- Chrome: v146.0.7680.80 <br>" + "- MacOS: Tahoe v26.3.1 <br>" + "- App Version : N/A");
            extent.setSystemInfo("UI Base URL", "https://resonance.dibimbing.id");
            extent.setSystemInfo("API Base URL", "https://resonance.dibimbing.id/docs");
        }
        return extent;
    }
}