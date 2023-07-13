@multitripTC3
Feature: Flight Booking for MultiwayTrip

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
