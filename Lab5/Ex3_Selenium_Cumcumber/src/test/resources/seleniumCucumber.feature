Feature: Search in Google

  Scenario: Seek for Selenium-Jupiter documentation
    When I navigate to "https://google.com"
    And I type "Selenium-Jupiter"
    And I press Enter
    Then I should be shown results including "Selenium"


  Scenario: Seek for BlazeDemo Page and select cities
    When I navigate to "https://blazedemo.com/"
    And I choose "Boston" as departure city
    And I choose "London" as destination city
    And I press "Find Flights"
    Then I should be shown results including "Flights from Boston to London:"

  Scenario: Choose Flight
    When I navigate to "https://blazedemo.com/reserve.php"
    And I press "Choose This Flight" on row number 3
    Then I should be shown results including "Your flight from TLV to SFO has been reserved."

  Scenario: Fill form and purchase
    When I navigate to "https://blazedemo.com/purchase.php"
    And I write "Mary" on "inputName" field
    And I write "Aveiro" on "city" field
    And I write "12345" on "zipCode" field
    And I select "Remember me" box
    And I press "Purchase Flight"
    Then I should be shown results including "Thank you for your purchase today!"

