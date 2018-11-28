Feature: Verify different GET operations using REST-Assured
  As an API user
  I want to perform a GET operation
  So I can see the results / list of values returned

  Scenario: Verify one Author of the post endpoint
    Given I perform a GET operation for "/posts"
    When I perform a GET on the "1" post resource
    Then I should see the Author name as "Karthik KK"
