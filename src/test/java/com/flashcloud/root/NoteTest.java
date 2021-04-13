package com.flashcloud.root;

import com.flashcloud.testhelper.NoteHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
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
    @Order(1)
    public void testAddNote() throws InterruptedException {

        driver.get("http://localhost:" + this.port + "/login");
        helper.login(); //Login First

        int prevRows = helper.getNumberOfNotes();

        helper.addNote();

        int newRows = helper.getNumberOfNotes();

        Assertions.assertEquals(prevRows + 1, newRows);
    }

    @Test
    @Order(2)
    public void testEditNote() throws InterruptedException {
        driver.get("http://localhost:" + this.port + "/login");
        helper.login(); //Login First

        String newTitle = "Edit Test New Title";
        String newDesc = "Edit Test New Description";

        helper.editFirstNote(newTitle, newDesc);

        String updatedTitle = helper.getFirstNoteTitle();
        String updatedDesc = helper.getFirstNoteDesc();

        Assertions.assertEquals(newTitle, updatedTitle);
        Assertions.assertEquals(newDesc, updatedDesc);
    }

    @Test
    @Order(3)
    public void testDelete() throws InterruptedException {
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
