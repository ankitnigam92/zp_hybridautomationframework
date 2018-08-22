# HybridAutomation Framework

This is a test automation framework to implement the Tasks for ZooPlus. I have designed a Hybrid Automation Framework to test **Web GUI** test cases and integrated with Jenkins.

To design this framework I am using **Cucumber** + **Selenium WebDriver** libraries. 

_In this framework I have tried catering all the requirements mentioned in the automation task._ 



## Getting Started

### Prerequisites

- [Java](https://www.java.com/en/download/index.jsp)
- [Configure Maven](https://maven.apache.org/download.cgi)
- [Cucumber Reports Plugin - Jenkins](https://wiki.jenkins.io/display/JENKINS/Cucumber+Reports+Plugin)
- Browser Chrome

:exclamation: I have checkedin binaries on the github to facilitate the execution via Jenkins. In real work we keep 
selenium-server-standalone.jar and driver exe on server and node machines.

:exclamation: In absence of mutiple machines I am using single machine to create 1 Hub and 2 Nodes.

## Running the tests

All test cases are available under **\test\resources\zp\bdd** folder. 

[Import attached AutomationJob.xml file in Jenkins and click build now](https://github.com/ankitnigam92/zp_hybridautomationframework/raw/master/jenkins_config/AutomationJob.xml)

```
java -jar jenkins-cli.jar -s http://jenkins-server create-job AutomationJob < AutomationJob.xml
```

> For this task I am using chrome browser but framework can support firefox browser and other platforms.


### Framework details:

```
Features
```

* **Feature** : In Cucumber, we write our test cases as .feature files

To script the required test scenarios I have created 2 test cases(features):

 1. ZP_TestCategory_DE.feature
 2. ZP_TestCategory_NL.feature

> Each feature file is implementing category and filter (flavour or toys) combinations for DE and NL countries.
> In scripts I am testing single category - single filter or single category - multi filter combinations.

:exclamation: To write the features, I am following **Imperative style** instead of **Declarative style**. As per my experience, Imperative style makes framework more re-usable to test other applications and functional testers can easily adopt the scripting.

```
Repository
```

* Instead of using traditional POM for designing framework. I am maintaining objects, divided by pages in **repository.xml**
* Using unmarshalling to load xml to objects and extracting locators at runtime

```
Driver
```
* Driver folder contains DriverManger. It allows us to test application with different browsers like _Chrome and Firefox_. However, I have tested my scripts only with Chrome browser.
* User can pass browser and platform using **-Dbrowser={browser_name}** and **-Dplatform={platform_name}** properties.
* Environment details like app urls can also be set using properties in pom.xml.

```
ActionSteps and Action
```
* The sentences we wrote in the features are linked with Java functions available in `src/test/java` folder
* Using ActionStep functions only to call Action functions and performing assertions
* All the script execution logic are written in Action functions

```
TestData
```
* I am maintaining test data in the feature files.

```
Reports
```
* On Jenkins using Cucumber Reports Plugin.

_Test Results_

 <img src="https://github.com/ankitnigam92/zp_hybridautomationframework/raw/master/result_screenshots/overall.png" alt="Test Report" style="width: 600px;"/>
 <img src="https://github.com/ankitnigam92/zp_hybridautomationframework/raw/master/result_screenshots/steps.png" alt="Test Steps" style="width: 600px;"/>
 <img src="https://github.com/ankitnigam92/zp_hybridautomationframework/raw/master/result_screenshots/totalcases.png" alt="Tags" style="width: 600px;"/>


```
Logs
```
Generating custom logs under zp_logs/ folders

```
Improvements
```
Number of improvements can be put in place to reduce the future maintenance efforts. Few quick ones are:

* Separating test data from cucumber scenarios.
* Setup profiles in pom to make it run on different environments and parameters.
* Removing binary files from GitHub.
* Adding more details in log and dividing them in three separate files like regression logs, debug logs and error logs.
* Granular exception handling.

## Author

**Ankit Nigam**
