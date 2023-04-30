Feature: As a User, I want to be able to navigate to Products page
  @sb
  Scenario: Navigate to the Product page from Home page and write "Dress" on search bar
    Given I am on the Home Page
    When I click on the Products button
    And I click on the search bar
    And Enter "Dress" and press enter
    Then I will go to Dress page
  @sb
  Scenario: On the Product page search for "Dress" and press search button
    Given I am on the Home Page
    When I click on the Products button
    And Enter "Dress" on search bar
    And Press on search button
    Then I will go to Dress page
  @sb
  Scenario: On the Product page press search button with empty search bar
    Given I am on the Home Page
    And I click on the Products button
    When I press search button
    Then I should send empty query
  @sb
  Scenario: Navigate to the Products page from Home page
    Given I am on the Home Page
    When I click on the Products button
    Then I will go to the Products page
