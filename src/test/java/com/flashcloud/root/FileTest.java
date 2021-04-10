package com.flashcloud.root;

import com.flashcloud.testhelper.FileHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private FileHelper fileHelper;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() {
        driver = new ChromeDriver();
        fileHelper = new FileHelper(driver);
    }

    @Test
    public void testUpload() {
        driver.get("http://localhost:" + this.port + "/login");

        fileHelper.login(); //Login First

        int preRows = fileHelper.numberOfUploadedFiles(); //Count Number of Rows

        fileHelper.uploadFile("C:\\Users\\ALKODS\\Desktop\\Test.txt"); //Upload A File

        //Count Number of Rows Again
        int newRows = fileHelper.numberOfUploadedFiles();

        Assertions.assertEquals(preRows + 1, newRows);
    }

    @Test
    public void testDelete(){
        driver.get("http://localhost:" + this.port + "/login");

        fileHelper.login(); //Login First

        int preRows = fileHelper.numberOfUploadedFiles(); //Count Number of Rows

        fileHelper.deleteFirstFile();

        int newRows = fileHelper.numberOfUploadedFiles(); //Count Number of Rows Again

        Assertions.assertEquals(preRows - 1, newRows);
    }

    @AfterEach
    public void destroy(){
        if(driver != null)
            driver.quit();
    }

}
