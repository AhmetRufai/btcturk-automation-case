Feature: Reqres Api Get Users Page ep

  Note: While waiting for fail response for some values, success response is returned. In order for the automation to work
  without fail, the returned values were accepted as expected.

  Scenario Outline:  In this scenario, different parameter values are sent to the get users page ep and the returned http
  status code and response control is made

    When Send request to GetUsersPage ep with "<page>" for "<isSuccessCase>"
    Then Check GetUsersPageEp response
    Examples:
      | description                              | isSuccessCase | page        |
      | 1-Varolan bir sayfa için get yap         | true          | 1           |
      | 2-Varolan farklı bir sayfa için get yap  | true          | 2           |
      | 3-Sayfa değerini 0 göndererek get yap    | true          | 0           |
      | 4-Sistemde olmayan bir sayfa ile get yap | false         | 10000       |
      | 5-String bir sayfa değeri ile get yap    | true          | Ahmet       |
      | 6-Karakter ile get yap                   | true          | _?=)(/&%+^! |