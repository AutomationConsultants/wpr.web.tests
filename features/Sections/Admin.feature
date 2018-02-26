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
     And validate that the following fields are displayed
      | txtAdminName     |
      | txtAdminRole     |
      | txtAdminUserName |
     And validate that there is data in the table

  Scenario Outline: Validate Create New Admin functionality
    Given click on button "btnAdminCreateNew"
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
      | Value            | Field                |
      | "<FirstName>"    | txtAdminFirstName    |
      | "<LastName>"     | txtAdminLastName     |
      | "<Email>"        | txtAdminEmail        |
      | "<ContactEmail>" | txtAdminContactEmail |
      | "<Role>"         | txtAdminRole         |
      | "<AdminNotes>"   | txtAdminNotes        |
      | "<BioPetNotes>"  | txtAdminBioPetNotes  |
      And click on button "btnAdminCreate"
     Then validate that the text for "lblAdminModalHeader" is "New Admin created successfully."
      And validate that the following fields are displayed
      | btnAdminCreateSuccessOK |
     When click on button "btnAdminCreateSuccessOK"
      And text "<FirstName> <LastName>" is entered in field "txtAdminName"
      And text "<FirstName> <LastName>" is entered in field "txtAdminName"
      And text "<Role>	" is entered in field "txtAdminRole"
     Then validate that there is data in the table

    Examples: 
      | FirstName | LastName | Email         | ContactEmail  | Role                       | AdminNotes       | BioPetNotes            |
      | FNAME     | LNAME    | test@test.com | test@test.com | BioPetCustomerServiceAdmin | Admin notes test | Bio Pet Lab Notes test |

  Scenario Outline: Validate error messages while Create New Admin
    Given click on button "btnAdminCreateNew"
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
      | Value            | Field                |
      | "<FirstName>"    | txtAdminFirstName    |
      | "<LastName>"     | txtAdminLastName     |
      | "<Email>"        | txtAdminEmail        |
      | "<ContactEmail>" | txtAdminContactEmail |
      | "<Role>"         | txtAdminRole         |
      | "<AdminNotes>"   | txtAdminNotes        |
      | "<BioPetNotes>"  | txtAdminBioPetNotes  |
      And click on button "btnAdminCreate"
     Then validate that the following error messages are displayed while creating a new admin "<ErrorMsgList>"

    Examples: 
      | FirstName | LastName | Email | ContactEmail | Role                       | AdminNotes       | BioPetNotes            | ErrorMsgList                                                                  |
      |           |          |       |              | BioPetCustomerServiceAdmin | Admin notes test | Bio Pet Lab Notes test | First Name is required,Last Name is required,Please provide an email address. |

  Scenario Outline: Validate Add Existing Admin functionality
    Given click on button "btnAdminCreateNew"
     Then validate that the header is "Add Existing Admin"
      And validate that the following fields are displayed
      | txtAdminFirstName |
      | txtAdminEmail     |
     When admin with name "<AdminName>" and email id "<AdminEmail>" is selected
     Then validate that there are not error messages
     When text "<AdminName>" is entered in field "txtAdminName"
      And text "<AdminRole>" is entered in field "txtAdminRole"
      And text "<AdminUserName>" is entered in field "txtAdminUserName"
     Then validate that there is data in the table

  Scenario Outline: Validate that a list is populated for existing admins
    Given click on button "btnAdminCreateNew"
     Then validate that the header is "Add Existing Admin"
      And validate that the following fields are displayed
      | txtAdminFirstName |
      | txtAdminEmail     |
      And validate that a list of existing admins is populated

    Examples: 
      | AdminName | AdminRole      | AdminEmail    | AdminUserName |
      | Test Test | BioPetLabAdmin | test@test.com |               |

  Scenario Outline: Validate filtering logic for Admin Page
    Given text "<AdminName>" is entered in field "txtAdminName"
      And text "<AdminRole>" is entered in field "txtAdminRole"
      And text "<AdminUserName>" is entered in field "txtAdminUserName"
     Then validate that there is data in the table

    Examples: 
      | AdminName | AdminRole      | AdminUserName |
      | Test Test | BioPetLabAdmin | WPR154541     |

  Scenario Outline: Validate Admin details page
    Given text "<AdminName>" is entered in field "txtAdminName"
      And text "<AdminRole>" is entered in field "txtAdminRole"
      And text "<AdminUserName>" is entered in field "txtAdminUserName"
     Then validate that there is data in the table
      And validate Admin details page

    Examples: 
      | AdminName | AdminRole      | AdminUserName |
      | Test Test | BioPetLabAdmin |               |

  Scenario: Validate

  Scenario: Validate sorting logic for Admin Page
