package com.flashcloud.testhelper;

import com.flashcloud.root.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NoteHelper {
    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "inputSubmit")
    private WebElement inputSubmit;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "addNoteBtn")
    private WebElement addNoteBtn;

    @FindBy(id = "note-title")
    private WebElement inputNoteTitle;

    @FindBy(id = "note-description")
    private WebElement inputNoteDesc;

    @FindBy(id = "save-note")
    private WebElement saveNoteBtn;

    @FindBy(id = "edit-note")
    private WebElement editNoteBtn;

    @FindBy(id = "delete-note")
    private WebElement deleteNoteBtn;

    private LoginHelper loginHelper;
    private final WebDriver driver;
    private User user;

    public NoteHelper(WebDriver driver){
        PageFactory.initElements(driver, this);
        loginHelper = new LoginHelper(driver);
        this.driver = driver;
        user = new User("admin@storage.com", "admin1234");
    }
    public void login(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getUsername() + "';", inputUsername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getPassword() + "';", inputPassword);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", inputSubmit);
    }
    public void addNote(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noteTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNoteBtn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + "Insert Test Title" + "';", inputNoteTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + "Insert Test Description" + "';", inputNoteDesc);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveNoteBtn);
    }

    public void editFirstNote(String title, String desc){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noteTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editNoteBtn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + title + "';", inputNoteTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + desc + "';", inputNoteDesc);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveNoteBtn);

    }

    public void deleteFirstNote(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noteTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteNoteBtn);
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
