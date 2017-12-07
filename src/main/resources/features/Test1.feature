Feature: Login to WPR and smoke test
@test
Scenario: Validate left nav and landing page
Given login is completed on world pet registry website
Then validate that the left navigation is open
And validate that text "Pet Samples" is displayed on the page
And validate that texts are "Pet Samples, Waster Samples" displayed on the page
And validate that the following text is displayed on the page
|Pet Samples|
|Waste Samples|
|Pet Owners|
And validate that options are displayed in the left navigation panel
|Dashboard|
|Detail|
|Sub-Groups|
|Admins|
|Pet Owners|

@test1
Scenario: Validate page load for left nav items
Given login is completed on world pet registry website
When "Dashboard" link is clicked on the left navigation
And validate that the following text is displayed on the page
|Pet Samples|
|Waste Samples|
|Pet Owners|
When "Detail" link is clicked on the left navigation
Then validate that the following fields are displayed "txtName, btnSubmit, txtEmail, txtPhone, txtTollFreeNumber, txtDescription, txtHours, txtWebsite" on the page
When "Sub-Groups" link is clicked on the left navigation
And validate that the following text is displayed on the page
|Group Name|
|Group Type|
|Primary Phone|
|Parent Group Name|
|City|
|State|
|Actions|
And validate that the following fields are displayed "txtName, txtPhone, txtGroupType, txtParentName, txtCity, txtState" on the page
And validate that there is data in the table
And validate the pagination at the bottom is displayed
When "Admins" link is clicked on the left navigation
And validate that the following text is displayed on the page
|Create New Admin|
|Add Existing Admin|
|Name|
|Role|
|User Name|
|Last Login Date|
|Primary Admin|
|Actions|
And validate that there is data in the table
And validate the pagination at the bottom is displayed

When "Detail" link is clicked on the left navigation

