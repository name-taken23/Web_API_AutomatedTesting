# new feature
# Tags: optional

Feature: As a user I want to Login so that I can use the website

  Background: Starting from the Login Page
    Given I am on the Login Page


  @rl
  Scenario: Entering Invalid credentials
    When I enter invalid credentials And click on the Sign In button
    Then I should see an error message indicating that the credentials are incorrect

##  @rl
  ##Scenario: Empty username and password fields
    ##When I leave the username and password fields empty And click on the Sign In button
    ##Then I should see an error message indicating that both fields are required