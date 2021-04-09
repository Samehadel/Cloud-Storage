package com.flashcloud.root;

import com.flashcloud.root.model.User;
import com.flashcloud.testhelper.LoginHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginTest {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	private LoginHelper loginHelper;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		loginHelper = new LoginHelper(driver);
	}


	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testRedirect() {
		driver.get("http://localhost:" + this.port + "/home");
		assertEquals("Login", driver.getTitle());
	}
	@Test
	public void testLogin(){
		driver.get("http://localhost:" + this.port + "/login");
		User signingUser = new User("mail@exmaple.com", "sameh1234");
		loginHelper.loginUser(signingUser);

		assertEquals("Home", driver.getTitle());
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}
}
