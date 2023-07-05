@tag
Feature: Flipkart Functionality

  @tag1
  Scenario: Search the Product and Sort the order according to price
    Given Search with product as "shoes"
    Then i will click on price Low to High
    Then I should see the prices for all products till Page 2 are in ascending order

    
    @tag2
  Scenario: Search the Product and Sort the order according to price
    Given Search with product as "shoes"
    Then i will click on price Low to High
    And Click on the second available product in the list, and click on Add to cart
    And Click on the third available product in the list, and click on Add to cart
    Then I will click on Cart and move to Cart page
    Then validate the correct products are added with correct Price
    And validate the Total sum