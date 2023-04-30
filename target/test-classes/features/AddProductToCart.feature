Feature: Add product to the cart
  @sanity
  Scenario: Adding product to the cart without login in
    Given User launch browser
    And opens URL "https://automationexercise.com/"
    When User navigate to Products Page
    And click on Add to cart on a product
    And press continue to checkout
    Then User navigates to Cart Page
    And has one item in the cart

  @sanity
  Scenario: Adding two different products to the cart
    Given User launch browser
    And opens URL "https://automationexercise.com/"
    And User navigate to Products Page
    And click on Add to cart on a product
    And press continue to checkout
    When User click on Add to cart another product
    And press View Cart
    Then in the cart should be two items