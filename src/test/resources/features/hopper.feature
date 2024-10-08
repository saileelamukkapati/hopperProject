
    Feature: Hopper Flight Search

  Scenario Outline: Search for flights and find the cheapest price and longest duration
    Given I am on the Hopper website
    When I search for flights from "<fromCity>" to "<toCity>"
    Then I should see the cheapest flight price
    And I should see the longest flight duration

  Examples:
    | fromCity    | toCity         |
    | Chicago     | New York       |
  