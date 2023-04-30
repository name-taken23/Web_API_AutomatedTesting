Feature: As a User, I want to see a confirmation message that I have subscribed

  Scenario:Submitting email input on the home page
    Given I am on the Home Page
    And I scroll down to the footer
    And I can see the 'SUBSCRIPTION' message
    When I input my email details in the input box
    And click the arrow button
    Then I should see a success popup message

  Scenario:Submitting email input on the cart page
    Given I click on the cart
    And I scroll down to the footer on CartPage
    And I can see the 'SUBSCRIPTION' message on CartPage
    When I input my email details in the input box on CartPage
    And click the arrow button on CartPage
    Then I should see a success popup message on CartPage

