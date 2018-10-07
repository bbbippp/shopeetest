Feature: Login to github

  Scenario: Login successfully to github
    Given Open the chrome browser and launch the application
    When Enter the username and password
    Then User will get taken to github dashboard
    When User create new gist