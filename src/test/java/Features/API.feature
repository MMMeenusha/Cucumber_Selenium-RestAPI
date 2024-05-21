@APIFeature
Feature: API testing
  I want to test API

  @smoke @Regressions @trial
  Scenario: getThePlaceID
    #Arrange
    Given Create Input body through POJO Class
    #Act
    When Requesting response from Place API
    #Assert
    Then Display the Place ID from response through POJO Class