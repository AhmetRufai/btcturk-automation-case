Feature: Controls on the Simple Buy/Sell page on the BtcTurk site

  Scenario Outline: In this scenario, the 24h highest and 24h lowest amount was compared for BTC/TRY and USDT/TRY options

    Given Open browser with "<browser>" and go to home page
    When Switch to Simple Buy & Sell page
    And Get 24hMax and 24hMin price
    Then Compare max and min price
    When Select usdt-try
    And Get 24hMax and 24hMin price
    Then Compare max and min price
    Examples:
      | browser |
      | chrome  |
      | firefox |
      | edge    |
      | safari  |
      | opera   |