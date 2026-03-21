package API.base;

import API.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.lang.reflect.Method;

public class BaseTest {
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "https://resonance.dibimbing.id";

        if (extent == null) {
            extent = ExtentManager.getInstance();
        }
    }

    @BeforeMethod
    public void startTest(Method method) {
        test = extent.createTest("API: " + method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test Gagal: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Berhasil");
        }
        extent.flush();
    }
}