Feature: Create weekly booking for home cleaning
  @Booking
  Scenario: User creates a weekly booking for home cleaning
    Given web browser with "JustLifeURL" page
    When I click on "Weekly radio button"
      And I click on "Next button"
    Then I should see "Map popup"

    When I type "Dubai Marina" into "Search input"
      And I wait for "Dubai Marina in location search results"
      And I click on "Dubai Marina in location search results"
      And I wait for 2 seconds
      And I click on "Enabled Confirm button in location popup"
    Then I should see "Service Details section active"

    When I click on "2 hours option"
      And I click on "2 cleaners option"
      And I click on "No option under material selection"
      And I scroll down
      And I click on "Next button"
    Then I should see "Date & Time section active"

    When I set variable "tomorrow" to date value with format "yyyy-MM-dd" and offset 1 "days"
      And I click on element with attribute "data-date" matching variable "tomorrow"
      And I wait for "Price loading spinner" to disappear
      And I click on "09:00 option"
      And I wait for "Price loading spinner" to disappear
      And I set variable "appointmentDate" to text of element "Date value under Date & Time in booking page"
      And I scroll down
      And I click on "Next button"
    Then I should see "Login popup"

    When I type "501234567" into "Phone number input"
      And I wait for "2FA code input"
      And I type "4040" into "2FA code input"
      And I click on "I confirm that I am healthy box unchecked" if exists
      And I click on "Next button in health popup"
    Then I should not see "Login popup"

    When I click on "Pay with cash radio button"
      And I scroll down
      And I click on "Complete button"
      And I convert date variable "tomorrow" with format "yyyy-MM-dd" to format "dd MMM yyyy" and put it in variable "tomorrowConverted"
    Then I should see "Your order has been placed text"
      And I should see "Appointment details"
      And I should see "Home Cleaning" in "Service value in appointment details"
      And I should see "2 hours, 2 Professionals without cleaning material" in "Details value in appointment details"
      And I should see variable "tomorrowConverted" value in "Date value in appointment details"
      And I should see "09:00-09:30" in "Start Time value in appointment details"
      And I should see "Every week" in "Frequency value in appointment details"
      #And I should see "Dubai Marina" in "Address value in appointment details"

