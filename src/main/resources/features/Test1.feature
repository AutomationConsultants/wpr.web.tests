Feature: Login to WPR and smoke test
@test
Scenario: Validate left nav and landing page
Given login is completed on world pet registry website
Then validate that the left navigation is open
And validate that text is displayed on the landing page
|Pet Samples|
|Waste Samples|
|Pet Owners|
And validate that options are displayed in the left navigation panel
|Dashboard|
|Detail|
|Sub-Groups|
|Admins|
|Pet Owners|