Feature: Validate functionality for Sub-Groups section

  Background: Login to WPR
    Given login is completed on world pet registry website
    And right nav is closed

  Scenario: Validate page load for Sub-Groups
    When "Sub-Groups" link is clicked on the left navigation
    Then validate that the following text is displayed on the page
      | Group Name            |
      | Last Login            |
      | Last Sample Submitted |
      | Last Pet Created      |
      | Primary Phone         |
      | City                  |
      | State                 |
      | Actions               |
    And validate that following fields are displayed on the page
      | txtName       |
      | txtPhone      |
      | txtGroupType  |
      | txtParentName |
      | txtCity       |
      | txtState      |
    And validate that there is data in the table
    And validate the pagination at the bottom is displayed
