package com.flashcloud.testhelper;

import com.flashcloud.root.model.File;
import com.flashcloud.root.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FileHelper {

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "inputSubmit")
    private WebElement submit;

    private WebDriver driver;


    public FileHelper(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void login(){
        User user = new User("admin@storage.com", "admin1234");

        //Login
        username.sendKeys(user.getUsername());
        password.sendKeys(user.getPassword());
        submit.click();
    }

    public void uploadFile(String path){
        //Get Web Elements
        WebElement choseFile = driver.findElement(By.cssSelector("input[type='file']"));
        WebElement uploadBtn = driver.findElement(By.id("uploadBtn"));

        //Upload A File
        choseFile.sendKeys(path);
        uploadBtn.click();
    }

    public void deleteFirstFile(){
        List<WebElement> rows = driver.findElements(By.id("deleteFile"));
        rows.get(0).click();
    }

    public int numberOfUploadedFiles(){
        List<WebElement> rows = driver.findElements(By.id("filesRows"));

        return rows.size();
    }
}
