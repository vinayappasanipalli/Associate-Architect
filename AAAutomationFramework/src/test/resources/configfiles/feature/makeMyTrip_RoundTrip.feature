@makeMyTrip_RoundTrip_TC2
Feature: Flight Booking

  Scenario: Flight Reservation for Round Trip
    Given User is on the flight booking page to make round trip reservation
    When User clicks on the round trip radio button
    Then Round trip radio button should be selected
    Then A screenshot is taken when round trip radio button is selected
    When User randomly selects a From Place for a round trip
    And User randomly selects a To Place for a round trip
    When User select the flight with the lowest price for a round trip
    Then User selects the random number of travelers and clicks Apply for a round trip
    Then User performs Search and taken a screenshot of the results for a round trip
