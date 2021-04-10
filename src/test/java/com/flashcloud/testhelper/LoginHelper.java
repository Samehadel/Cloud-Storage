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

    public LoginHelper(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void loginUser(User user){
        username.sendKeys(user.getUsername());
        password.sendKeys(user.getPassword());

        submit.click();
    }
}
