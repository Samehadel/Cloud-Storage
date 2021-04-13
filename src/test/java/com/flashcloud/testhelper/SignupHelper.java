package com.flashcloud.testhelper;

import com.flashcloud.root.model.User;
import com.flashcloud.root.services.UserService;
import com.flashcloud.root.services.impl.UserServiceImp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class SignupHelper {

    @FindBy(id = "inputFirstName")
    private WebElement firstName;

    @FindBy(id = "inputLastName")
    private WebElement lastName;

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "inputSubmit")
    private WebElement submit;

    private WebDriver driver;

    private UserService userService;

    public SignupHelper(WebDriver driver, UserService userService) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.userService = userService;
    }

    public void signup(User user) {
        firstName.sendKeys(user.getFirstName());
        lastName.sendKeys(user.getLastName());
        username.sendKeys(user.getUsername());
        password.sendKeys(user.getPassword());

        submit.click();
    }

    public User getUser(String username){
        return userService.getUser(username);
    }

    public void login(User user) {
        username.sendKeys(user.getUsername());
        password.sendKeys(user.getPassword());
        submit.click();
    }

    public void logout(){
        WebElement logoutBtn = driver.findElement(By.id("logout-btn"));
        logoutBtn.click();
    }
}
