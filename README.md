# Cinema Room REST Service

## About 

A manager for your small private cinema that is handled through Http requests, create services and respond with JSON objects.
Built on Spring, this REST service manager helps you to show the seats available, sell and refund tickets and display statistics of your dome. 
### *`(server.port=9090)`*
The movie theater has 9 rows  each of 9 seats.  
Our movie theater is managed by several endpoints that return information in JSON format.  
* `GET /seats ` - requests and returns the information about the movie theatre.  
```
{
   "total_rows":5,
   "total_columns":6,
   "available_seats":[
      {
         "row":1,
         "column":1
      },

      ........

      {
         "row":5,
         "column":5
      },
      {
         "row":5,
         "column":6
      }
   ]
}
```
* `POST /purchase` - requests and marks a booked ticket as purchased.  
The requests are passed with JSON body:  
```
{
    "row": 3,
    "column": 4
}
```
If the purchase is successful, the response body should be as follows:  
```
{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```
The ticket price is determined by a row number. If the row number is less or equal to 4, set the price at 10. All other rows cost 8 per seat.  
The ticket token is determined by `randomUUID()`, which is used to identify the purchase, to support an option to return a ticket.  

If the seat is taken, the app responds with a 400 (***BAD_REQUEST***) status code. The response body should contain the following:  
```
{
    "error": "The ticket has been already purchased!"
}
```
If users pass a wrong row/column number, the app responds with a 400 (***BAD_REQUEST***) status code and the following line:  
```
{
    "error": "The number of a row or a column is out of bounds!"
}
```
* `POST /return` - handles requests and allow customers to refund their tickets.  
The requsts are passed with JSON body:  
```
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```
Respond body:
```
{
    "returned_ticket": {
        "row": 1,
        "column": 2,
        "price": 10
    }
}
```
If service cannot identify the ticket by the token, the program responds with a 400 (***BAD_REQUEST***) status code and the following response body:  
```
{
    "error": "Wrong token!"
}
```
And the last endpoint:
* `POST /stats` - If the URL parameters contain a password key with a `super_secret` value, returns the movie theatre statistics in the following format:  
```
{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
}
```
If the parameters don't contain a password key or a wrong value has been passed, it responds with a 401 (***UNAUTHORIZED***) status code. The response body should contain the following:

{
    "error": "The password is wrong!"
}
## Technical Observations
Statistics are updated each purchase directly in the endpoints instead of retrieving statistics by calculating data from data structures, though calculations are made for relatively small number of indices - 81 for each data structure.  
**To test the api you can use [Postman](https://www.postman.com/)**.
