# Lesson: Defects

## Introduction

Lorem ipsum

## Learning Outcomes

When you've finished this lesson and its exercises, you should be able to:
* Lorem ipsum
* Lorem ipsum
* Lorem ipsum

## title???

Defects (AKA Defect Reports, Bugs, Bug Reports) are opened whenever a test case fails or when ad-hoc testing is performed and an undesirable or questionable application behaviour is observed. Usually teams allow anyone to open a defect, but it is typically Testers, Developers, and Business Analysts/System Analysts that open defects. Defect Tracking tools are almost always used, and most of the time these defect tracking tools are tightly integrated with the other project tracking tools used, such as requirement documentation, task or "story" cards, etc.

Here is what a basic defect looks like:

```
Title/Summary 
User Configuration System Menu option missing when searching from System Menu 

Description
Was unable to find the User Configuration System option when searching for "User Configuration"
This regression was introduced in 56.7.2 and was successfully working in 56.7.1

Steps to Reproduce
- Login as User
- Go to the System menu.
- Search for "User Configuration"

Expected Result
User Configuration System Menu option is displayed.

Actual Result
User Configuration System Menu option is not displayed.

Work-around
The user can click on the hamburger menu option and find the User Configuration System option there and it works as expected. 

Screenshot attached.
```

Usually meta-data about the defect is also captured, and can include for example:
- Environment Found
    - Test
    - Stage
    - Prod
- Date Found
- Date Fixed/Closed
- Code Version
- Linked Test Case / Test Run
- Root Cause
    - Requirement
    - Coding issue
    - Environment issue
    - Data issue
- Severity (Most companies have custom priority/severity guidelines to follow)
    - Critical
    - Major
    - Moderate
    - Minor
- Priority
    - High
    - Medium
    - Low

Defects follow a company or team-defined defect tracking flow. Usually something like this:

![Defect Flow](../assets/defect_flow.png)

Some Teams opt to not allow any code to be released when there are known defects. Other teams, depending on the severity of the defect, will allow the code to be released despite known defects. Some teams hold periodic defect tracking meetings where some or all of the known existing defects will be discussed and either slotted for fixing in a future release, marked as "Closed Won't Fix" for various reasons, etc. Usually the Test Lead or QA Analyst on the team holds these meetings and invites key members of the team, including engineering leads, product / business owners, and project managers. 

## Exercises

Complete the following exercises
- [Manual Testing](../exercises/M15-exercise-manual-testing.md)

## Summary

Lorem ipsum
