package UI.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import UI.core.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import UI.utils.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUp() {
        if (extent == null) {
            extent = ExtentManager.getInstance();
        }

        test = extent.createTest(this.getClass().getSimpleName());

        driver = DriverManager.createDriver();
        driver.get("https://resonance.dibimbing.id/");
    }

    public String takeScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";

        String directory = System.getProperty("user.dir") + "/reports/screenshots/";
        new File(directory).mkdirs();

        String destinationPath = directory + fileName;
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(sourceFile.toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "screenshots/" + fileName;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Logika 1: Jika test GAGAL
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test Gagal: " + result.getThrowable());

            if (driver != null) {
                String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
                if (!screenshotPath.isEmpty()) {
                    test.addScreenCaptureFromPath(screenshotPath, "Screenshot Saat Gagal");
                }
            }
        }
        // Logika 2: Jika test BERHASIL
        else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Berhasil");
        }

        // Tutup browser
        if (driver != null) {
            driver.quit();
        }

        // Simpan report
        extent.flush();
    }
}