package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.utils.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class LoginTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    private WebDriver driver;

    private LoginPage loginPage = new LoginPage();

    public LoginTest(){
        driver = Hooks.driver;
    }

    // #001

    @Given("I am on the SwagLabs login page")
    public void i_am_on_the_swaglabs_login_page(){
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
        loginPage = new LoginPage();
        DriverSingleton.delay(1);
    }

    @When("I enter a valid username and password")
    public void i_enter_a_valid_username_and_password(){
        loginPage.login("standard_user","secret_sauce");
        DriverSingleton.delay(1);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button(){
        loginPage.setBtnLogin();
    }

    @Then("I should be redirected to the products page")
    public void i_should_be_redirected_to_the_products_page(){
        Assert.assertEquals(loginPage.getTxtProducts(), "Products");
        DriverSingleton.delay(3);
    }

    // #002
    @When("I enter an invalid username and password")
    public void i_enter_an_invalid_username_and_password(){
        loginPage.login("new_user","mayo_sauce");
    }

    @Then("I should see username and password do not match")
    public void i_should_see_username_and_password_do_not_match(){
        DriverSingleton.delay(2);
        Assert.assertEquals(loginPage.getTxtInvalidCredentials(), "Epic sadface: Username and password do not match any user in this service");
    }

    // #003
    @When("I enter a valid username without password")
    public void i_enter_a_valid_username_without_password(){
        loginPage.clearUsername();
        loginPage.clearPassword();
        DriverSingleton.delay(2);
        loginPage.login("standar_user","");
    }

    @Then("I should see password required")
    public void i_should_see_password_required(){
        DriverSingleton.delay(2);
        Assert.assertEquals(loginPage.getTxtPasswordRequired(),"Epic sadface: Password is required");
    }

    // #004
    @When("I enter nothing on username and a valid password")
    public void i_enter_nothing_on_username_and_a_valid_password(){
        loginPage.clearUsername();
        loginPage.clearPassword();
        DriverSingleton.delay(2);
        loginPage.login("","secret_sauce");
    }

    @Then("I should see username required")
    public void i_should_see_username_required(){
        DriverSingleton.delay(2);
        Assert.assertEquals(loginPage.getTxtUsernameRequired(),"Epic sadface: Username is required");
        DriverSingleton.closeObjectInstance();
    }
}
