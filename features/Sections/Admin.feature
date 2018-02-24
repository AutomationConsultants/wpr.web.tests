Feature: Validate functionality for Admin section

  Background: Login to WPR
    Given login is completed on world pet registry website
      And right nav is closed

  Scenario: Validate page load for Admin
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

  Scenario: Validate sorting

  Scenario: Validate filtering

  Scenario: Validate Create New Admin
    Given click on create new admin
     Then validate that the header is "Create New Admin"
     And validate that the following fields are displayed
      | txtAdminFirstName    |
      | txtAdminLastName     |
      | txtAdminEmail        |
      | txtAdminContactEmail |
      | drpAdminRole         |
      | txtAdminNotes        |
      | txtAdminBioPetNotes  |
      | btnAdminCreate       |
      | btnAdminCancel       |
      And validate that the dropdown "drpAdminRole" has values
      | BioPetCustomerServiceAdmin |
      | ManagementFirmManager      |
     When enter following values in fields
      | Value      | Field             |
      | First Name | txtAdminFirstName |
      And create button is clicked

  Scenario: Validate Add Existing Admin

  Scenario: Validate

  Scenario: Validate
