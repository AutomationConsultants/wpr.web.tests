Feature: Validate functionality for Pet Owners section

  Background: Login to WPR
    Given login is completed on world pet registry website
    And right nav is closed

  Scenario: Validate page load for Pet Owners
     When "Pet Owners" link is clicked on the left navigation
    And validate that the following text is displayed on the page
      | Name             |
      | User Name/ WPR # |
      | Phone            |
      | Email            |
      | #Pets            |
      | Date Created     |
      | Last Login Date  |
      | Status           |
    And validate that there is data in the table

