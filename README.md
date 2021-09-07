# TransferServiceAPI - App
A TransferServiceAPI Application for fund transfer transactions : A Spring boot application which provides a RESTful API for fund transfer transactions between source account and destination account.
 
### Prerequisite
- JDK 1.8+
- Maven

### Project Structure
```bash
TransferServiceAPI
├── src
│   ├── main
│   │   ├── java
│	  │   │   ├── com.natwest.microservice
│	  │   │   ├── com.natwest.microservice.controller
│	  │   │   ├── com.natwest.microservice.dao.data
│	  │   │   ├── com.natwest.microservice.model
│	  │   │   ├── com.natwest.microservice.service
│	  │   │   ├── com.natwest.microservice.transaction
│   │   └── resources
│   │   	└── application.properties
│   └── test
│       ├── java
│	      │   ├── com.natwest.microservice
│	      │   ├── com.natwest.microservice.controller
│	      │   ├── com.natwest.microservice.dao.data
│	      │   ├── com.natwest.microservice.service
│	      │   ├── com.natwest.microservice.transaction
│	      │   ├── com.natwest.microservices.test.base
│	      │   ├── com.natwest.microservices.test.client
│       └── resources
├── LICENSE
├── .gitignore
├── pom.xml
└── README.md
```
### Build, Test and Package
```
mvn clean install
```
### Run the application - port# 8012
```
java -jar TransferServiceAPI-0.0.1-SNAPSHOT.jar

```
### Use the below URL to test from the rest client
```
POST http://localhost:8012/ms/app/v1/transfer-service
```
### Web Service Request Example i.e REQUEST BODY 
```
{"transactionModel" : {	"srcAccountNo" : 1001, "destAccountNo" : 1002, "amount" : 100 }}

```
### Web Service Response Example i.e RESPONSE BODY if HTTP Status 200
```
{"srcAccountModel": { "accountNo": 1001, "balance": 900 }, "destAccountModel": { "accountNo": 1002, "balance": 1100 }, "errorMessage": null }

```
### Data 
The following two accounts have been used in this application and it will be loaded as default accounts for money transfer.
Source Account {source account number, balance} -> {1001L, 1000}
Destination Account {destination account number, balance} -> {1002L, 1000}
## Feature
This application has been developed with one API,
- transfer-service
### Basic API Information
| Method | Path 						| Usage 		|
| ---    | --- 							| --- 			|
| POST   | /ms/app/v1/transfer-service 				| transfer fund |

### Error Messages
| Error Messages | Usage |
| ---  		|	---|	
| Destination account should be differnt | used when both accounts are same  |
| Source account is not found | used when source account is not found |
| Destination account is not found | used when destination account is not found |
| There is no sufficient funds in the account | used when there is no sufficient funds in source account |

