Feature: Base end to end word frequency calculation

  Scenario: Read in book file, print base word frequency count
    Given a text file containing valid words/charecters exists
    And that file is placed in the correct read-in directory
    When the program is ran
    Then the frequency of each identical word is printed to the console

  Scenario: Read in stop words to array, print array pre word count
    Given a text file containing valid words/charecters exists
    And the file is placed in the correct read-in directory
    When the program is ran
    Then the words to exclude is printed to the console

  Scenario: Filter each book file word against a stop words array, to remove from print
    Given a valid book file and stop word file are in the correct read-in directory
    When the program is ran
    Then the frequency of each word not in the stop word list will be printed to the console
