Feature: :Logout functionality
  Background: Given the user is on homepage
    @mc
Scenario: User logs in and logs out successfully


Given the user is on homepage


When the user clicks the signIn button
And the login page is displayed
When the user enters their correct email address and password
And clicks the login button
Then the Logged in as example1 message is displayed
Then the user clicks the logout button
And the user is navigated to the login page