Feature: Write a review on a product

  Background:
    Given I am on a product page

  @writereview
  Scenario:
    When I enter my name and email and review
    And I submit
    Then I should see a message telling me review was submited

  @writereview
    Scenario:
    When I don't enter an email
    And I submit
    Then I should get a message telling the email field is missing.

  @writereview
  Scenario:
    When I don't enter an name
    And I submit
    Then I should get a message telling the name field is missing.

  @writereview
  Scenario:
    When I don't enter an review
    And I submit
    Then I should get a message telling the review field is missing.


