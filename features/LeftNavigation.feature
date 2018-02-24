Feature: Left navigation panel

  Background: Login to WPR
    Given login is completed on world pet registry website
      And right nav is closed

  Scenario: Validate that left navigation panel links open correct page
    Then validate that the left navigation is open
     And validate that options are displayed in the left navigation panel
      | Dashboard  |
      | Detail     |
      | Sub-Groups |
      | Admins     |
      | Pet Owners |
    When "Dashboard" link is clicked on the left navigation
     And validate that the following text is displayed on the page
      | Pet Samples   |
      | Waste Samples |
      | Pet Owners    |
     And validate percentages in following tables of "Dashboard"
      | Pet Samples   |
      | Waste Samples |
      | Pet Owners    |
    When "Detail" link is clicked on the left navigation
    Then validate that the following fields are displayed "txtName,btnSubmit,txtEmail,txtPhone,txtTollFreeNumber,txtDescription,txtHours,txtWebsite" on the page
    When "Sub-Groups" link is clicked on the left navigation
    Then validate that the following text is displayed on the page
      | Group Name        |
      | Group Type        |
      | Primary Phone     |
      | Parent Group Name |
      | City              |
      | State             |
      | Actions           |
     And validate that the following fields are displayed "txtName,txtPhone,txtGroupType,txtParentName,txtCity,txtState" on the page
     And validate that there is data in the table
     And validate the pagination at the bottom is displayed
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
