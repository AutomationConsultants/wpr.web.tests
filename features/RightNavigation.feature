Feature: Validate right navigation panel

  Background: Login to WPR
    Given login is completed on world pet registry website
    
Scenario: Validate accounts of right navigation panel
When right nav is opened
Then validate that the right nav has accounts

Scenario: Validate that the correct account opens
When right nav is opened
Then validate that the correct dashboard page is opened for each account