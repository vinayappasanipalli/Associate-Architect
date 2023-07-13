@roundtripTC1
Feature: Flight Booking for RoundTrip

  Scenario: Make a RoundTrip flight reservation
    Given User Login to MakemyTrip
    Then User selects Roundtrip option
    Then Take a Screenshot when a Roundtrip radio button is selected
    When Randomly select a Destination for the trip
    And Randomly select a Arrival for the trip
    When Select the lowest fare flight for the roundtrip
    Then Select the random Travellers for the roundtrip
    Then User performs Search and taken a screenshot of the results for a round trip
    Then User Logout from the application
