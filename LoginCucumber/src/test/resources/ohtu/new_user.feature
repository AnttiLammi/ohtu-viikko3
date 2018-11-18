Feature: A new user account can be created if a proper unused username and password are given
    Scenario: creation is successful with valid username and password
    Given command new user is selected
    When username "eero" and password "salainen1" are entered
    Then system will respond with "new user registered"

    Scenario: creation fails with already taken username and valid password
    Given command new user is selected
    When username "eero" and password "salainen1" are entered
    And command new user is selected
    And username "eero" and password "salainen2" are entered
    Then system will respond with "new user not registered"

    Scenario: creation fails with too short username and valid password
    Given command new user is selected
    When username "ee" and password "salainen1" are entered
    Then system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
    Given command new user is selected
    When username "eero" and password "salain1" are entered
    Then system will respond with "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting only of letters
    Given command new user is selected
    When username "eero" and password "salainen" are entered
    Then system will respond with "new user not registered"

    Scenario: can login successfully with generated account
    Given command new user is selected
    When username "eero" and password "salainen1" are entered
    And command login is selected
    And username "eero" and password "salainen1" are entered
    Then system will respond with "logged in"
