Feature: I want to verify the number of products in the cart
  Background:
    Given I start on the homepage
    @cart_quantity
    Scenario: I have an empty cart, therefore no items in the cart
      When I navigate to the cart
      And I have an empty cart
      Then I should have 0 products in the cart

    @cart_quantity
    Scenario: I have 4 of the same product in the cart,
      When I add four items to the cart
      And I go to the cart page
      Then I should have four products in the cart

