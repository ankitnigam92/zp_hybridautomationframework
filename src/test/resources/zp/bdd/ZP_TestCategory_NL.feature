Feature: Verify category with selection filter for NL

  Background: Navigate to home page
    Given User navigate to the application home

    @SingleFilter_NL
    Scenario Outline: Verify user able to select category and filter for NL
      Given User on "Home" page
      When User select "Country" as "<Country>"
      And Use click "<CategoryTab>"
      And "<CategoryTab>" highlighting with color "#5f929f"
      Then User on "<CategoryPage>" page
      And User click "<Filter>" checkbox
      And "SelectedFilters" values are displaying as "<FilterValues>"
      And Filters in query parameters are displaying as "<FilterValues>"


      Examples:
      |Country  |CategoryTab            |CategoryPage         |Filter             |FilterValues         |
      |NL       |AlleProductenTab       |AlleProducten        |GemengdVlees       |Gemengd vlees        |
      |NL       |AlleProductenTab       |AlleProducten        |Gevogelte          |Gevogelte            |
      |NL       |AlleProductenTab       |AlleProducten        |Kip                |Kip                  |
      |NL       |AlleProductenTab       |AlleProducten        |Konijn             |Konijn               |
      |NL       |AlleProductenTab       |AlleProducten        |Lam                |Lam                  |
      |NL       |AlleProductenTab       |AlleProducten        |RundAndKalfsvlees  |Rund- & kalfsvlees   |
      |NL       |AlleProductenTab       |AlleProducten        |Vis                |Vis                  |
      |NL       |AlleProductenTab       |AlleProducten        |Wild               |Wild                 |
      |NL       |DroogvoerTab           |Droogvoer            |GemengdVlees       |Gemengd vlees        |
      |NL       |DroogvoerTab           |Droogvoer            |Gevogelte          |Gevogelte            |
      |NL       |DroogvoerTab           |Droogvoer            |Kip                |Kip                  |
      |NL       |DroogvoerTab           |Droogvoer            |Lam                |Lam                  |
      |NL       |DroogvoerTab           |Droogvoer            |RundAndKalfsvlees  |Rund- & kalfsvlees   |
      |NL       |DroogvoerTab           |Droogvoer            |Vis                |Vis                  |
      |NL       |DroogvoerTab           |Droogvoer            |Wild               |Wild                 |
      |NL       |NatvoerTab             |Natvoer              |Gevogelte          |Gevogelte            |
      |NL       |NatvoerTab             |Natvoer              |Kip                |Kip                  |
      |NL       |NatvoerTab             |Natvoer              |Lam                |Lam                  |
      |NL       |NatvoerTab             |Natvoer              |RundAndKalfsvlees  |Rund- & kalfsvlees   |
      |NL       |NatvoerTab             |Natvoer              |Vis                |Vis                  |
      |NL       |NatvoerTab             |Natvoer              |Wild               |Wild                 |
      |NL       |SnacksTab              |Snacks               |Kip                |Kip                  |
      |NL       |SnacksTab              |Snacks               |Konijn             |Konijn               |
      |NL       |SnacksTab              |Snacks               |Lam                |Lam                  |
      |NL       |SnacksTab              |Snacks               |RundAndKalfsvlees  |Rund- & kalfsvlees   |
      |NL       |SnacksTab              |Snacks               |Vis                |Vis                  |
      |NL       |SnacksTab              |Snacks               |Wild               |Wild                 |
      |NL       |ProbeerverpakkingenTab |Probeerverpakkingen  |Gevogelte          |Gevogelte            |
      |NL       |ProbeerverpakkingenTab |Probeerverpakkingen  |Kip                |Kip                  |
      |NL       |ProbeerverpakkingenTab |Probeerverpakkingen  |Konijn             |Konijn               |
      |NL       |ProbeerverpakkingenTab |Probeerverpakkingen  |Lam                |Lam                  |
      |NL       |ProbeerverpakkingenTab |Probeerverpakkingen  |RundAndKalfsvlees  |Rund- & kalfsvlees   |
      |NL       |ProbeerverpakkingenTab |Probeerverpakkingen  |Vis                |Vis                  |
      |NL       |ProbeerverpakkingenTab |Probeerverpakkingen  |Wild               |Wild                 |
      |NL       |WildFreedomTab         |WildFreedom          |GemengdVlees       |Gemengd vlees        |
      |NL       |WildFreedomTab         |WildFreedom          |Gevogelte          |Gevogelte            |
      |NL       |WildFreedomTab         |WildFreedom          |Kip                |Kip                  |
      |NL       |WildFreedomTab         |WildFreedom          |Konijn             |Konijn               |
      |NL       |WildFreedomTab         |WildFreedom          |Lam                |Lam                  |
      |NL       |WildFreedomTab         |WildFreedom          |Vis                |Vis                  |
      |NL       |WildFreedomTab         |WildFreedom          |Wild               |Wild                 |


  @Multifilter_NL
  Scenario Outline: Verify user able to select category and multiple filters
    Given User on "Home" page
    When User select "Country" as "<Country>"
    And Use click "<CategoryTab>"
    And "<CategoryTab>" highlighting with color "#5f929f"
    Then User on "<CategoryPage>" page
    And User click multiple "<Filter>" checkboxes
    And "SelectedFilters" values are displaying as "<FilterValues>"
    And Filters in query parameters are displaying as "<FilterValues>"


    Examples:
      |Country  |CategoryTab              |CategoryPage         |Filter                                         |FilterValues                                     |
      |NL       |AlleProductenTab         |AlleProducten        |GemengdVlees;Gevogelte;Konijn                  |Gemengd vlees;Gevogelte;Konijn                   |
      |NL       |DroogvoerTab             |Droogvoer            |Kip;Lam;RundAndKalfsvlees                      |Kip;Lam;Rund- & kalfsvlees                       |
      |NL       |NatvoerTab               |Natvoer              |Lam;Wild                                       |Lam;Wild                                         |
      |NL       |SnacksTab                |Snacks               |Konijn;Vis                                     |Konijn;Vis                                       |
      |NL       |ProbeerverpakkingenTab   |Probeerverpakkingen  |Lam;RundAndKalfsvlees                          |Lam;Rund- & kalfsvlees                           |
      |NL       |WildFreedomTab           |WildFreedom          |GemengdVlees;Gevogelte;Kip;Konijn;Lam;Vis;Wild |Gemengd vlees;Gevogelte;Kip;Konijn;Lam;Vis;Wild  |










