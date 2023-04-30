Feature: I want to view the product details
  Background:
    Given I am on the automation exercise homepage

    @prod_details
    Scenario: I can navigate to the product page
      When I click on the products page
      Then I navigate to products page
