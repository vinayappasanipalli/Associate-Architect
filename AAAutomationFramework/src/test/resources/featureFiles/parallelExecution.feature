@parallelExecution
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

  Scenario: Make a multiwayTrip flight reservation
    Given User Login to MakemyTrip site for multiwaytrip
    Then User selects multiwaytrip option
    Then Take a Screenshot when a multiwaytrip radio button is selected
    When Randomly select a Destination for the multiwaytrip
    And Randomly select a Arrival for the multiway
    When Select the lowest fare flight for the multiwaytrip
    Then Select the random Travellers for the multiwaytrip
    Then User performs Search and taken a screenshot of the results for a multiway trip
    Then Logout from the makemytrip application
