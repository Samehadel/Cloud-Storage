package com.flashcloud.testhelper;

import com.flashcloud.root.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NoteHelper {
    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "inputSubmit")
    private WebElement submit;

    private LoginHelper loginHelper;
    private WebDriver driver;
    private User user;

    public NoteHelper(WebDriver driver){
        PageFactory.initElements(driver, this);
        loginHelper = new LoginHelper(driver);
        this.driver = driver;
        user = new User("admin@storage.com", "admin1234");
    }
    public void login(){
        //Login
        username.sendKeys(user.getUsername());
        password.sendKeys(user.getPassword());

        submit.click();
    }
    public void addNote(){
        WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
        noteTab.click(); //Go To Note Tab

        WebElement addNoteBtn = driver.findElement(By.id("addNoteBtn"));
        WebElement noteTitle = driver.findElement(By.id("note-title"));
        WebElement noteDescription = driver.findElement(By.id("note-description"));
        WebElement saveChangesBtn = driver.findElement(By.id("save-note"));



        addNoteBtn.click();

        noteTitle.sendKeys("Insert Test Title");
        noteDescription.sendKeys("Insert Test Description");
        saveChangesBtn.click();
    }

    public void editFirstNote(String title, String desc){
        WebElement noteTitle = driver.findElement(By.id("note-title"));
        WebElement noteDescription = driver.findElement(By.id("note-description"));
        WebElement saveChangesBtn = driver.findElement(By.id("save-note"));
        WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
        List<WebElement> editBtns = driver.findElements(By.id("edit-note"));

        noteTab.click();
        editBtns.get(0).click();

        //Clear Fields
        noteTitle.clear();
        noteDescription.clear();

        noteTitle.sendKeys(title);
        noteDescription.sendKeys(desc);
        saveChangesBtn.click();
    }

    public void deleteFirstNote(){
        WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
        List<WebElement> deleteBtns = driver.findElements(By.id("delete-note"));

        noteTab.click();

        deleteBtns.get(0).click();
    }
    public String getFirstNoteTitle(){
        List<WebElement> titles = driver.findElements(By.id("in-note-title"));
        return titles.get(0).getAttribute("innerHTML");
    }

    public String getFirstNoteDesc(){
        List<WebElement> descriptions = driver.findElements(By.id("in-note-description"));
        return descriptions.get(0).getAttribute("innerHTML");
    }

    public int getNumberOfNotes(){
        List<WebElement> rows = driver.findElements(By.id("notesId"));
        return rows.size();
    }
}
