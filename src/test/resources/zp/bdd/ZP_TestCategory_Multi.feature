Feature: Verify category with multi selection filter

  Background: Navigate to home page
    Given User navigate to the application home

  @Multifilter
  Scenario Outline: Verify user unable to select category and multiple filters
    Given User on "Home" page
    When User select "Country" as "<Country>"
    And Use click "<CategoryTab>"
    And "<CategoryTab>" highlighting with color "#5f929f"
    Then User on "<CategoryPage>" page
    And User click multiple "<Filter>" checkboxes
    And "SelectedFilters" values are displaying as "<FilterValues>"
    And Filters in query parameters are displaying as "<FilterValues>"


    Examples:
      |Country  |CategoryTab       |CategoryPage  |Filter                 |FilterValues   |
      |NL       |AlleProductenTab  |AlleProducten |GemengdVlees;Gevogelte |Gemengd vlees;Gevogelte |






