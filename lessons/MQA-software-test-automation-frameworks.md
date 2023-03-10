## Software Test Automation Approaches

### Record and Playback

Record and Playback is the simplest form of Test Automation. For Record and Playback, first the tester opens an automation tool and clicks Record, then performs steps to exercise the application and instructs the automation tool to add "verification check points" at various points in the test, then clicks Stop. The automation tool will then convert those actions and verifications into the code of choice for this tool (e.g. JavaScript, Java, VBS, C#, or many other languages). This code can then be saved as any other code would (to disk, git, shared folder, etc), and then played back at some point in the future. The automation tool would then display a report indicating test case status (pass/fail), possibly along with screenshots of the verifications points. Since these tests are just code, the code can be copied and edited into new tests to test other scenarios. The code may look something like this:

```
enterText("//*[@id='username']", "admin");
enterSecureText("//*[@id='password']", "pa$$word");
clickButton("//*[@id='login']");
verifyText("//*[@id='grt_msg']", "Welcome Admin!");
clickButton("//*[@id='addAgent']");
enterText("//*[@id='fname']", "Bob");
enterText("//*[@id='lname']", "Johnson");
enterText("//*[@id='dob']", "01/01/1970");
clickButton("//*[@id='submit']");
verifyText("//*[@id='response_msg']", "Agent Bob Johnson added successfully.");
clickButton("//*[@id='logout']");
```

As you can imagine, Record and Playback can quickly become unwieldy as the Test Automation Suite expands. Each automated test is independent and there are no shared components. A single change in the application can cause 100% of the test cases to need to be changed. These Record and Playback scripts are a great way to get started and can be thought of as the "Hello World" of Test Automation, but they wouldn't be the first choice for mature engineering teams. 

### Keyword Driven

The next approach we will explore is Keyword Driven. For Keyword Driven automation, you create a collection of "keywords" each with 0-many parameters. Each keyword will represent a concrete action or a combination of concrete actions possibly including verification steps. These keywords will be stored in a central location, and all tests will reference these keywords. 

```
enterUsername("admin");
enterPassword("pa$$word");
clickButtonWithText("Login");
verifyGreeting("Welcome Admin!");
clickButtonWithText("Add Agent");
enterFirstName("Bob");
enterLastName("Johnson");
enterDob("01/01/1970");
clickButtonWithText("Submit");
verifyResponse("Agent Bob Johnson added successfully.");
clickButtonWithText("Logout");
```

This is much better than Record and Playback, but what if an "Are you sure?" message is added to the application after the Submit button is pressed? Now all tests which try to add an agent will need to be updated. With Keyword Driven tests, you can also group a set of keywords into a new keyword. The above sequence of steps could be re-written as:

```
login("admin", "pa$$word");
verifyGreeting("Welcome Admin!");
addAgent("Bob", "Johnson", "01/01/1970");
verifyResponse("Agent Bob Johnson added successfully.");
logout();
```

If the "Are you sure?" dialog is added to the application, only the "addAgent" keyword will need to be updated.

#### Keyword Driven (Parameterized)

Frequently multiple iterations of identical steps are performed for testing. For example, our Add Agent test could be repeated multiple times with different usernames, different dates, and different verification messages. We can extract out the data into parameters and create a single method that is called multiple times for each test. 

```
runAddAgentTest("Bob", "Johnson", "01/01/1970", "Agent Bob Johnson added successfully.");
runAddAgentTest("Bob", "", "01/01/1970", "Last name is required.");
runAddAgentTest("", "Johnson", "01/01/1970", "First name is required.");
runAddAgentTest("Bob", "Johnson", "", "DOB is required.");
runAddAgentTest("Bob", "Johnson", "01/01/2022", "Agent must be at least 18 years old.");
```

Note that there will be completely different groups of test cases that don't follow the same steps as runAddAgentTest (e.g. runDeleteAgentTest, runEditAgentTest, etc). Each of these groups of test cases can be automated as one test case (as a Java method) that takes different input and expected results. 

#### Keyword Driven (Externalized Test Data)

Next you can extract the data out of the test suite and put it into a database or an excel spreadsheet. This allows you to quickly make new tests by simply adding new rows to a table. 

| Test ID | Test Title            | First Name | Last Name | DOB        | Expected Message                      |
|---------|-----------------------|------------|-----------|------------|---------------------------------------|
| 1 | Happy Path | Bob | Johnson | 01/01/1970 | Agent Bob Johnson added successfully. |
| 2 | Last name should be required | Bob |  | 01/01/1970 | Last name is required. |
| 3 | First name should be required |  | Johnson | 01/01/1970 | First name is required. |
| 4 | DOB should be required | Bob | Johnson |  | DOB is required. |
| 5 | Agent must be 18 years old | Bob | Johnson | 01/01/2022 | Agent must be at least 18 years old. |

```
String[][] allTests = getTestDataFromDatabaseOrSpreadsheet();
for (String[] testData : allTests) {
    runAddAgentSuccessTest(testData[0], testData[1], testData[2], testData[3]);
}
```

### Data Driven

With Keyword Driven tests, the test execution flow is dictated by the code. In the above example, there is a runAddAgentTest, but also a runEditAgentTest and a runDeleteAgentTest. Every "flow" needs to be written in code separately. With Data Driven tests, the "flow" is moved out of the code and into the external data file. 

Suppose the next iteration of Field Agent allows users create Agencies and also associate Agents to Agencies. The Test Team may want to execute these two similar but different tests:
- Test 1: Create Agent, then Agency, then link
- Test 2: Create Agency, then Agent, then link

With Keyword Driven tests, this would require you to create a keyword for createAgent, and createAgency and linkAgent. But you would need to have two separate test methods, you couldn't simply have 1 test method for both of these tests because the test steps are different. With Data Driven testing, you introduce flow control by having each test have their own separate steps in the data. Your Automation code is no longer an "Automated Test Case" but a Test Driver or Test Executor. The Test Cases are contained within the data itself. Below is a simple example of how this could work. There are much more elaborate designs which allow for common actions to be grouped together.

Test Table

| Test ID | Test Title            | 
|---------|-----------------------|
| 1 | Create Agent, then Agency, then link |
| 2 | Create Agency, then Agent, then link |

Test Step Table

| Test ID | Test Step | Keyword |
|---------|---------|---------|
| 1 | 1 | login | 
| 1 | 2 | verifyGreeting |
| 1 | 3 | addAgent |
| 1 | 4 | addAgency |
| 1 | 5 | linkAgent |
| 1 | 6 | verifyResponse |
| 2 | 1 | login | 
| 2 | 2 | verifyGreeting |
| 2 | 3 | addAgency |
| 2 | 4 | addAgent |
| 2 | 5 | linkAgent |
| 2 | 6 | verifyResponse |

Test Step Detail Table

| Test ID | Test Step | Test Data Key | Test Data Value |
|---------|---------|---------|---------|
| 1 | 1 | username | admin |
| 1 | 1 | password | pa$$word |
| 1 | 2 | greeting | Welcome Admin! |
| 1 | 3 | firstName | Bob |
| 1 | 3 | lastName | Johnson |
| 1 | 3 | DOB | 01/01/1970 |
| 1 | 4 | agencyName | ACME Agency |
| 1 | 4 | address | 123 Happy Street |
| 1 | 5 | agentName | Bob Johnson |
| 1 | 5 | agencyName | ACME Agency |
| 1 | 6 | response | Agent Bob Johnson was successfully assigned to ACME Agency. |
| 2 | 1 | username | admin |
| 2 | 1 | password | pa$$word |
| 2 | 2 | greeting | Welcome Admin! |
| 2 | 3 | agencyName | ACME Agency |
| 2 | 3 | address | 123 Happy Street |
| 2 | 4 | firstName | Bob |
| 2 | 4 | lastName | Johnson |
| 2 | 4 | DOB | 01/01/1970 |
| 2 | 5 | agentName | Bob Johnson |
| 2 | 5 | agencyName | ACME Agency |
| 2 | 6 | response | Agent Bob Johnson was successfully assigned to ACME Agency. |