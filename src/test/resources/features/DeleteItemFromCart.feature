Feature: Delete items from cart

  @kh
    Scenario:
    Given I am on the product page
    And I add two items to cart
    When I click on the cart
    And I click on the remove button
    Then the item should only be one item in the cart