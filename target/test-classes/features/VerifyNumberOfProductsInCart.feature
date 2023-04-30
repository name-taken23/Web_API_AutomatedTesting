Feature: I want to verify the number of products in the cart
  Background:
    Given I start on the homepage
    @cart_quantity
    Scenario: I have an empty cart, therefore no items in the cart
      When I navigate to the cart
      And I have an empty cart
      Then I should have 0 products in the cart
