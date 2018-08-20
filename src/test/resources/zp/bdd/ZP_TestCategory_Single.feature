Feature: Verify category with selection filter

  Background: Navigate to home page
    Given User navigate to the application home

    @SingleFilter
    Scenario Outline: Verify user unable to select category and filter
      Given User on "Home" page
      When User select "Country" as "<Country>"
      And Use click "<CategoryTab>"
      And "<CategoryTab>" highlighting with color "#5f929f"
      Then User on "<CategoryPage>" page
      And User click "<Filter>" checkbox
      And "SelectedFilters" values are displaying as "<FilterValues>"
      And Filters in query parameters are displaying as "<FilterValues>"


      Examples:
      |Country  |CategoryTab       |CategoryPage  |Filter       |FilterValues   |
      |NL       |AlleProductenTab  |AlleProducten |GemengdVlees |Gemengd vlees  |
      |DE       |TrockenfutterTab  |Trockenfutter |Geflügel     |Geflügel       |






