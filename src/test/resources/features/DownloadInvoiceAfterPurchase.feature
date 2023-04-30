Feature: As a User, I want to Verify that the address details in Checkout Page are correct So That I know my order will be shipped to the correct location
  Background: Launching Browser
    Given I am on the homepage
  @di
  Scenario: Creating a user account from the homepage
    When I click the signup or login link
    And I fill all the details to create an account
    And I click submit
    Then I should see that the account has been created
  @di
  Scenario: Checking that a user is logged in after creating their account
    When I click the continue button
    Then I should see I am logged in from the navigation bar
  @di
  Scenario: Adding a product to the cart and going to the cart page
    When I go to the products page
    And I add a product to the cart
    And I click the cart button
    Then I should view the cart page and my basket
  @di
  Scenario: Making a successful purchase
    When I click the link to proceed to checkout
    And I click to purchase order
    And I fill out the correct card details
    And I click proceed
    Then  I should view a message that my order has been successful
  @di
  Scenario: Downloading an invoice
    When I click on the button to download an invoice
    Then an invoice should be downloaded
  @di
  Scenario: Deleting the account after the test
    When I click the link to delete my account
    Then I should view an message stating the account is deleted