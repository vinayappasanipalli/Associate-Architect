
@makeMyTrip_OnewayTrip_TC1
Feature: Flight Booking

  Scenario: Flight Reservation for OneWay Trip
    Given User is on the flight booking page to make oneway trip
    When User clicks on the oneway trip radio button
    Then Oneway trip radio button should be selected
    Then A screenshot is taken when oneway trip radio button is selected
    When User randomly selects a From Place for oneway trip
    And User randomly selects a To Place for oneway trip
    When User select the flight with the lowest price for a oneway trip
    Then User selects the random number of travelers and clicks Apply for a oneway trip
    Then User performs Search and taken a screenshot of the results of the oneway trip
    
