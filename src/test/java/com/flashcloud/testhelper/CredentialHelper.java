package com.flashcloud.testhelper;

import com.flashcloud.root.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CredentialHelper {
    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "inputSubmit")
    private WebElement submit;

    private WebDriver driver;


    public CredentialHelper(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void addCredential(){
        WebElement addCredBtn = driver.findElement(By.id("addCredentialBtn"));
        WebElement creUrl = driver.findElement(By.id("credential-url"));
        WebElement credUsername = driver.findElement(By.id("credential-username"));
        WebElement credUPassword = driver.findElement(By.id("credential-password"));
        WebElement saveChangesBtn = driver.findElement(By.id("save-cred"));
        WebElement credTab = driver.findElement(By.id("nav-credentials-tab"));

        credTab.click(); //Go To Note Tab
        addCredBtn.click();

        creUrl.sendKeys("Test URL");
        credUsername.sendKeys("Test Username");
        credUPassword.sendKeys("Test Password");
        saveChangesBtn.click();
    }

    public void editFirstCred(String url, String username, String password){
        WebElement creUrl = driver.findElement(By.id("credential-url"));
        WebElement credUsername = driver.findElement(By.id("credential-username"));
        WebElement credUPassword = driver.findElement(By.id("credential-password"));
        WebElement saveChangesBtn = driver.findElement(By.id("save-cred"));
        WebElement credTab = driver.findElement(By.id("nav-credentials-tab"));

        List<WebElement> editBtns = driver.findElements(By.id("edit-cred"));

        credTab.click();
        editBtns.get(0).click();

        //Clear Fields
        creUrl.clear();
        credUsername.clear();
        credUPassword.clear();

        creUrl.sendKeys(url);
        credUsername.sendKeys(username);
        credUPassword.sendKeys(password);

        saveChangesBtn.click();
    }
    public void deleteFirstCred(){
        WebElement credTab = driver.findElement(By.id("nav-credentials-tab"));
        List<WebElement> deleteBtns = driver.findElements(By.id("delete-cred"));

        credTab.click();
        deleteBtns.get(0).click();
    }
    public String getFirstCreUrl(){
        List<WebElement> titles = driver.findElements(By.id("in-url-cred"));
        return titles.get(0).getAttribute("innerHTML");
    }

    public String getFirstCredUsername(){
        List<WebElement> descriptions = driver.findElements(By.id("in-username-cred"));
        return descriptions.get(0).getAttribute("innerHTML");
    }

    public String getFirstCrePassword(){
        List<WebElement> descriptions = driver.findElements(By.id("in-password-cred"));
        return descriptions.get(0).getAttribute("innerHTML");
    }

    public int getNumberOfCredentials(){
        WebElement noteTab = driver.findElement(By.id("nav-credentials-tab"));
        noteTab.click(); //Go To Note Tab

        List<WebElement> rows = driver.findElements(By.id("cred-rows"));
        return rows.size();
    }

    public void login(){
        User user = new User("admin@storage.com", "admin1234");

        //Login
        username.sendKeys(user.getUsername());
        password.sendKeys(user.getPassword());
        submit.click();
    }
}
