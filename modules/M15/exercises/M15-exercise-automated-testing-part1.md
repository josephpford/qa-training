## Introduction

In this exercise, we will build on the last exercise by automating (some of) the existing test cases.
- Identify test cases for automation
- Record and Playback one of the test cases using Selenium IDE
- Convert the test case into Keyword Driven format by extracting out common functionality into Java methods
- Extract test data and expected results to an external data file

## Learning Outcomes

When you've finished this exercise you should be able to write basic browser-based software test automation.
 - Part 1: Installation
 - Part 2: Record and Playback
 - Part 3: Keyword Driven - Parameterized
 - Part 4: Keyword Driven - Parameterized w/ External Data File

## Part 1: Installation
- Selenium IDE
  - Selenium IDE is a browser extension for Chrome and Firefox. Follow the [installation instructions](https://www.selenium.dev/selenium-ide/). 
- Selenium Client and WebDriver Language Bindings
  - These are libraries (JARs) which provide the API for Selenium for Java, C#, Ruby, etc.. You can read the [JavaDocs](https://www.selenium.dev/selenium/docs/api/java/overview-summary.html). We will be installing this using Maven. Nothing to do for now...
- Selenium WebDriver
  - WebDriver is an executable that allows your Java code to open Chrome or Firefox and automate browser activity. Normally you have to download an exact version of WebDriver for your exact version of Chrome or Firefox. And you need to tell your Java code where to find your executable. WebDriverManager is an open-source Java library that carries out the management (i.e., download, setup, and maintenance) of the drivers required by WebDriver. We will be installing Selenium WebDriver using WebDriverManager, and we will be installing WebDriverManager using Maven. Nothing to do for now...

