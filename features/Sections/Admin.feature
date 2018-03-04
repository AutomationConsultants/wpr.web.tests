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
    When "Admins" link is clicked on the left navigation
     And click on button "btnAdminCreateNew"
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
     And validate that the dropdown "drpAdminRole" has following values
      | BioPetCustomerServiceAdmin |
      | ManagementFirmManager      |
    When enter following values in fields
      | Value          | Field                |
      | <FirstName>    | txtAdminFirstName    |
      | <LastName>     | txtAdminLastName     |
      | <Email>        | txtAdminEmail        |
      | <ContactEmail> | txtAdminContactEmail |
      | <Role>         | drpAdminRole         |
      | <AdminNotes>   | txtAdminNotes        |
      | <BioPetNotes>  | txtAdminBioPetNotes  |
     And click on button "btnAdminCreate"
    Then validate that the text for "lblAdminModaCreateDeleteSuccess" is "New Admin created successfully."
     And validate that the following fields are displayed
      | btnOK |
    When click on button "btnOK"
     And admin with name "<FirstName> <LastName>", role "<Role>" and username "" is searched
    Then validate that there is data in the table

    Examples: 
      | FirstName | LastName | Email         | ContactEmail        | Role                       | AdminNotes       | BioPetNotes            |
      | FNAME     | LNAME    | test@test.com | fnamelname@test.com | BioPetCustomerServiceAdmin | Admin notes test | Bio Pet Lab Notes test |

  Scenario Outline: Validate error messages while Create New Admin
    When "Admins" link is clicked on the left navigation
     And click on button "btnAdminCreateNew"
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
     And validate that the dropdown "drpAdminRole" has following values
      | BioPetCustomerServiceAdmin |
      | ManagementFirmManager      |
    When enter following values in fields
      | Value          | Field                |
      | <FirstName>    | txtAdminFirstName    |
      | <LastName>     | txtAdminLastName     |
      | <Email>        | txtAdminEmail        |
      | <ContactEmail> | txtAdminContactEmail |
      | <Role>         | drpAdminRole         |
      | <AdminNotes>   | txtAdminNotes        |
      | <BioPetNotes>  | txtAdminBioPetNotes  |
     And click on button "btnAdminCreate"
    Then validate that the following error messages are displayed when mandatory field is left blank "<ErrorMsgList>"

    Examples: 
      | FirstName | LastName | Email | ContactEmail | Role                       | AdminNotes       | BioPetNotes            | ErrorMsgList                                                                  |
      |           |          |       |              | BioPetCustomerServiceAdmin | Admin notes test | Bio Pet Lab Notes test | First Name is required,Last Name is required,Please provide an email address. |

  Scenario Outline: Validate Add Existing Admin functionality
    When "Admins" link is clicked on the left navigation
     And click on button "btnAdminAddExisting"
    Then validate that the header is "Add Existing Admin"
     And validate that the following fields are displayed
      | txtAdminFullName |
      | txtAdminEmail    |
    When admin with name "<AdminName>" and email id "<AdminEmail>" is selected
    Then validate that there are no error messages
    When text "<AdminName>" is entered in field "txtAdminName"
    Then validate that there is data in the table

    Examples: 
      | AdminName | AdminRole      | AdminEmail | AdminUserName |
      | Test Test | BioPetLabAdmin |            |               |

  Scenario Outline: Validate that a list is populated for existing admins
    When "Admins" link is clicked on the left navigation
     And click on button "btnAdminAddExisting"
    Then validate that the header is "Add Existing Admin"
     And validate that the following fields are displayed
      | txtAdminFullName |
      | txtAdminEmail    |
     And validate that a list of existing admins is populated

    Examples: 
      | AdminName | AdminRole      | AdminEmail    | AdminUserName |
      | Test Test | BioPetLabAdmin | test@test.com |               |

  Scenario Outline: Validate filtering logic for Admin Page
    When "Admins" link is clicked on the left navigation
     And admin with name "<AdminName>", role "<AdminRole>" and username "<AdminUserName>" is searched
    Then validate that there is data in the table

    Examples: 
      | AdminName | AdminRole      | AdminUserName |
      | Test Test | BioPetLabAdmin |               |

  Scenario Outline: Validate Admin details page
    When "Admins" link is clicked on the left navigation
     And admin with name "<AdminName>", role "<AdminRole>" and username "<AdminUserName>" is searched
    Then validate that there is data in the table
     And validate admin details page

    Examples: 
      | AdminName | AdminRole      | AdminUserName |
      | Test Test | BioPetLabAdmin |               |

  Scenario Outline: Validate delete Admin functionality
    When "Admins" link is clicked on the left navigation
     And admin with name "<AdminName>", role "<AdminRole>" and username "<AdminUserName>" is searched
    Then validate that there is data in the table
    When admin details page is opened for the admin
     And click on button "btnAdminDelete"
    Then validate the message "Are you sure you want to delete this admin? This action cannot be undone! Type <AdminUserName> to confirm" is displayed
     And text "<AdminUserName>" is entered in field "txtAdminModal"
    When click on button "btnYes"
    Then validate the message "Admin deleted successfully." is displayed
    When click on button "btnOK"
    Then validate that the following text is displayed on the page
      | Pet Samples   |
      | Waste Samples |
      | Pet Owners    |

    Examples: 
      | AdminName | AdminRole      | AdminUserName |
      | Test Test | BioPetLabAdmin | meg           |

  @test
  Scenario Outline: Validate deactivate Admin functionality
    When "Admins" link is clicked on the left navigation
     And admin with name "<AdminName>", role "<AdminRole>" and username "<AdminUserName>" is searched
    Then validate that there is data in the table
    When admin details page is opened for the admin
     And click on button "btnAdminDeactivate"
    Then validate the message "Deactivated users cannot log into the World Pet Registry. Are you sure you want to deactivate this admin?" is displayed
    When click on button "btnYes"
    Then validate the message "Admin '<AdminName>' deactivated successfully." is displayed
    When click on button "btnOK"

    Examples: 
      | AdminName | AdminRole      | AdminUserName |
      | Test Test | BioPetLabAdmin |               |

  Scenario Outline: Validate reset password functionality
    When "Admins" link is clicked on the left navigation
     And admin with name "<AdminName>", role "<AdminRole>" and username "<AdminUserName>" is searched
    Then validate that there is data in the table
    When admin details page is opened for the admin
     And click on button "btnAdminResetPwd"
    Then validate the message "You are about to reset this user password. If you click continue a new password will be automatically generated and emailed to this user email address. Are you sure you want to continue?" is displayed
    When click on button "btnYes"
    Then validate the message "Password has been reset successfully." is displayed
     And log user and new password
    When click on button "btnOK"

    Examples: 
      | AdminName | AdminRole | AdminUserName |
      |           |           | meg           |
