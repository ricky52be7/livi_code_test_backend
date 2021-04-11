# Server info
URL: http://localhost:8080/  
Oauth2 URL: URL: http://localhost:8080/oauth/token  
Swagger UI URL: http://localhost:8080/swagger-ui/index.html

# Oauth2 User
|   | User   | Password | Role  |
|---|--------|----------|-------|
| 1 | user_1 | 123456   | ADMIN |
| 2 | user_2 | 123456   | USER  |

# Swagger OAS v3
Test Step:
 1. browse http://localhost:8080/swagger-ui/index.html
 2. enter /v3/api-docs in the explore box to go Open APi definition
 3. before test via Swagger, it need to authorize first by clicking Authorize button (please refer Oauth2 User section to login)
 4. open credit-controller to test /creditservice/v1/calculator

# Explanation
## DB Repository
The project aims to calculate credit score base on the rule. I assume that the project in real world is connecting to a DB. However in this project, I will create a RuleRepository and return data by just hardcoding.

The DB table will be

### Table: Rule
| Column Name | Data Type      | Remark      |
|-------------|----------------|-------------|
| id          | NUMERIC(10, 0) | Primary Key |
| name        | varchar2(45)   |             |
| type        | varchar2(45)   |             |
| config      | varchar2(1000) | Rule config in Json format |

## Oauth2
The application is using Spring framework Oauth2 library to implement the Oauth2 flow. User need to login via Oauth2 URL. And there are two type of role ADMIN and USER to simlulate 401 and 403 error. Before each call user must login to the application. User will get the access token from and user have to pass the token as "Bearer Authentication" to API call.

## Cucumber Test Case
there are 4 scenario in the project.

| Scenario Name                                                       | Remark                                                  |
|---------------------------------------------------------------------|------------------------------------------------------|
| Calculate Credit Assessment Score                                   | to test happy flow                                   |
| Calculate Credit Assessment Score and inputs are invalid            | to test invalid input and get http code 400          |
| Calculate Credit Assessment Score and the user is not authenticated | to test user not authenticated and get http code 403 |
| Calculate Credit Assessment Score and the user is not authorized    | to test user not authorized and get http code 401    |