package com.flashcloud.testhelper;

import com.flashcloud.root.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CredentialHelper {
    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "inputSubmit")
    private WebElement inputSubmit;

    @FindBy(id = "addCredentialBtn")
    private WebElement addCredentialBtn;

    @FindBy(id = "credential-url")
    private WebElement inputCredUrl;

    @FindBy(id = "credential-username")
    private WebElement inputCredUsername;

    @FindBy(id = "credential-password")
    private WebElement inputCredPassword;

    @FindBy(id = "save-cred")
    private WebElement saveCredBtn;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credTab;

    @FindBy(id = "edit-cred")
    private WebElement editBtn;

    @FindBy(id = "delete-cred")
    private WebElement deleteBtn;

    private WebDriver driver;


    public CredentialHelper(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void addCredential() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credTab);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addCredentialBtn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + "Test URL" + "';", inputCredUrl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + "Test Username" + "';", inputCredUsername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + "Test Password" + "';", inputCredPassword);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveCredBtn);
    }

    public void editFirstCred(String url, String username, String password) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editBtn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + url + "';", inputCredUrl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", inputCredUsername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", inputCredPassword);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveCredBtn);
    }

    public void deleteFirstCred() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
    }

    public String getFirstCreUrl() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credTab);

        List<WebElement> titles = driver.findElements(By.id("in-url-cred"));
        return titles.get(0).getAttribute("innerHTML");
    }

    public String getFirstCredUsername() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credTab);

        List<WebElement> descriptions = driver.findElements(By.id("in-username-cred"));
        return descriptions.get(0).getAttribute("innerHTML");
    }

    public String getFirstCrePassword() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credTab);

        List<WebElement> descriptions = driver.findElements(By.id("in-password-cred"));
        return descriptions.get(0).getAttribute("innerHTML");
    }

    public int getNumberOfCredentials() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credTab);

        List<WebElement> rows = driver.findElements(By.id("cred-rows"));
        return rows.size();
    }

    public void login() {
        User user = new User("admin@storage.com", "admin1234");

        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getUsername() + "';", inputUsername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getPassword() + "';", inputPassword);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", inputSubmit);
    }
}
