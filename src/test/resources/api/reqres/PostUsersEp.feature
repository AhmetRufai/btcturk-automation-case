Feature: Reqres Api Post Users ep

  Scenario Outline: In this scenario, different parameter values are sent to the post users EP and the returned http
  status code and response control is made

    When Send request to PostUsersEp with "<name>","<job>" for "<isSuccessCase>"
    Then Check PostUsersEp response
    Examples:
      | description                                  | isSuccessCase | name                                          | job                                                              |
      | 1-Name ve Job'ı tek text gönder              | true          | Ahmet                                         | Engineer                                                         |
      | 2-Aynı Name ve Job'ı ikinci kez gönder       | true          | Ahmet                                         | Engineer                                                         |
      | 3-Name'i tek text, job'ı çift text gönder    | true          | Ahmet                                         | QA Engineer                                                      |
      | 4-Name'i çift text, job'ı tek text gönder    | true          | Ahmet Rufai                                   | Engineer                                                         |
      | 5-Name ve Job'ı çift text string gönder      | true          | Ahmet Rufai                                   | QA Engineer                                                      |
      | 6-Name'i ve Job'ı türkçe karakter gönder     | true          | ÖöŞşÜüİıÇçĞğ                                  | ÖöŞşÜüİıÇçĞğ                                                     |
      | 7-Name'i string, Job'ı decimal gönder        | true          | Ahmet                                         | 42.0                                                             |
      | 8-Name'i decimal, Job'ı string gönder        | true          | 42.0                                          | Engineer                                                         |
      | 9-Name'i ve Job'ı decimal gönder             | true          | 42.0                                          | 42.0                                                             |
      | 10-Name'i string, Job'ı boş gönder           | true          | Ahmet                                         |                                                                  |
      | 11-Name'i boş, Job'ı string gönder           | true          |                                               | Engineer                                                         |
      | 12-Name'i ve Job'ı boş gönder                | true          |                                               |                                                                  |
      | 13-Name'i string, job'ı boşluk gönder        | true          | Ahmet                                         | BLANK                                                            |
      | 14-Name'i boşluk, job'ı string gönder        | true          | BLANK                                         | Engineer                                                         |
      | 15-Name'i ve Job'ı boşluk gönder             | true          | BLANK                                         | BLANK                                                            |
      | 16-Name'i string, job'ı null gönder          | true          | Ahmet                                         | NULL                                                             |
      | 17-Name'i null, job'ı string gönder          | true          | NULL                                          | Engineer                                                         |
      | 18-Name'i ve Job'ı null gönder               | true          | NULL                                          | NULL                                                             |
      | 19-Name'in içerisinde karakter gönder        | true          | *?/()&%+^;:.,!                                | Engineer                                                         |
      | 20-Job'ın içerisinde karakter gönder         | true          | Ahmet                                         | *?/()&%+^;:.,!                                                   |
      | 21-Name ve Job'ın içerisinde karakter gönder | true          | *?/()&%+^;:.,!                                | *?/()&%+^;:.,!                                                   |
      | 22-Name'i uzun, job'a normal string gönder   | true          | AhmetAhmetAhmetAhmetAhmetAhmetAhmetAhmetAhmet | Engineer                                                         |
      | 23-Name'i normal, job'a uzun string gönder   | true          | Ahmet                                         | Engineer                                                         |
      | 24-Name'e ve job'a uzun string gönder        | true          | Ahmet                                         | EngineerEngineerEngineerEngineerEngineerEngineerEngineerEngineer |