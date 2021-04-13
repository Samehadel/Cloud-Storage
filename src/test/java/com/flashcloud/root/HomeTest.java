package com.flashcloud.root;

import com.flashcloud.root.model.User;
import com.flashcloud.testhelper.SignupHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

/*
 This test verifies that the home page is not accessible without logging in.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeTest {

    @LocalServerPort
    private int port;

    private static WebDriver driver;

    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void init(){
        this.driver = new ChromeDriver();
    }

    @Test
    public void testHomePageAccessability() throws InterruptedException {
        driver.get("http://localhost:" + this.port + "/home");

        Thread.sleep(1000);
        String location = driver.getTitle();

        Assertions.assertNotEquals("Home", location);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
