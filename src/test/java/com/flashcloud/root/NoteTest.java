package com.flashcloud.root;

import com.flashcloud.testhelper.NoteHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private NoteHelper helper;

    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        helper = new NoteHelper(driver);
    }
    @Test
    public void testAddNote(){

        driver.get("http://localhost:" + this.port + "/login");
        helper.login(); //Login First

        int prevRows = helper.getNumberOfNotes();
        helper.addNote();
        int newRows = helper.getNumberOfNotes();

        Assertions.assertEquals(prevRows + 1, newRows);
    }

    @Test
    public void testEditNote(){
        driver.get("http://localhost:" + this.port + "/login");
        helper.login(); //Login First

        String newTitle = "Test New Title";
        String newDesc = "Test New Description";

        helper.editFirstNote(newTitle, newDesc);

        String updatedTitle = helper.getFirstNoteTitle();
        String updatedDesc = helper.getFirstNoteDesc();

        Assertions.assertEquals(newTitle, updatedTitle);
        Assertions.assertEquals(newDesc, updatedDesc);
    }

    @Test
    public void testDelete(){
        driver.get("http://localhost:" + this.port + "/login");
        helper.login(); //Login First

        int prevRows = helper.getNumberOfNotes();
        helper.deleteFirstNote();
        int newRows = helper.getNumberOfNotes();

        Assertions.assertEquals(prevRows - 1, newRows);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
