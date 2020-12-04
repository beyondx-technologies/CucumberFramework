@Register
Feature: User is able to login into the application

Scenario: I validate Landing page in E-Commerce Application
Given I check the page title is 'My Store'
And I should see 'HomePage_Banner' present in page
And I should see 'HomePage_Nav' present in page
And I should see 'HomePage_Phone' present in page
And I should see 'HomePage_Login' present in page
And I should see 'HomePage_ContactUs' present in page
And I should see 'HomePage_HeaderLogo' present in page
And I should see 'HomePage_SearchBox' present in page
And I should see 'HomePage_ShoppingCart' present in page

@Runthis
Scenario: I register into the ecommerce store
When I click 'HomePage_Login'
Then I enter random email address in field 'Login_RegisteredEmail'
And I click 'Login_SubmitButton'
And I wait for '5' sec
And I click 'Register_GenderMale' by actions
And I click 'Register_GenderMale'
And I enter 'Vishal' in 'Register_CustomerFirstName' field
And I enter 'Vaitheeswaran' in 'Register_CustomerLastName' field
And I enter 'Test@1234' in 'Register_Password' field
And I select '20' in 'Register_days' by value
And I select '4' in 'Register_months' by value
And I select '1997' in 'Register_years' by value
And I click 'Register_newsletter'
And I click 'Register_option'
And I enter 'Blind Chemsitry' in 'Register_Company' field
And I enter '2 Arcadia Dr.' in 'Register_Address1' field
And I enter 'Pensacola' in 'Register_Address2' field
And I enter 'Pensacola' in 'Register_City' field
And I select 'Florida' in 'Register_State' by text
And I enter '32503' in 'Register_Postcode' field
And I enter 'Testing 1234' in 'Register_other' field
And I enter '888888888' in 'Register_Phone' field
And I enter '898989898' in 'Register_PhoneMobile' field
And I enter 'Test Address' in 'Register_Alias' field
And I click 'Register_submitAccount'