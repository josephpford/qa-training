# Lesson: Careers in Quality Engineering

## Introduction

Quality Engineering is a vast domain, and there are numerous roles available. In this lesson we explore some of the more common roles you may perform as a Quality Engineering professional.

## Learning Outcomes

When you've finished this lesson you should be able to 
* Articulate how a Quality Engineer fits into a typical software development team
* Understand three technical career paths in Quality Engineering

## Quality Careers / Titles

While this list is not exhaustive, below are some of the more common roles in the industry.

### Quality Assurance Analyst / Tester

People in these positions spend their time creating and (manually) executing [Test Plans](https://en.wikipedia.org/wiki/Test_plan) and [Test Cases](https://en.wikipedia.org/wiki/Test_case). Test Plans are high-level documents which spell out the types of testing that will take place. Test Cases are step-by-step instructions of what to do once testing starts. Test Plans and Test Cases can be mapped back to requirements with [requirements traceability matrices](https://www.softwaretestinghelp.com/requirements-traceability-matrix/).

The step-by-step instructions are important not just to ensure that the execution is done correctly, but after a [Test Run](https://www.testmonitor.com/blog/test-case-test-suite-test-run-whats-the-difference) is executed, it can become an important piece of compliance for [SOX](https://en.wikipedia.org/wiki/Sarbanes%E2%80%93Oxley_Act) or [FDA](https://www.fda.gov/files/medical%20devices/published/General-Principles-of-Software-Validation---Final-Guidance-for-Industry-and-FDA-Staff.pdf) regulated environments. Quality Assurance Analysts create defect reports, and may hold Defect Triage meetings to prioritize defects and [determine root case](https://en.wikipedia.org/wiki/Root_cause_analysis).

### Automation Engineer / Automation Architect

Automation Engineers create software or scripts which automates functional test cases. The test cases may already have been identified, or the automation engineer will identify the test cases as they are being automated. If existing test cases are to be automated, there may be decisions to as to which test cases make the most sense for automation. For example, if there are many similar test cases that just have different data (for example, to verify a bank's compliance with APR calculations across different state jurisdictions), they may be a great candidate for automation as a single script can be executed over and over with different data. Or if there is software that is released every 2 weeks (as can be the case in an Agile project), the test cases for that software should be considered as candidates for automation over some other product that is only released once a year.

Automation Engineers use many tools to write the automation. There are many commercial and open source tools available. You'll learn more about the various test tools later in this module.

### Performance Engineer

Performance Engineers create software or scripts which emulate tens/hundreds/thousands/etc. of user or system interactions per second. Generally a small subset of the functional test cases are identified as good candidates for scripting. Then the performance engineer will write the code necessary to simulate a user performing that test case, and most likely parameterize the data inputs to vary the mock user behavior. The performance test scripts will be executed in various ways to test the system for stability or acceptable performance across various user loads. Again, there are many commercial and open source tools available. 

## Summary

There are many different roles in Quality Engineering. Some roles are more technical than others and require programming or scripting skills. Quality Assurance Analysts (or Testers) generally perform manual testing to look for defects. Automation engineers create automation scripts which automate test case execution. Performance Engineers focus on various aspects of system performance to ensure the system works under various loads and scenarios.

## Next:
[Quality Engineering Summary](./MQA-qe-overview-summary.md)