Feature: Smoke tests

  Background: Login to WPR
    Given login is completed on world pet registry website
      And right nav is closed

  @smokeTests @leftNav
  Scenario: Validate left nav
    Then validate that the left navigation is open
     And validate that options are displayed in the left navigation panel
      | Dashboard  |
      | Detail     |
      | Sub-Groups |
      | Admins     |
      | Pet Owners |

  @smokeTests @dashboard
  Scenario: Validate page load for Dashboard on left nav
    When "Dashboard" link is clicked on the left navigation
     And validate that the following text is displayed on the page
      | Pet Samples   |
      | Waste Samples |
      | Pet Owners    |

  @smokeTests @detail
  Scenario: Validate page load for Detail on left nav
    When "Detail" link is clicked on the left navigation
    Then validate that following fields are displayed on the page
      | txtName           |
      | btnSubmit         |
      | txtEmail          |
      | txtPhone          |
      | txtTollFreeNumber |
      | txtDescription    |
      | txtHours          |
      | txtWebsite        |

  @smokeTests @subGroups
  Scenario: Validate page load for Sub-Groups on left nav
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
      | txtCity       |
      | txtState      |
     And validate that there is data in the table
     And validate the pagination at the bottom is displayed

  @smokeTests @admins
  Scenario: Validate page load for Admins on left nav
    When "Admins" link is clicked on the left navigation
     And validate that the following text is displayed on the page
      | Create New Admin   |
      | Add Existing Admin |
      | Name               |
      | Role               |
      | User Name          |
      | Last Login Date    |
      | Primary Admin      |
      | Actions            |
     And validate that there is data in the table

  @smokeTests @petOwners
  Scenario: Validate page load for Pet Owners on left nav
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

  @smokeTests @rightNav
  Scenario: Validate right nav
    When right nav is opened
    Then validate that the right nav has accounts
