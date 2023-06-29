@makeMyTrip_MultiwayTrip_TC3
Feature: Flight Booking

  Scenario: Flight Reservation for Multicity Trip
    Given User is on the flight booking page for the multicity trip
    When User clicks on the multi city radio button
    Then Multi city radio button should be selected
    Then A screenshot is taken when multcity trip radio button is selected
    When User randomly selects a From Place for multicity trip
    And User randomly selects a To Place for a multicity trip
    When User select the flight with the lowest price for a multicity trip
    Then User selects the random number of travelers and clicks Apply for a multicity trip
    Then User performs Search and taken a screenshot of the results for a multicity trip
    
