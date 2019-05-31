**API Test automation task**

Target API: http://openweathermap.org/current

Setup and Run Instructions 

1. Download source code from GitLab: https://github.com/arekon7/AutomationApiTask
2. Open "AutomationApiTask" Project with IntelliJ or simmilar IDE
3. In directory "./src/main/java/com/apitesting/" are located: TestData, BaseClass, common parameters, methods, reporting classes, messages
4. In directory "./src/test/java/com.qa.rest.tests./" is located test suite "GetWeatherDataTest.java" with test cases.
5. In order to run tests Maven dependencies in "./pom.xml" should be synchronized 

Run Tests
1. Open test suite (GetWeatherDataTest.java)
2. Run "GetWeatherDataTest" to run all test cases
3. You can also run each test case separately
4. After test run Extent report is generated in: "./test-report/TestReport.html".

Mini Framework was created using Java language and REST Assured, JUnit, TestNG libraries. 
By Test cases are covered:
* Validation of status codes (200,400,401,404)
* Validation of response types (JSON, XML, HTML) 
* Data getting by coordinates
* Data getting by location id
* Data integrity between location and ID
* Response body structure validation
* API key validation
* Measurement units validation


These areas were chosen to cover because this is main functionality of current API.
Some tests (test data) are intentionally designed to fail.


