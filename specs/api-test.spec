# Test Employee Web-Services Application
This is an executable specification file. This file follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

To execute this specification, run
	gauge run specs



Verify if we are able to get the list of employees
---------------------------------------------------

* Given a "EMPLOYEE_API"
* When a user makes a GET request
* Then the status code from "EMPLOYEE_API" should be "200"
//* And response includes the following
  //  |Key                            |Value                |
    //|-------------------------------|---------------------|
//	| origin		                | 31.205.46.10, 31.205.46.10	  |










