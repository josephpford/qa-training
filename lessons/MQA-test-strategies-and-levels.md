## Test Strategies and Testing Levels (unit, integration, system, performance, etc.)

In order to help facilitate discussions among engineers, architects, testers and managers, there is a need to be able to clearly articulate different types of tests. Here we will introduce some of these terms and provide a loose definition of this concept. Please note that different organizations and different teams may use some of these terms differently. You can use this as a loose guide to help you understand these terms, but know that you may require clarification from your particular assignment.

### Test Strategies
Test Strategies (AKA Test Methods) can be applied to any Test Level below. At a high-level, there are two: Black Box and White Box. 
  - [Black Box testing](https://en.wikipedia.org/wiki/Black-box_testing) is testing where you put on your "end user" hat and only manipulate the input and verify the output. What happens behind the curtain is of no concern to you. Behind the scenes there may be [NoSQL databases](https://en.wikipedia.org/wiki/NoSQL), [Message Queues](https://en.wikipedia.org/wiki/Message_queue) and complicated [ETL](https://en.wikipedia.org/wiki/Extract,_transform,_load) processes, but none of that is your concern as a Black Box tester. All you care about is "If I do X, I better see Y happen"
  - [White Box testing](https://en.wikipedia.org/wiki/White-box_testing) is where you put your technical hat on and verify more than just what the end-user sees. Perhaps after clicking Submit, the system is supposed to put a message onto a queue which allows a downstream system to read those messages and updates a NoSQL database. It's possible the User Interface is displaying all values to the end user correctly, but the message that is put onto the queue is malformed so the database isn't updated correctly as expected. Or maybe the front-end has validation that prevents you from exercising all the business logic that has been developed in the API. As a White Box tester, pretty much anything is fair game. You can manually manipulate the data in the database to see how the UI reacts with bad data. You could put test messages onto the message queue to see how the system responds when presented with missing or invalid data.

### Test Levels

[Test Levels](https://en.wikipedia.org/wiki/Software_testing#Testing_types,_techniques_and_tactics) (AKA Test Types) are different ways of grouping tests. They help to hold conversations with other people. "Hey, can you run a Smoke Test after I deploy this change to Production?", "We need to run our Regression Tests to make sure we didn't introduce any bugs into our existing features!". 

- [Functional Testing](https://en.wikipedia.org/wiki/Functional_testing)
  - Functional testing is any type of testing which can easily be verified by simply using the software.
  - Typically both white and black box
  - [Regression Testing](https://en.wikipedia.org/wiki/Regression_testing)
    - Regression Testing is an umbrella term to describe testing that validates that behavior that worked in previous releases continues to work as expected in future releases. For example, in Release 1, the product allowed the user to submit an event for scheduling a birthday party. In Release 2, the product allowed the user to submit an event for scheduling an office party. In Release 2, it is important to verify that the birthday party functionality that was released in Release 1 still functions as expected. Testing the birthday party functionality would be considered a Regression Test. If the birthday party functionality does not work, it is called a "regression".
  - [System Testing](https://en.wikipedia.org/wiki/System_testing)
    - System Testing usually means to test a system in isolation from other systems. For example, if the system that you are testing consists of an input queue, a rules processing engine, a database, and an output queue, you may perform all of your testing by putting messages onto the input queue, verify the data in the database and verify the output messages on the output queue. As part of System Testing, you would not be responsible for testing the systems which put messages onto the input queue. You also would not be responsible for testing the system that takes messages off the output queue. 
  - [Integration Testing / System Integration Testing](https://en.wikipedia.org/wiki/Integration_testing)
    - Integration Testing is testing the integration of two or more components. It is a very generic term that can mean a lot of things. It could be testing the integration of the "Controller" and the "Service" layers together and could be part of the code's unit test suite. It could also mean testing how our product integrates with a 3rd party vendor's API. The term is very generic, and when discussing "integration testing" with other professionals, make sure you clearly understand what it is that is being "integration" tested.
  - [User Acceptance Testing](https://en.wikipedia.org/wiki/Acceptance_testing)
    - User Acceptance Testing (UAT) is conducted to ensure that the system can be used by the user for the intended purpose. 
    - If the system is for making airline reservations, then UAT would ensure that a user can actually use the system to make an airline reservation.
    - UAT is usually performed by a product owner or their team, but its goal is to ensure that the software meets all the expectations of the actual end-user. 
    - UAT is generally only black-box testing
  - [Smoke Testing](https://en.wikipedia.org/wiki/Smoke_testing_(software))
    - Smoke testing is a term used to describe a minimal set of validations that can/should occur for a "sanity check" that the software was deployed successfully and that there are no environmental issues.
    - Sometimes code works perfectly fine in a test environment, but once it is deployed to production, it doesn't work correctly because the data in the test environment was set up differently or perhaps there are firewalls in place in production that do not exist in the test environment.
    - Some example smoke tests include: 
      - Is the correct version of the code deployed?
      - Do the [Health Check APIs](https://www.ibm.com/garage/method/practices/manage/health-check-apis/) all pass?
      - Can users log in / submit a record / log out?
  - [Accessibility Testing](https://www.a11yproject.com) Accessibility testing is verifying your application is usable (accessible) to as many people as possible. Accessibility testing ensures your application is usable by people with physical, visual, hearing disabilities, but it also includes testing for people that are using older or less powerful equipment or people who lack access to high-speed internet. 
  - [Exploratory/Ad-hoc Testing](https://www.atlassian.com/continuous-delivery/software-testing/exploratory-testing)
    - Exploratory testing is the process of using the software without a set of predetermined steps and trying to uncover hard to find defects.
    - Most "Testing" is intentionally well-documented, to provide proof as to what has been verified as working (PASSED), what has not yet been verified as working (NOT RUN), and what has been identified as not working (FAILED).
    - Exploratory Testing is different from other types of testing in that it really cannot be documented well. The whole purpose of exploratory testing it is to allow the creativity and free thinking of the exploratory tester to find defects. It can difficult to document all the tests that were performed by the tester. However, if any defects were found, the steps to reproduce that defect should be documented as well as possible as a target for future testing.
      - Screen capture technologies and system generated log files can help with this
  - [Performance Testing](https://en.wikipedia.org/wiki/Software_performance_testing)
    - Performance Testing is an umbrella term to describe all aspects of testing that include an application's ability to respond in a timely manner under all sorts of load scenarios. We will dive into Performance Testing in depth on Day 4.
    - Load Testing
    - Stress Testing
    - Spike Testing
    - Endurance Testing
    - Scalability Testing

## Next:
[Careers in Quality Engineering](./MQA-careers-in-quality-engineering.md)