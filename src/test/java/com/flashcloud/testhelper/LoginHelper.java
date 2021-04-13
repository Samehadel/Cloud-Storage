package com.flashcloud.testhelper;

import com.flashcloud.root.model.User;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginHelper {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "inputSubmit")
    private WebElement inputSubmit;

    private User user;
    private final WebDriver driver;

    public LoginHelper(WebDriver driver) {
        PageFactory.initElements(driver, this);
        user = new User("admin@storage.com", "admin1234");
        this.driver = driver;
    }

    public void loginUser(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getUsername() + "';", inputUsername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getPassword() + "';", inputPassword);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", inputSubmit);
    }
}
