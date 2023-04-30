Feature: Place Order: Register while Checkout

  @sanity
  Scenario: Place order with registered account
    Given I launch the browser
    And I navigate to the homepage
    When I add products to cart
    And I press the cart button
    And I click Proceed To Checkout
    And I click the Register button
    When I fill all the details in Signup and create an account
    And I fill in the rest of the other details in the page
    Then I verify ACCOUNT CREATED! is displayed
    And I click the Continue button
    Then I verify Logged in as username is displayed at the top
    And I click the Cart button there
    And I click the Proceed To Checkout button
    When I enter a description in the comment text area
    And I click the Place Order button
    And I enter payment details: Name on Card, Card Number, CVC, Expiration date
    And I click the Pay and Confirm Order button
    And I click the Delete Account button
    Then I verify ACCOUNT DELETED! is displayed
    And I click the Continue button