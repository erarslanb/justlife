Feature: Create one-time booking for home cleaning
  @Booking
  Scenario: User creates a one-time booking for home cleaning
    Given web browser with "JustLifeURL" page
    When I click on "One-time radio button" if exists
      And I click on "Next button"
    Then I should see "Map popup"

    When I type "Dubai Marina" into "Search input"
      And I wait for "Dubai Marina in location search results"
      And I click on "Dubai Marina in location search results"
      And I wait for 2 seconds
      And I click on "Enabled Confirm button in location popup"
    Then I should see "Service Details section active"

    When I click on "2 hours option"
      And I click on "1 cleaner option"
      And I click on "Yes option under material selection"
      And I click on "Next button"
    Then I should see "Date & Time section active"

    When I click on "First suggested cleaner"
      And I click on "First available date"
      And I click on "First available time"
      And I wait for "Price loading spinner" to disappear
      And I set variable "appointmentDate" to text of element "Date value under Date & Time in booking page"
      And I click on "Next button"
    Then I should see "Login popup"

    When I type "501234567" into "Phone number input"
      And I wait for "2FA code input"
      And I type "4040" into "2FA code input"
      And I click on "I confirm that I am healthy box unchecked" if exists
      And I click on "Next button in health popup"
    Then I should not see "Login popup"

    When I click on "Pay with cash radio button"
      And I click on "Complete button"
      And I wait for "Your order has been placed text"
      And I set variable "finalDate" to text of element "Date value in appointment details"
      And I convert date variable "finalDate" with format "dd MMM yyyy" to format "EEEE, MMMM dd" and put it in variable "finalDateConverted"
    Then I should see "Appointment details"
      And I should see "Home Cleaning" in "Service value in appointment details"
      And I should see "2 hours, 1 Professional with cleaning material" in "Details value in appointment details"
      And variable "finalDateConverted" should be equal to variable "appointmentDate"
      #And I should see "Dubai Marina" in "Address value in appointment details"

