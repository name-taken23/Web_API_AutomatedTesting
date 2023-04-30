Feature: As a User, I want to Verify that the address details in Checkout Page are correct So That I know my order will be shipped to the correct location
  Background: Launch Browser
    Given I go to the homepage
  @va
  Scenario: Creating a user account from the homepage
    When I click signup or login
    And I fill all the sign up details to create an account
    And click submit
    Then I should see a message that the account has been created
  @va
  Scenario: Checking that a user is logged in after an account has been created
    When I click continue
    Then I should be logged in and should see the correct message in the navigation bar
  @va
  Scenario: Adding a product to the cart as a logged in user and navigating to the cart page
    When I add products to the cart
    And I click the Cart button
    Then I should view the cart page
  @va
  Scenario: Checking the billing address is the same as the registration address
    When I click proceed to checkout
    Then the billing address should be the same as registration
    And the registration address should be the same as registration
  @va
  Scenario: Deleting the account after the test
    When I click Delete Account
    Then I should view an message verifying the account is deleted