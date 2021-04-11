package com.flashcloud.testhelper;

import com.flashcloud.root.model.User;
import com.flashcloud.root.services.UserService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginHelper {

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "inputSubmit")
    private WebElement submit;

    private User user;

    public LoginHelper(WebDriver driver) {
        PageFactory.initElements(driver, this);
        user = new User("admin@storage.com", "admin1234");
    }

    public void loginUser(){
        username.sendKeys(user.getUsername());
        password.sendKeys(user.getPassword());

        submit.click();
    }
}
