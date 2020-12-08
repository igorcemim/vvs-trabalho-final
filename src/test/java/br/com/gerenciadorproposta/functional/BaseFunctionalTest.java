package br.com.gerenciadorproposta.functional;

import br.com.gerenciadorproposta.util.FileUtil;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Instant;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class BaseFunctionalTest {

    protected WebDriver driver;

    protected WebDriverWait wait;

    @LocalServerPort
    protected int port;

    @Autowired
    private FileUtil fileUtil;

    @Value("${system.application.hostname}")
    protected String applicationHostname;

    @Value("${system.remote-web-driver.hostname}")
    protected String remoteWebDriverHostname;

    @BeforeAll
    public void setUp() throws Exception {
        String port = "4444";
        String url = String.format("http://%s:%s/wd/hub", remoteWebDriverHostname, port);

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(url), options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 30);
    }

    protected String baseUrl() {
        return String.format("http://%s:%s", applicationHostname, port);
    }

    @AfterEach
    public void afterEach(TestInfo testInfo) {
        String className = testInfo.getTestClass().map(Class::getSimpleName).orElse("");
        String methodName = testInfo.getTestMethod().map(Method::getName).orElse("");

        takeScreenshot("Test-" + className + "-" + methodName);
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }

    protected void takeScreenshot(String baseName) {
        String destinationPath = "./target/" + baseName + Instant.now().toEpochMilli() + ".png";
        TakesScreenshot scrShot = ((TakesScreenshot) driver);

        File sourceFile = scrShot.getScreenshotAs(OutputType.FILE);

        fileUtil.copy(sourceFile, destinationPath);
    }

    protected void waitAndAcceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}
