package br.com.gerenciadorproposta.functional;

import lombok.extern.slf4j.Slf4j;
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

import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class BaseFunctionalTest {
    protected static WebDriver driver;

    @LocalServerPort
    protected int port;

    @BeforeAll
    public static void setUp() throws Exception {
        String addr = getChromeAddr();
        String port = getChromePort();
        String url = String.format("http://%s:%s/wd/hub", addr, port);

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(url), options);
        driver.manage().window().maximize();
    }

    protected String baseUrl() {
        String hostname = getHostname();
        return String.format("http://%s:%s", hostname, port);
    }

    private String getHostname() {
        if (isNetworkPerBuildEnabled()) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                throw new RuntimeException("Não foi possível obter o IP.");
            }
        }
        return Optional.ofNullable(System.getenv("HOSTNAME"))
                .orElse("localhost");
    }

    private static String getChromePort() {
        return Optional.ofNullable(System.getenv("SELENIUM_STANDALONE_CHROME_PORT_4444_TCP_PORT"))
                .orElse("4444");
    }

    private static String getChromeAddr() {
        if (isNetworkPerBuildEnabled()) {
            return "selenium-chrome-browser";
        }
        return Optional.ofNullable(System.getenv("SELENIUM_STANDALONE_CHROME_PORT_4444_TCP_ADDR"))
                .orElse("localhost");
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
