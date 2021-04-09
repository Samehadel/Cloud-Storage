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

    private static User signedUser;

    @BeforeAll
    public static void init(){
        WebDriverManager.chromedriver().setup();
        signedUser = new User("Sameh", "Adel", "mail2@exmaple.com", "sameh1234");
    }

    @BeforeEach
    public void reInit(){
        this.driver = new ChromeDriver();
        helper = new SignupHelper(driver, userService);
    }

    @Test
    public void getSignupPage() {
        driver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());
    }

    @Test
    public void testSignup(){
        driver.get("http://localhost:" + port + "/signup");

        helper.signup(signedUser);

        User registeredUser = helper.getUser(signedUser.getUsername());

        assertEquals(signedUser.getUsername(), registeredUser.getUsername());
    }

    @Test
    public void testUserId(){
        User registeredUser = helper.getUser(signedUser.getUsername());
        assertNotEquals(0, registeredUser.getUserId());
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
