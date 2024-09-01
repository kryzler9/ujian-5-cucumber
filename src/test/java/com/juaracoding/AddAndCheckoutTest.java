package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.AddAndCheckoutPage;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.utils.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AddAndCheckoutTest {

    private WebDriver driver;

    private LoginPage loginPage;
    private AddAndCheckoutPage addAndCheckoutPage = new AddAndCheckoutPage();

    public AddAndCheckoutTest(){
        driver = Hooks.driver;
    }

    // #005
    @Given("I am logged into SwagLabs")
    public void i_am_logged_into_swaglabs(){
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
        loginPage = new LoginPage();
        addAndCheckoutPage =  new AddAndCheckoutPage();
        loginPage.login("standard_user","secret_sauce");
        loginPage.setBtnLogin();
    }

    @When("I added two products")
    public void i_added_two_products(){
        DriverSingleton.delay(2);
        addAndCheckoutPage.setAddBackpack();
        DriverSingleton.delay(2);
        addAndCheckoutPage.setAddOnesie();
        DriverSingleton.delay(2);
    }

    @And("I click my cart button")
    public void i_click_my_cart_button(){
        DriverSingleton.delay(2);
        addAndCheckoutPage.setMyCart();
        DriverSingleton.delay(2);
        addAndCheckoutPage.setFirstCheckout();
    }

    @And("I fill the Name and Postal Code")
    public void i_fill_the_name_and_postal_code(){
        DriverSingleton.delay(2);
        addAndCheckoutPage.yourInfo("Daffa","Z","1987");
        DriverSingleton.delay(2);
        addAndCheckoutPage.setContinueCheckout();
    }

    @And("I should be redirected to the checkout page and click finish")
    public void i_should_be_redirected_to_the_checkout_page_and_click_finish(){
        DriverSingleton.delay(2);
        addAndCheckoutPage.setFinishCheckout();
    }

    @Then("I should see Thank you for your order")
    public void i_should_see_thank_you_for_your_order(){
        DriverSingleton.delay(2);
        Assert.assertEquals(addAndCheckoutPage.getTxtSuccessCheckout(),"Thank you for your order!");
        addAndCheckoutPage.setBackHome();
        loginPage.logout();
        DriverSingleton.delay(2);
    }

    // #006
    @When("I empty the postal code")
    public void i_empty_the_postal_code(){
        DriverSingleton.delay(2);
        addAndCheckoutPage.yourInfo("Daffa","Z","");
        addAndCheckoutPage.setContinueCheckout();
    }

    @Then("I should see Postal Code is required")
    public void i_should_see_postal_code_is_required(){
        DriverSingleton.delay(2);
        Assert.assertEquals(addAndCheckoutPage.getTxtPostalCodeRequired(),"Error: Postal Code is required");
        loginPage.logout();
        DriverSingleton.delay(2);
    }

    // #007
    @When("I empty the last name")
    public void i_empty_the_last_name(){
        DriverSingleton.delay(2);
        addAndCheckoutPage.yourInfo("Daffa","","1987");
        addAndCheckoutPage.setContinueCheckout();
    }

    @Then("I should see Last Name is required")
    public void i_should_see_last_name_is_required(){
        DriverSingleton.delay(2);
        Assert.assertEquals(addAndCheckoutPage.getTxtLastNameRequired(),"Error: Last Name is required");
        loginPage.logout();
        DriverSingleton.delay(2);
        DriverSingleton.closeObjectInstance();
    }

}
