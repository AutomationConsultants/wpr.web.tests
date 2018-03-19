Feature: Validate functionality for Dashboard section

  Background: Login to WPR
    Given login is completed on world pet registry website
      And right nav is closed

  Scenario: Validate Dashboard page
    When "Dashboard" link is clicked on the left navigation
     And validate that the following text is displayed on the page
      | Pet Samples   |
      | Waste Samples |
      | Pet Owners    |
     And validate status, count and percentage in following tables of "Dashboard"
      | Pet Samples   |
      | Waste Samples |
      | Pet Owners    |
