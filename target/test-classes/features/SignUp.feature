Feature: As a User, I want to be able to sign up so that I can use the website

  @jr
  Scenario: Navigating to the sign up page
    Given I am on the main page
    When I click on the Signup link
    Then I should go to the Signup page

  @jr
  Scenario: Entering details on login in page
    Given I am on the login page
    When I enter details to sign up
    Then I should go to the sign up page

#  @jr
#  Scenario: Entering details on the sign up page
#    Given I have entered details to sign up on the login page and clicked signup
#    When I enter details on the sign up page and click create account
#    Then I should be taken to the account creation page

  @jr
  Scenario: Signing up and deleting account
    Given I have signed up
    When I click on delete account
    Then my account should be delete

  @jr
  Scenario: Signing up with existing account
    Given I have an existing account and I am trying to sign up again
    When I try to sign up with the same details
    Then I should get Email Address already exists message