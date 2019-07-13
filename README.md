# major-selection
Project written for SWE 3643: Systems Testing & Quality Assurance

## Overview 
The purpose of this project was to utilize the test-driven development process to create a working prototype for an application that houses a repository of college departments and their information. An existing Excel database (CollegeDatabase.xlsx), which contains all the information the prototype will need to make decisions, and a test suite (MajorDBTest.java) were provided. The goal was for the code to be written to pass all JUnit tests.

The specific instructions were as follows: <blockquote>The client runs the Major Selection tool to get information about current majors at Kennesaw State University. The application outputs to the screen the number of departments, along with relevant information: Department Name, University ID, Department Chair, Associated College, and Average Years To Earn Degree. It also prints the most recent Potential Earnings for students in that department. After that, it asks the client if they want to continue or exit. In the former case, it repeats the output again.</blockquote>

## Project Components
+ **CollegeDatabase.xlsx:** Excel database containing information about departments in the university.
+ **MajorDBTest.java:** JUnit testing suite, the code is written to pass this.
+ **MajorDB.java:** MajorDB class. This is the core of the application. This class takes in a database given a relative address of an Excel workbook. It retrieves the information from this database, and creates a HashSet of Department objects containing all departments included in the given database.
+ **MajorSelection.java:** The MajorSelection tool can be run to get information about current departments at the university. Extends MajorDB to display information based on user input.
+ **Department.java:** This object contains an individual Department, including all records and relevant information. Contains the name, ID, and chair of the Department, as well as the college it is part of. Houses HashMaps of the potential earnings by year, time to graduate by year, and job satisfaction rate by year.
