package com.flashcloud.root;

import com.flashcloud.testhelper.CredentialHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private CredentialHelper helper;

    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        helper = new CredentialHelper(driver);
    }

    @Test
    public void addCredential(){

        driver.get("http://localhost:" + this.port + "/login");
        helper.login(); //Login First

        int prevRows = helper.getNumberOfCredentials();

        helper.addCredential();

        int newRows = helper.getNumberOfCredentials();

        Assertions.assertEquals(prevRows + 1, newRows);
    }

    @Test
    public void testEditCred(){
        driver.get("http://localhost:" + this.port + "/login");
        helper.login(); //Login First

        String newUrl = "localhost:8082/Test";
        String newUsername = "admin@test.com";
        String newPassword = "admin0123";

        helper.editFirstCred(newUrl, newUsername, newPassword);

        String updatedUrl = helper.getFirstCreUrl();
        String updatedUsername = helper.getFirstCredUsername();
        String updatedPassword = helper.getFirstCrePassword();

        Assertions.assertEquals(newUrl, updatedUrl);
        Assertions.assertEquals(newUsername, updatedUsername);
        Assertions.assertNotEquals(newPassword, updatedPassword);
    }

    @Test
    public void testDelete(){
        driver.get("http://localhost:" + this.port + "/login");
        helper.login(); //Login First

        int prevRows = helper.getNumberOfCredentials();
        helper.deleteFirstCred();
        int newRows = helper.getNumberOfCredentials();

        Assertions.assertEquals(prevRows - 1, newRows);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
