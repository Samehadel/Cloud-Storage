package com.flashcloud.root;

import com.flashcloud.testhelper.FileHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private FileHelper helper;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() {
        driver = new ChromeDriver();
        helper = new FileHelper(driver);
    }

    @Test
    @Order(1)
    public void testUpload() throws InterruptedException {
        driver.get("http://localhost:" + this.port + "/login");

        helper.login(); //Login First

        Thread.sleep(1000);
        int preRows = helper.numberOfUploadedFiles(); //Count Number of Rows

        helper.uploadFile("C:\\Users\\ALKODS\\Desktop\\Test.txt"); //Upload A File

        //Count Number of Rows Again
        Thread.sleep(1000);
        int newRows = helper.numberOfUploadedFiles();

        Assertions.assertEquals(preRows + 1, newRows);
    }

    @Test
    @Order(2)
    public void testDelete() throws InterruptedException {
        driver.get("http://localhost:" + this.port + "/login");

        helper.login(); //Login First

        Thread.sleep(1000);
        int preRows = helper.numberOfUploadedFiles(); //Count Number of Rows

        helper.deleteFirstFile();

        Thread.sleep(1000);
        int newRows = helper.numberOfUploadedFiles(); //Count Number of Rows Again

        Assertions.assertEquals(preRows - 1, newRows);
    }

    @AfterEach
    public void destroy(){
        if(driver != null)
            driver.quit();
    }

}
