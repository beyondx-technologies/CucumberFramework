@RestAssured
Feature: Rest Assured Automation Scenario

Scenario: GET Requests
When I GET response from 'https://reqres.in/api/users?page=2' and check status code is '200'
Then I POST response from 'https://reqres.in/api/users' url and add body Name: 'morpheus' Position: 'leader' check status code is '201'