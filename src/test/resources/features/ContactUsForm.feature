Feature: Submit a message through the Contact Us form
Scenario: User send a message successfully

  Given the user navigates to homepage



When the user clicks the Contact Us button
And the GET IN TOUCH section is displayed
Then the user enters their name, email, subject, and message
And the user click on choose file button
When the user clicks the Submit button
And the user clicks the OK button
Then the message Success! Your details have been submitted successfully is visible

And the user is navigated back the login page
