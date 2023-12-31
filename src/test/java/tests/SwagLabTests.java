package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import retry.BootsnipRetry;

public class SwagLabTests extends BasicTest {
    //Test #1
    @Test(priority = 1, retryAnalyzer = BootsnipRetry.class)
    public void verifyErrorIsDisplayedWhenUsernameIsMissing() {
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(),
                "Epic sadface: Username is required",
                "Error message should be present if username is missing");
    }

    //Test #2
    @Test(priority = 2, retryAnalyzer = BootsnipRetry.class)
    public void verifyErrorIsDisplayedWhenPasswordIsMissing() {
        String username = "standard_user";

        loginPage.enterUsername(username);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(), "Epic sadface: Username is required",
                "Error message should be present if password is missing");
    }

    //Test #3
    @Test
    public void verifyErrorIsDisplayedWhenCredentialsAreWrong() {
        String username = "standard_user";
        String password = "invalidpassword";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(),
                "Epic sadface: Password is required", "Error message for " +
                        "wrong username and password should be present");
    }

    //Test #4
    @Test
    public void verifyErrorIsDisplayedWhenUserIsLocked() {
        String username = "locked_out_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Error message for locked out user should be present");
    }

    //Test #5
    @Test
    public void verifySuccessfulLogin() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        wait.until(ExpectedConditions.urlContains("/inventory.html"));
        wait.withMessage("Url doesn't contain /inventory.html");
        topNavPage.clickOnBurger();
        leftNavPage.waitLeftNavMenu();
        Assert.assertTrue(leftNavPage.doesLogoutButtonExist(), "Logout should exist.");
        leftNavPage.clickOnLogoutButton();
    }

    //Test #6
    @Test
    public void addingProductsToCart() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        wait
                .withMessage("Url doesn't contain /inventory.html")
                .until(ExpectedConditions.urlContains("/inventory.html"));
        Assert.assertTrue(inventoryPage.findSauceLabsItem(),
                "Sauce Labs Backpack not found in the cart");
        inventoryPage.clickAddToCart();
        Assert.assertTrue(inventoryPage.checkIfThereIsRemoveButton(),
                "Remove button is not present");
        int numberOfItems = topNavPage.getNumberOfItemsFromCart();

        Assert.assertEquals(numberOfItems, 1,
                "Number of items in the cart should be 1");
    }

    //Cart Page sheet #1
    @Test(priority = 3, retryAnalyzer = BootsnipRetry.class)
    public void verifyTheUrl() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();

        Assert.assertEquals(loginPage.getCurrentUrl(), url + "cart.html",
                "Url should be https://www.saucedemo.com/cart.html");
    }

    //Cart Page sheet #2
    @Test
    public void verifyTheTitlePage() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();

        Assert.assertEquals(inventoryPage.getCartPageTitle(),
                "Swag Labs", "Title should be Swag Labs.");
    }

    //Cart page sheet #3
    @Test
    public void verifyTheTitleInHeader() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        Assert.assertEquals(topNavPage.getHeaderTitleText(),
                "Swag Labs",
                "Title in header should be Swag Labs");
    }

    //Cart page sheet #4
    @Test
    public void verifyIfTheHamburgerMenuButtonIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.waitForBurgerButton();
    }

    //Cart page sheet #5
    @Test
    public void verifyIfTheCartIconIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.waitForBurgerButton();
        topNavPage.waitForCartIcon();
    }

    //Cart page sheet #6
    @Test
    public void verifyIfTheHamburgerMenuButtonIsEnabled() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnBurger();
        Assert.assertTrue(topNavPage.isBurgerButtonEnabled(), "Hamburger button should be enabled");
    }

    //Cart page sheet #7
    @Test
    public void verifyIfTheCartIconIsEnabled() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(topNavPage.isCartIconEnabled(),
                "Cart icon should be enabled");
    }

    //Cart page sheet #8
    @Test
    public void verifyIfTheHamburgerButtonIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnBurger();
        leftNavPage.waitLeftNavMenu();
    }

    //Cart page sheet #9
    @Test
    public void verifyIfTheCartIconIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();

        Assert.assertEquals(loginPage.getCurrentUrl(),
                url + "/cart.html",
                "Should redirect to cart page after clicking the cart icon.");
    }

    //Cart page sheet #10
    @Test
    public void verifyIfTheCartIconHasCorrectNumberOfAddedItems() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();

        int itemsBefore = topNavPage.getNumberOfItemsFromCart();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(inventoryPage.checkIfThereIsRemoveButton(),
                "There should be a remove button");

        int itemsAfter = topNavPage.getNumberOfItemsFromCart();

        Assert.assertEquals(itemsAfter, itemsBefore + 1,
                "Number of items in the cart should be increased");
    }

    //Cart page sheet #11
    @Test
    public void verifyTheSubHeaderTitle() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();

        Assert.assertEquals(topNavPage.getSubheaderTitleText(), "Your Cart",
                "Subheader title should be: 'Your Cart'");
    }

    //Cart page sheet #12
    @Test
    public void verifyTheTotalNumberOfMenuOptions() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnBurger();

        leftNavPage.waitLeftNavMenu();
        Assert.assertEquals(leftNavPage.getNumberOfMenuOptions(), 4,
                "Total number of options in menu should be four.");
    }

    //Cart page sheet #13
    @Test
    public void verifyTheSpellingOfAllOptionsInMenu() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnBurger();

        leftNavPage.waitLeftNavMenu();

        Assert.assertTrue(leftNavPage.spellingOfOptions(),
                "Spelling of options in menu is incorrect.");
    }

    //Cart page sheet #14
    @Test
    public void verifyIfAllItemsOptionIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnBurger();

        leftNavPage.waitLeftNavMenu();
        leftNavPage.clickAllItems();

        Assert.assertEquals(loginPage.getCurrentUrl(),
                url + "/inventory.html",
                "Should redirect to the products page.");
    }

    //Cart page sheet #15
    @Test
    public void verifyIfAboutOptionIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnBurger();

        leftNavPage.waitLeftNavMenu();

        leftNavPage.clickAbout();

        Assert.assertEquals(loginPage.getCurrentUrl(),
                "https://saucelabs.com/",
                "Should redirect to the sauce labs website");
    }

    //Cart page sheet #16
    @Test
    public void verifyIfLogoutOptionIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnBurger();

        leftNavPage.waitLeftNavMenu();
        leftNavPage.clickOnLogoutButton();

        Assert.assertTrue(
                loginPage.doesUsernameInputExist(),
                "Should redirect to login page");

    }

    //Cart page sheet #17
    @Test
    public void verifyIfResetAppStateIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        boolean doesBadgeExist = topNavPage.checkIfCartBadgeExists();

        topNavPage.clickOnCartButton();
        topNavPage.clickOnBurger();
        leftNavPage.waitLeftNavMenu();
        leftNavPage.clickResetAppState();
        boolean doesBadgeExistAfterReset = topNavPage.checkIfCartBadgeExists();

        Assert.assertEquals(doesBadgeExistAfterReset,
                !doesBadgeExist, "Reset option should reset the app");
    }

    //Cart page sheet #18
    @Test
    public void verifyIfTheEkisButtonIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnBurger();
        leftNavPage.waitXButton();
    }

    //Cart page sheet #19
    @Test
    public void verifyIfTheEkisButtonsIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnBurger();
        leftNavPage.waitXButton();
        leftNavPage.clickXButton();
        leftNavPage.waitLeftNavMenuDissapears();
    }

    //Cart page sheet #20
    @Test
    public void verifyIfTheItemsAddedIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();

        Assert.assertTrue(cartPage.checkIfAddedItemsExist(),
                "Added items should be on the page.");
    }

    //Cart page sheet #21
    @Test
    public void verifyIfTheItemsTitleIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();

        Assert.assertTrue(cartPage.checkIfAddedItemsExist(),
                "Title of the added item should be visible.");
    }

    //Cart page sheet #22
    @Test
    public void verifyIfTheItemsDescriptionIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(),
                "Description of the item in cart should be visible.");
    }

    //Cart page sheet #23
    @Test
    public void verifyIfTheItemsPriceIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(),
                "Price of the item in cart should be visible.");
    }

    //Cart page sheet #24
    @Test
    public void verifyIfTheItemsQuantityIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(),
                "Quantity of the added item should be visible.");
    }

    //Cart page sheet #25
    @Test
    public void verifyIfTheItemsTitleIsClickable() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(),
                "Item should exist in cart");
        cartPage.waitForItemTitleToBeClickable();
    }

    //Cart page sheet #26
    @Test
    public void verifyIfTheItemsTitleIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();

        Assert.assertTrue(cartPage.checkIfAddedItemsExist(),
                "Item should exist in cart");
        cartPage.waitForItemTitleToBeClickable();

        Assert.assertTrue(loginPage.getCurrentUrl().contains("inventory-item.html"),
                "Should be redirected to the item's page.");
    }

    //Cart page sheet #27
    @Test
    public void verifyIfTheRemoveButtonIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        cartPage.waitForRemoveButtonToBeVisible();

    }

    //Cart page sheet #28
    @Test
    public void verifyIfTheRemoveButtonIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        cartPage.clickOnRemoveButton();

        Assert.assertFalse(cartPage.checkIfAddedItemsExist(),
                "The item should disappear after removing");
    }

    //Cart page sheet #29
    @Test
    public void verifyIfTheContinueShoppingButtonIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        cartPage.waitForContinueShoppingButtonToBeVisible();
    }

    //Cart page sheet #30
    @Test
    public void verifyIfTheContinueShoppingButtonIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();

        cartPage.waitForContinueShoppingButtonToBeVisible();
        cartPage.clickContinueShoppingButton();

        wait.withMessage("Should be redirected to the item's page")
                .until(ExpectedConditions.urlContains("/inventory.html"));
    }

    //Cart page sheet #31
    @Test
    public void verifyIfTheCheckoutButtonIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        cartPage.waitForCheckoutButtonToBeVisible();
    }

    //Cart page sheet #32
    @Test
    public void verifyIfTheCheckoutButtonIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        cartPage.waitForCheckoutButtonToBeVisible();
        cartPage.clickCheckoutButton();

        Assert.assertEquals(loginPage.getCurrentUrl(), url + "checkout-step-one.html",
                "Should be redirected to Checkout page");
    }

    //Cart page sheet #33
    @Test
    public void verifyIfTheTwitterButtonIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footer.scrollToFooter();
        footer.waitForTwitterIconToBeVisible();

    }

    //Cart page sheet #34
    @Test
    public void verifyIfTheFacebookButtonIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footer.scrollToFooter();
        footer.waitForFacebookIconToBeVisible();

    }

    //Cart page sheet #35
    @Test
    public void verifyIfTheLinkedinButtonIsPresented() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footer.scrollToFooter();
        footer.waitForLinkedinIconToBeVisible();

    }

    //Cart page sheet #36
    @Test
    public void verifyIfTheTwitterButtonIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footer.scrollToFooter();
        footer.waitForTwitterIconToBeVisible();
        footer.clickOnTwitterIcon();
        footer.redirectToTwitter();
    }

    //Cart page sheet #37
    @Test
    public void verifyIfFacebookButtonWorksInTheCartPage() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footer.scrollToFooter();
        footer.waitForFacebookIconToBeVisible();
        footer.clickOnFacebookIcon();
        footer.redirectToFacebook();
    }

    //Cart page sheet #38
    @Test
    public void verifyIfTheLinkedinButtonIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footer.scrollToFooter();
        footer.waitForLinkedinIconToBeVisible();
        footer.clickOnLinkedinIcon();
        footer.redirectToLinkedin();
    }
    //Cart page sheet #39
    @Test
    public void verifyTheCopyRightNoticeMessage() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footer.scrollToFooter();

        Assert.assertEquals(footer.getCopyrightText(),
                "© 2023 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy",
                "The copyright text in the footer is not correct");

    }
}