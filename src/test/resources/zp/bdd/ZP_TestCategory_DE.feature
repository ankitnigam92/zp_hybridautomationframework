Feature: Verify category with selection filter for DE

  Background: Navigate to home page
    Given User navigate to the application home

  @SingleFilter_DE
  Scenario Outline: Verify user able to select category and filter for DE
    Given User on "Home" page
    When User select "Country" as "<Country>"
    And Use click "<CategoryTab>"
    And "<CategoryTab>" highlighting with color "#5f929f"
    Then User on "<CategoryPage>" page
    And User click "<Filter>" checkbox
    And "SelectedFilters" values are displaying as "<FilterValues>"
    And Filters in query parameters are displaying as "<FilterValues>"


    Examples:
      |Country  |CategoryTab            |CategoryPage         |Filter               |FilterValues           |
      |DE       |TrockenfutterTab       |Trockenfutter        |Fisch                |Fisch                  |
      |DE       |TrockenfutterTab       |Trockenfutter        |Geflügel             |Geflügel               |
      |DE       |TrockenfutterTab       |Trockenfutter        |GemischtesFleisch    |gemischtes Fleisch     |
      |DE       |TrockenfutterTab       |Trockenfutter        |Huhn                 |Huhn                   |
      |DE       |TrockenfutterTab       |Trockenfutter        |Lamm                 |Lamm                   |
      |DE       |TrockenfutterTab       |Trockenfutter        |RindAndKalb          |Rind & Kalb            |
      |DE       |TrockenfutterTab       |Trockenfutter        |Wild                 |Wild                   |
      |DE       |NassfutterTab          |Nassfutter           |Fisch                |Fisch                  |
      |DE       |NassfutterTab          |Nassfutter           |Geflügel             |Geflügel               |
      |DE       |NassfutterTab          |Nassfutter           |Huhn                 |Huhn                   |
      |DE       |NassfutterTab          |Nassfutter           |Lamm                 |Lamm                   |
      |DE       |NassfutterTab          |Nassfutter           |RindAndKalb          |Rind & Kalb            |
      |DE       |NassfutterTab          |Nassfutter           |Wild                 |Wild                   |
      |DE       |SnacksTab              |Snacks               |Fisch                |Fisch                  |
      |DE       |SnacksTab              |Snacks               |Hase                 |Hase                   |
      |DE       |SnacksTab              |Snacks               |Huhn                 |Huhn                   |
      |DE       |SnacksTab              |Snacks               |Lamm                 |Lamm                   |
      |DE       |SnacksTab              |Snacks               |RindAndKalb          |Rind & Kalb            |
      |DE       |SnacksTab              |Snacks               |Wild                 |Wild                   |
      |DE       |ProbierpaketeTab       |Probierpakete        |Fisch                |Fisch                  |
      |DE       |ProbierpaketeTab       |Probierpakete        |Geflügel             |Geflügel               |
      |DE       |ProbierpaketeTab       |Probierpakete        |Hase                 |Hase                   |
      |DE       |ProbierpaketeTab       |Probierpakete        |Huhn                 |Huhn                   |
      |DE       |ProbierpaketeTab       |Probierpakete        |Lamm                 |Lamm                   |
      |DE       |ProbierpaketeTab       |Probierpakete        |RindAndKalb          |Rind & Kalb            |
      |DE       |ProbierpaketeTab       |Probierpakete        |Wild                 |Wild                   |
      |DE       |AccessoiresTab         |Accessoires          |Bälle                |Bälle                  |
      |DE       |AccessoiresTab         |Accessoires          |BefüllbaresSpielzeug |befüllbares Spielzeug  |
      |DE       |AccessoiresTab         |Accessoires          |Taue                 |Taue                   |
      |DE       |AccessoiresTab         |Accessoires          |WeiteresSpielzeug    |weiteres Spielzeug     |
      |DE       |WildFreedomTab         |WildFreedom          |Fisch                |Fisch                  |
      |DE       |WildFreedomTab         |WildFreedom          |Geflügel             |Geflügel               |
      |DE       |WildFreedomTab         |WildFreedom          |GemischtesFleisch    |gemischtes Fleisch     |
      |DE       |WildFreedomTab         |WildFreedom          |Hase                 |Hase                   |
      |DE       |WildFreedomTab         |WildFreedom          |Huhn                 |Huhn                   |
      |DE       |WildFreedomTab         |WildFreedom          |Lamm                 |Lamm                   |
      |DE       |WildFreedomTab         |WildFreedom          |Wild                 |Wild                   |



  @Multifilter_DE
  Scenario Outline: Verify user able to select category and multiple filters for DE
    Given User on "Home" page
    When User select "Country" as "<Country>"
    And Use click "<CategoryTab>"
    And "<CategoryTab>" highlighting with color "#5f929f"
    Then User on "<CategoryPage>" page
    And User click multiple "<Filter>" checkboxes
    And "SelectedFilters" values are displaying as "<FilterValues>"
    And Filters in query parameters are displaying as "<FilterValues>"


    Examples:
      |Country  |CategoryTab              |CategoryPage     |Filter                                         |FilterValues                                     |
      |DE       |TrockenfutterTab         |Trockenfutter    |Fisch;GemischtesFleisch;Huhn                   |Fisch;gemischtes Fleisch;Huhn                    |
      |DE       |NassfutterTab            |Nassfutter       |Fisch;Geflügel;Huhn;Lamm;RindAndKalb;Wild      |Fisch;Geflügel;Huhn;Lamm;Rind & Kalb ;Wild       |
      |DE       |SnacksTab                |Snacks           |Fisch;Wild                                     |Fisch;Wild                                       |
      |DE       |ProbierpaketeTab         |Probierpakete    |Hase;Huhn                                      |Hase;Huhn                                        |
      |DE       |AccessoiresTab           |Accessoires      |Bälle;Taue;WeiteresSpielzeug                   |Bälle;Taue;weiteres Spielzeug                    |
      |DE       |WildFreedomTab           |WildFreedom      |Geflügel;GemischtesFleisch;Lamm                |Geflügel;gemischtes Fleisch;Lamm                 |










