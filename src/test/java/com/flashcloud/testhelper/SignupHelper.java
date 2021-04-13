package com.flashcloud.testhelper;

import com.flashcloud.root.model.User;
import com.flashcloud.root.services.UserService;
import com.flashcloud.root.services.impl.UserServiceImp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class SignupHelper {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "inputSubmit")
    private WebElement inputSubmit;

    @FindBy(id = "logout-btn")
    private WebElement logout;

    private WebDriver driver;

    private UserService userService;

    public SignupHelper(WebDriver driver, UserService userService) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.userService = userService;
    }

    public void signup(User user) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getFirstName() + "';", inputFirstName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getLastName() + "';", inputLastName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getUsername() + "';", inputUsername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getPassword() + "';", inputPassword);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", inputSubmit);

    }

    public void login(User user) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getUsername() + "';", inputUsername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + user.getPassword() + "';", inputPassword);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", inputSubmit);

    }

    public void logout(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);
    }
}
