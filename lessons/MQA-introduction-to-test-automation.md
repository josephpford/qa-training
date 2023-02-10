## Introduction

Test Automation is the use of software to verify the functionality of other software. Unit Testing is a type of Test Automation, but this lesson is about Functional Test Automation, or the creation of software that will automatically execute the manual test cases that were created by Quality Assurance Analysts, and most likely have been executed manually by the QA team over and over. Test Automation can partially or fully replace the manual execution of test cases. Test Automation allows teams to quickly verify changes did not inadvertently cause software regressions (defects). This lesson will give you a quick overview of Test Automation.

## Learning Outcomes

When you've finished this lesson and its exercises, you should:
* Understand what Automated Testing is at a high level
* Have an awareness about the wide selection of tools and frameworks available to help with Test Automation
* Understand 3 different methodologies for Frontend Test Automation 
  * Record and Playback
  * Keyword Driven
  * Data Driven

## What is Automated Testing

Automated Testing is any software used to test other software. It is a combination of (virtual) user actions to perform and validations of the system outputs. For example, a simple Automation Test may launch a browser, navigate to a particular webpage, fill out and submit a form, then verify the application displays the expected result. Typically Automated Testing works by exercising the application as an end-user would, or emulating an end-user by making API calls. After performing actions, frequently automation tests will perform validations by inspecting the UI, making API calls or database queries. 

In the case of Desktop Applications, Automated Testing may launch programs, move and click the mouse, type characters into text boxes, and compare bitmap images of expected and actual results on the screen. 

In the case of Browser applications, Automated Tests will launch a browser and interact with a web application. You can also spin up "headless" browsers. For Mobile Application Testing, there are emulators that are used for testing the application, but there are also companies that specialize in hosting automated testing services that have actual devices exercised as part of the automation. 

An Automated API Test may make HTTP requests and verify expected results by inspecting the HTTP response code and the response body. 

Automated Testing speeds up the software development lifecycle by having a collection of repeatable tests that can be executed quickly and reliably. 

Sauce Labs has an [excellent article](https://saucelabs.com/resources/topics/automated-testing) explaining what Automated Testing is all about. 

## Benefits and Drawbacks of Test Automation

Test Automation excels at performing highly repetitive, data intensive testing where expected outcomes are known. Mature applications with frequent incremental changes benefit from a comprehensive Test Automation Suite because they will get executed over and over and won't have to change significantly from release to release. Test Automation takes the burden off humans for repetitive tasks. 

Keeping Test Automation up to date with the System Under Test is necessary for the automated tests to function properly, so automated testing can be challenging to implement for an immature application because frequent changes to the system under test will force the automated tests to change as well. Following The [Test Pyramid metaphor](https://martinfowler.com/articles/practical-test-pyramid.html) can help focus efforts so that tests do not need to change as often. 

Test Automation is software itself, so it should also be written following [SOLID](https://en.wikipedia.org/wiki/SOLID) design principles. Historically Test Automation was written by test analysts, not experienced software engineers. This is rapidly changing as the industry recognizes the importance and benefits of test automation and the focus is moving from Quality Assurance to Quality Engineering.

## Summary

Software Automation is a rapidly growing and evolving field. There are many commercial and open source products to help create software test automation. Using good software development practices are important for test automation, because test automation code is software itself. There are many different approaches for writing Software Test Automation, and the techniques and tools used are evolving rapidly as more and more organizations adopt Quality Engineering practices. 

## Next
[Testing Automation Tools](./MQA-test-automation-tools.md)