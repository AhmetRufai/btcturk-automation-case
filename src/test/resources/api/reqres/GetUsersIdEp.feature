Feature: Reqres Api Get Users ep

  Scenario Outline:  In this scenario, different parameter values are sent to the get users id EP and the returned http
  status code and response control is made

    When Send request to GetUsersId ep with "<id>" for "<isSuccessCase>"
    Then Check GetUsersIdEp response
    Examples:
      | description                           | isSuccessCase | id          |
      | 1-Sistemde olan bir id ile get yap    | true          | 1           |
      | 2-Id değerini 0 göndererek get yap    | false         | 0           |
      | 3-Sistemde olmayan bir id ile get yap | false         | 10000       |
      | 4-String bir id değer ile get yap     | false         | Ahmet       |
      | 5-Karakter ile get yap                | false         | _?=)(/&%+^! |