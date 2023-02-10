# Exercise: Manual Test Cases

In this exercise, you will review requirements for the Field Agent UI, then create and execute manual test cases based on the requirements. You will then write up defects for any deviations from the requirements discovered. 

## Step 1: Prepare the test environment

- Open the `react-field-agent-solution/server/` folder in IntelliJ as a Maven project and run the SpringBootApplication (App)
  - You will probably need to add this to your Run Configuration: `FIELD_AGENT_DB_USERNAME=root; FIELD_AGENT_DB_PASSWORD=passwd`
- Start MySQL, then open `react-field-agent-solution/server/sql/field-agent-schema-prod.sql` in MySQL Workbench and run it
- Open a Terminal and in `react-field-agent-solution/client/` execute `npm install` and `npm run` 
- Open a browser and navigate to http://localhost:3000/

## Step 2: Review Field Agent UI Requirements
### Main UI
- Displays all agents
- Each agent should display: Last Name, First Name, Birthday, Edit and Delete buttons
- Agents should be sorted by Last Name, then First Name, then Birthday
- If Birthday is not known, display "unknown"

### Add/Edit
- First Name is required
- Middle Name is optional
- Last Name is required
- Birthday is optional, but if provided, agents must be at least 16 years old.
- Height in inches is optional, but if provided must be between 36 and 96 inches
- First, Middle and Last name combinations must be unique.

### Delete
- Must be able to delete any agent.

## Step 3: Create Manual Test Cases
- Use Excel to document Test Cases.
- Download the provided [Test Case Sample](../assets/TestCaseSample.xlsx) as a starting point.
- Work individually or break off into groups. 
- Create Test Cases based on the Requirements
- Consider the following Testing Types as you create Test Cases
  - Happy Path Testing
  - Negative Testing
  - Ad-Hoc Testing
  - API Testing
  - All Pairs / Pairwise Testing
  - Boundary Testing

## Step 4: Execute Manual Test Cases and Open Defect Reports
- Create a copy of your Excel spreadsheet and name it Field-Agent-Test-Run-01.xlsx
  - This copy will become your first Test Run.
- Execute each of your test cases.
- Document defects in Github for any deviations from expected results.
  - Use Github "issues"
  - Navigate to any personal repository and navigate to "Issues" (e.g. https://github.com/someuser/java-fundamentals-student/issues)
  - Assign the defects to your designated instructor.
  - Defects should follow this format:

```
Title: 
First Name should be required

Description:
First Name should be required, but is not.

Steps to reproduce:
1. Load the Agent UI: http://localhost:3000/

2. Click Add Agent

3. Enter the form as follows:
First Name: __________ (blank)
Middle Name: MiddleTest
Last Name: LastTest
Date of Birth: 01/01/1901
Height in Inches: 66

4. Click Add Agent

Expected Result:
Error is displayed indicating First Name is required.

Actual Result:
No error is displayed. The agent without first name was saved to the database and displayed in the Field Agent Main UI.

Attached please find screenshot of Add Agent screen as well as a screenshot of the Field Agent Main UI displaying the newly added agent.
```
