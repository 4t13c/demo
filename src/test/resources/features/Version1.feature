Feature: the version can be retrieved
  Scenario: client makes call to GET /version
    When the client calls /version
    Then the client receives status code of 200
    And the client receives server version 0.1

  Scenario: client makes call to GET price filtered by some data
    #Given a price request:brandId:1,pricelistid:3,productId:2,applicationDate:2020-06-16-00.00.00
    When the client calls /prices
    Then the client receives a price response