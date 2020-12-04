package br.com.gerenciadorproposta.functional;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Instant;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseFunctionalTest {
    protected static WebDriver driver;

    @LocalServerPort
    protected int port;

    @BeforeAll
    public static void setUp() throws Exception {
        String hostname = getRemoteWebDriverHostname();
        String port = "4444";
        String url = String.format("http://%s:%s/wd/hub", hostname, port);

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(url), options);
        driver.manage().window().maximize();
    }

    protected String baseUrl() {
        String hostname = getApplicationHostname();
        return String.format("http://%s:%s", hostname, port);
    }

    private String getApplicationHostname() {
        return isNetworkPerBuildEnabled() ? "build" : "localhost";
    }

    private static String getRemoteWebDriverHostname() {
        return isNetworkPerBuildEnabled() ? "selenium-chrome-browser" : "localhost";
    }

    private static boolean isNetworkPerBuildEnabled() {
        String networkPerBuild = System.getenv("FF_NETWORK_PER_BUILD");
        return networkPerBuild != null && !networkPerBuild.equals("false") && !networkPerBuild.equals("0");
    }

    @AfterEach
    public void afterEach(TestInfo testInfo) throws Exception {
        String className = testInfo.getTestClass().map(Class::getSimpleName).orElse("");
        String methodName = testInfo.getTestMethod().map(Method::getName).orElse("");

        takeSnapShot(driver, "Test-" + className + "-" + methodName);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    public static void takeSnapShot(WebDriver webdriver, String baseName) throws Exception {
        String fileWithPath = "./target/" + baseName + Instant.now().toEpochMilli() + ".png";
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        File DestFile = new File(fileWithPath);

        FileUtils.copyFile(SrcFile, DestFile);
    }

}
