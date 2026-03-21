package UI.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/UI_Report.html");
            spark.config().setReportName("UI Automation Test Report");
            spark.config().setDocumentTitle("UI Test Result");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Diaz");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Module", "UI Frontend");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Operating System", "MacOS");
            extent.setSystemInfo("Version Details",
                              "- Chrome: v146.0.7680.80 <br>" +
                                 "- MacOS: Tahoe v26.3.1 <br>" +
                                 "- Java: v21 <br>" +
                                 "- App Version: N/A");
            extent.setSystemInfo("UI Base URL", "https://resonance.dibimbing.id/");
        }
        return extent;
    }
}