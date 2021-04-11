package com.flashcloud.root;

import com.flashcloud.testhelper.LoginHelper;
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
    private NoteHelper noteHelper;

    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        noteHelper = new NoteHelper(driver);
    }
    @Test
    public void testAddNote(){

        driver.get("http://localhost:" + this.port + "/login");
        noteHelper.login(); //Login First

        int prevRows = noteHelper.getNumberOfNotes();
        noteHelper.addNote();
        int newRows = noteHelper.getNumberOfNotes();

        Assertions.assertEquals(prevRows + 1, newRows);
    }

    @Test
    public void testEditNote(){
        driver.get("http://localhost:" + this.port + "/login");
        noteHelper.login(); //Login First

        String newTitle = "Test New Title";
        String newDesc = "Test New Description";

        noteHelper.editFirstNote(newTitle, newDesc);

        String updatedTitle = noteHelper.getFirstNoteTitle();
        String updatedDesc = noteHelper.getFirstNoteDesc();

        Assertions.assertEquals(newTitle, updatedTitle);
        Assertions.assertEquals(newDesc, updatedDesc);
    }

    @Test
    public void testDelete(){
        driver.get("http://localhost:" + this.port + "/login");
        noteHelper.login(); //Login First

        int prevRows = noteHelper.getNumberOfNotes();
        noteHelper.deleteFirstNote();
        int newRows = noteHelper.getNumberOfNotes();

        Assertions.assertEquals(prevRows - 1, newRows);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
