package com.flashcloud.root;

import com.flashcloud.testhelper.CredentialHelper;
import com.flashcloud.testhelper.NoteHelper;
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
    private CredentialHelper credHelper;

    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        credHelper = new CredentialHelper(driver);
    }

    @Test
    public void addCredential(){

        driver.get("http://localhost:" + this.port + "/login");
        credHelper.login(); //Login First

        int prevRows = credHelper.getNumberOfCredentials();
        credHelper.addCredential();
        int newRows = credHelper.getNumberOfCredentials();

        Assertions.assertEquals(prevRows + 1, newRows);
    }

    @Test
    public void testEditCred(){
        driver.get("http://localhost:" + this.port + "/login");
        credHelper.login(); //Login First

        String newUrl = "localhost:8082/Test";
        String newUsername = "admin@test.com";
        String newPassword = "admin0123";

        credHelper.editFirstCred(newUrl, newUsername, newPassword);

        String updatedUrl = credHelper.getFirstCreUrl();
        String updatedUsername = credHelper.getFirstCredUsername();
        String updatedPassword = credHelper.getFirstCrePassword();

        Assertions.assertEquals(newUrl, updatedUrl);
        Assertions.assertEquals(newUsername, updatedUsername);
        Assertions.assertNotEquals(newPassword, updatedPassword);
    }

    @Test
    public void testDelete(){
        driver.get("http://localhost:" + this.port + "/login");
        credHelper.login(); //Login First

        int prevRows = credHelper.getNumberOfCredentials();
        credHelper.deleteFirstCred();
        int newRows = credHelper.getNumberOfCredentials();

        Assertions.assertEquals(prevRows - 1, newRows);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
