@onewaytripTC2
Feature: Flight Booking for OnewayTrip

  Scenario: Make a OnewayTrip flight reservation
    Given User Login to MakemyTrip site for onewaytrip
    Then User selects onewaytrip option
    Then Take a Screenshot when a onewaytrip radio button is selected
    When Randomly select a Destination for the onewaytrip
    And Randomly select a Arrival for the onewaytrip
    When Select the lowest fare flight for the onewaytrip
    Then Select the random Travellers for the onewaytrip
    Then User performs Search and taken a screenshot of the results for a oneway trip
    Then User Logout from the makemytrip application
