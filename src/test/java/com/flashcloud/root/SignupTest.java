package com.flashcloud.root;

import com.flashcloud.root.model.User;
import com.flashcloud.root.services.UserService;
import com.flashcloud.testhelper.SignupHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserService userService;

    private SignupHelper helper;

    private static WebDriver driver;

    private static User user;

    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        user = new User("Admin", "Admin", "admin1200@storage.com", "admin1234");
    }

    @BeforeEach
    public void init(){
        this.driver = new ChromeDriver();
        helper = new SignupHelper(driver, userService);
    }

    /*
        Test The Signup Page Accessability
     */
    @Test
    public void testSignupPageAccessability() {
        driver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());
    }

    /*
     This test signs up a new user, logs that user in, verifies that they can access the home page,
     then logs out and verifies that the home page is no longer accessible.
     */
    @Test
    public void testUserLifeCycle() throws InterruptedException {
        driver.get("http://localhost:" + port + "/signup");

        helper.signup(user);
        assertEquals("Login", driver.getTitle());

        helper.login(user);
        Assertions.assertEquals("Home", driver.getTitle());

        helper.logout();
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
