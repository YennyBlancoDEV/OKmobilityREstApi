Documentation to deploy and test the OkMobility application app v.2.

Enviroment set up: 

Eclipse enviroment + spring boots application

Sprint version : 3.0

MySql dababase locally:

	version 9.0

	
Spring configuration   :

	Project: Gradle
	Language: Java
	Spring Boot: 3.0
	Java: 22
	Dependencies: Spring Web, MySql


Public and prived key set up for basic autentication, this files are in the resources file.

Postman desktop app.

-------

Instalation steps: 

Pre instalation: set up java enviroment with the vesion on your pc. 

1) Install SQL 9.0 , 

	Verify user, password, port in application.properties file in the resoruces folder

	instanciate it and create database 'Operations'


2) Install Eclipse workspace and from the market place in eclipse install the sprint boot application to complete env setup 

3) Import the project to eclipse and regrest the gradle dependencies. 

4) Select Run as sprint boot App in eclipse to deploy aplication

5) Perform testing on the postman app as describe bellow. 

Note: The default port of the Tomcat server is 8080 and can be changed in the application.properties file.

-----

Testing steps and sample data to test in Postman app.

1) Try to perform a autentication for a user not register yet:

Request:
		POST: localhost:8080/auth/login
		{
		"email": "pereiramaria@gmail.cm",
		"password": "!passwoArd127"
		}

Result:  401 Unathorized

2) register a new user not register yet:

Request:
		POST: localhost:8080/auth/register
		{
		"firstName": "Martha",
		"lastName":"Smith",
		"email": "martasmith@gmail.com",
		"password": "passwordArd1453!"
		}


Result: 200 Ok 
{
    "numOfErrors": 0,
    "message": "User created successfully!"
}


3) Login with the same user : 

POST: localhost:8080/auth/login

	{
	"email": "pereiramaria@gmail.com",
	"password": "!passwoArd1237"
	}

Result: 202 acepted  

	{
	    "jwt": "eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE3MjA0MDQ4MDgsInN1YiI6IjEiLCJpYXQiOjE3MjAzOTA0MDh9.pvWIF-e5eSMtm7TIvxXPgBxGw8VRFtSyAKqEGe5a2cDcCo6xetPKCHkO0JoMqERP6gFl2sP-50V2nr2O6o9pF4rWtJomhDLuL8LJlA7G1B_ptFXUjnmamFK6EHGbUONjxtu1UD5QD9FjOmJAc-L3KN7-a51gG9IzTbLUBNQGYkJmI98NGRJBOzGB0pPLHFbyhlr7b6guKZm3o0TXAB852B7nqfpeZuQbWC_XDgs5MVWsTvj04f28sw6DIQgORXgMB_rnsziezetgr3hAFRgH9_N4R6EnJZHwcDYhW3nTuiobMwKx5nBKlwMRcH_pATVvWzkrvXT_cVkWjkE3CkiAFQ"
	}

4) Create a new Operation Task: 

POST: localhost:8080/operation/create
	{
	"operationName":"paint the new area just build    ",
	"actionDescription": "paint, registration and organization required",
	"parentTaskId":"0",
	"taskStatus":"new",
	"expiringDate":"20.09.2024"
	}

Add Authorization/ Auth type/ Bearer Token

	Token : eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE3MjA0MDQ4MDgsInN1YiI6IjEiLCJpYXQiOjE3MjAzOTA0MDh9.pvWIF-e5eSMtm7TIvxXPgBxGw8VRFtSyAKqEGe5a2cDcCo6xetPKCHkO0JoMqERP6gFl2sP-50V2nr2O6o9pF4rWtJomhDLuL8LJlA7G1B_ptFXUjnmamFK6EHGbUONjxtu1UD5QD9FjOmJAc-L3KN7-a51gG9IzTbLUBNQGYkJmI98NGRJBOzGB0pPLHFbyhlr7b6guKZm3o0TXAB852B7nqfpeZuQbWC_XDgs5MVWsTvj04f28sw6DIQgORXgMB_rnsziezetgr3hAFRgH9_N4R6EnJZHwcDYhW3nTuiobMwKx5nBKlwMRcH_pATVvWzkrvXT_cVkWjkE3CkiAFQ


Result: 201 created
{
    "taskId": 8,
    "operationName": "paint the new area just build    ",
    "actionDescription": "paint, registration and organization required",
    "parentTaskId": "0",
    "taskStatus": "new",
    "expiringDate": "20.09.2024"
}


Plus... create 3 more with this one as its parent...   "parentTaskId":"01",


5) List all Operations: 

GET : localhost:8080/operation/listAllOperations

Add Authorization/ Auth type/ Bearer Token

	Token : eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE3MjA0MDQ4MDgsInN1YiI6IjEiLCJpYXQiOjE3MjAzOTA0MDh9.pvWIF-e5eSMtm7TIvxXPgBxGw8VRFtSyAKqEGe5a2cDcCo6xetPKCHkO0JoMqERP6gFl2sP-50V2nr2O6o9pF4rWtJomhDLuL8LJlA7G1B_ptFXUjnmamFK6EHGbUONjxtu1UD5QD9FjOmJAc-L3KN7-a51gG9IzTbLUBNQGYkJmI98NGRJBOzGB0pPLHFbyhlr7b6guKZm3o0TXAB852B7nqfpeZuQbWC_XDgs5MVWsTvj04f28sw6DIQgORXgMB_rnsziezetgr3hAFRgH9_N4R6EnJZHwcDYhW3nTuiobMwKx5nBKlwMRcH_pATVvWzkrvXT_cVkWjkE3CkiAFQ


Result: 

[
    {
        "taskId": 9,
        "operationName": "NEW store preparation    ",
        "actionDescription": "registration and organization required",
        "parentTaskId": 0,
        "taskStatus": "new",
        "expiringDate": "20.09.2024"
    },
    {
        "taskId": 10,
        "operationName": "Start the law paper work    ",
        "actionDescription": "registration and organization required",
        "parentTaskId": 9,
        "taskStatus": "new",
        "expiringDate": "20.07.2024"
    },
    {
        "taskId": 11,
        "operationName": "Start call the builders    ",
        "actionDescription": "registration and organization required",
        "parentTaskId": 9,
        "taskStatus": "new",
        "expiringDate": "20.07.2024"
    },
    {
        "taskId": 12,
        "operationName": "Stop the cards request     ",
        "actionDescription": "registration and organization required",
        "parentTaskId": 9,
        "taskStatus": "new",
        "expiringDate": "20.08.2024"
    }
]



6) Update a subtask (ID 12 ) as completed: 

PUT : localhost:8080/operation/update/12


Add Authorization/ Auth type/ Bearer Token

	Token : eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE3MjA0MDQ4MDgsInN1YiI6IjEiLCJpYXQiOjE3MjAzOTA0MDh9.pvWIF-e5eSMtm7TIvxXPgBxGw8VRFtSyAKqEGe5a2cDcCo6xetPKCHkO0JoMqERP6gFl2sP-50V2nr2O6o9pF4rWtJomhDLuL8LJlA7G1B_ptFXUjnmamFK6EHGbUONjxtu1UD5QD9FjOmJAc-L3KN7-a51gG9IzTbLUBNQGYkJmI98NGRJBOzGB0pPLHFbyhlr7b6guKZm3o0TXAB852B7nqfpeZuQbWC_XDgs5MVWsTvj04f28sw6DIQgORXgMB_rnsziezetgr3hAFRgH9_N4R6EnJZHwcDYhW3nTuiobMwKx5nBKlwMRcH_pATVvWzkrvXT_cVkWjkE3CkiAFQ

Result:  200 ok
{
    "taskId": 12,
	"operationName": "Stop the cards request     ",
	"actionDescription": "registration and organization required",
	"parentTaskId": 9,
	"taskStatus": "COMPLETED",
	"expiringDate": "20.08.2024"
}


7) Delete a subtask (ID 12 ) as completed: 

PUT : localhost:8080/operation/update/12


Add Authorization/ Auth type/ Bearer Token

	Token : eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE3MjA0MDQ4MDgsInN1YiI6IjEiLCJpYXQiOjE3MjAzOTA0MDh9.pvWIF-e5eSMtm7TIvxXPgBxGw8VRFtSyAKqEGe5a2cDcCo6xetPKCHkO0JoMqERP6gFl2sP-50V2nr2O6o9pF4rWtJomhDLuL8LJlA7G1B_ptFXUjnmamFK6EHGbUONjxtu1UD5QD9FjOmJAc-L3KN7-a51gG9IzTbLUBNQGYkJmI98NGRJBOzGB0pPLHFbyhlr7b6guKZm3o0TXAB852B7nqfpeZuQbWC_XDgs5MVWsTvj04f28sw6DIQgORXgMB_rnsziezetgr3hAFRgH9_N4R6EnJZHwcDYhW3nTuiobMwKx5nBKlwMRcH_pATVvWzkrvXT_cVkWjkE3CkiAFQ

Result:  200 ok

{
    "message": "Operation deleted succesfully!"
}

