# WALLET MICROSERVICE

A wallet microservice running on the JVM that manages wallet credit/debit transactions.


## Description
A Wallet Rest API to access wallet accounts with the current balance and transaction of a user.
The balance can be modified by registering transactions on the account, either debit transactions (removing funds) 
or credit transactions (adding funds).

A debit transaction will only succeed if there are sufficient funds on the account 
(balance - debit amount >= 0). 

Unique global transaction id must be provided when registering a transaction on an account. 

It is also possible to fetch the users accounts and get current balance.

## Api requirements and running instructions
1. Java 23
2. Maven 3 to build the application.
This will activate Flyway to run db creation scripts. 
3. The Wallet project use an Embedded H2 Database.
It is available here:
   https://docs.spring.io/spring-boot/reference/data/sql.html

Client can be installed from https://squirrel-sql.sourceforge.io/ 

4. Database will be initialized automatically when the Application starts.
5. For the database wallet configuration in application.properties:
```
# setup local h2 database config
spring.datasource.url=jdbc:h2:file:./data/wallet
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
 ```
 ```
Obs: If you want to use a database client you need make the client configuration.
The database file will be created in jdbc:h2:file:~/data/wallet
You can import database from you preferred client.
 ```
6. Observability
 ```
   run the follow command if you have docker in your computer. if no follow the steps:
   https://docs.docker.com/desktop/setup/install/windows-install/
   
   And after: 
   docker run -d -p 9411:9411 openzipkin/zipkin-slim
   
 ```
6. Open your IDE and import project:
```
Intellij for example:
Go to File > Open > directory that you cloned the application.
 ```
7. Start application:
```
All tables, reference data will be created and inserted by Flyway.
```
```
mvn spring-boot:run or use the IDE
``` 
8. To check that application started successfully access http://localhost:8090:
``` 
http://localhost:8090/
``` 
This should produce result:
``` 
Wallet Microservice!
``` 
The port can be also configured from 
``` 
recargapay-wallet-service/src/main/resources/application.properties
```
see 'server.port' property

## Api endpoints
Examples of all requests can be found in:
Import the json file to Postman and use the follows endpoints to test.
``` 
RecargaPay.postman_collection.json
```
Http GET endpoints:
1. http://localhost:8090/wallets
Gets all wallets
Some wallets are generated after the first start of the application by Flyway.

2. http://localhost:8090/wallets/{id}
Gets wallet with transactions

3. http://localhost:8090/wallets/user?userId=user1
Gets wallet with transactions by user id

4. http://localhost:8080/wallets/{id}/transactions
Gets list of transactions by wallet id
Some transactions are generated after the first start of the application by Flyway.

Http POST endpoints:
1. http://localhost:8090/wallets
With the following JSON in the body:
``` 
{
"currency":"{currency}",
"userId":"{userId}"
}
``` 
Creates new wallet.
e.g.
``` 
{
"currency":"EUR",
"userId":"new-user"
}
``` 
Will create new wallet with currency EUR and userId=new-user.
The currency id should be present in the reference table 'currency'.

2. http://localhost:8080/transactions
With the following JSON in the body:
Generate a globalId on 
``` 
{"globalId":"b7e7fa0c-f71b-11ef-9cd2-0242ac120002",
"currency":"EUR",
"walletId": "2",
"amount":"20",
"transactionTypeId":"D",
"description":"add money"
}
``` 
for credit transaction.
``` 
will create credit transaction.
{"globalId":"558",
"currency":"EUR",
"walletId": "2",
"amount":"20",
"transactionTypeId":"D",
"description":"add money"
}
``` 
for debit transaction.
Creates transaction.
'globalId' must be unique.
'currency','transactionTypeId' and 'walletId' must be present in the db.
'currency' should be the same as in wallet.

## Technology used

- H2 database.
- Spring Boot, including Spring Data JPA for JPA based repositories.
- Flyway,tool db migration
- logback + slf4j for logging.
- Gson from com.google.code.gson to serialize objects to JSON.
- 

## Support of the aspects:

1. Transactions on service and repository level ensure atomicity.
2. Identifiers for entities and primary keys in the db ensure idempotency. 
As well as unique globalIds for wallet transactions.
3. Scalability: This can be solved by installing recargapay-wallet-service application on several hosts, 
with Database server running on it own host/hosts(can be distributed).
Load balancer (e.g. NGINX) will share requests between the application instances and provide high-availability.
Shared cache can be configured for Spring application. For example,
Spring supports Redis, which can be used as shared cache. 
But since this application is targeted on transactions creation, it might not be useful,
because we have to update cache too often.

## Features not implemented
1. Security (Information Exchange) - Can be implemented using JWT.
2. Authentication - Can be used Spring Security.
3. Authorization - Can be implemented using JWT.
4. Scaling - Can be implemented using cloud provider like AWS Auto Scaling.



 








