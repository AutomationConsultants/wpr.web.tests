Feature: Validate functionality for Detail section

  Background: Login to WPR
    Given login is completed on world pet registry website
      And right nav is closed

  Scenario: Validate page load for Detail
    When "Detail" link is clicked on the left navigation
     And validate that the following text is displayed on the page
      | Name                |
      | Toll Free Number    |
      | Email               |
      | Description         |
      | Phone               |
      | Hours               |
      | Last Logged in Date |
      | Website             |
      | Address Line 1      |
      | Suite               |
      | City                |
      | State               |
      | Zip                 |
      | Country             |
    Then validate that following fields are displayed on the page
      | txtName           |
      | txtEmail          |
      | txtPhone          |
      | txtTollFreeNumber |
      | txtDescription    |
      | txtHours          |
      | txtWebsite        |
      | btnSubmit         |
