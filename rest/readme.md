# Rest Interface

Welcome to our project! This module contains a REST compliant interface for our
airport user. So far, the REST interface has controllers for Airport and Steward
entities. 

## Setup
Download the whole master branch and inside the project folder, 
run following command: ```mvn install && cd rest && mvn cargo:run```.
After that, the REST interface will be available at ```http://localhost:8080/pa165/rest```.

## Testing 

For testing of the interface you can use any reasonable data transfer tool, i.e. ```curl```
or ```postman```

## Examples

```curl -X GET -i http://localhost:8080/pa165/rest``` We get the entry point to our api

```json
{   
    "stewards_uri":"/stewards",
    "airports_uri":"/airports"
}
```

```curl -X GET -i http://localhost:8080/pa165/rest/airports``` This gets us all the airports. Initially, 
there are none.

```json
{   
    "links":[
              {
                "rel":"self",
                "href":"http://localhost:8080/pa165/rest/airports"
              }
            ],
    "content":[]
}
```

```curl -X GET -i http://localhost:8080/pa165/rest/airports/1``` We can try to get an airport with ID 1, but
obviously it fails.

```json
{   
    "timestamp":"2020/05/11 14:11:42",
    "status":"FAIL",
    "message":"Airport with ID 1 is not existing in the database"
}
```

``` curl -X POST -i -H "Content-Type: application/json" --data '{"id":"1", "city":"Frankfurt", "country":"Germany"}' http://localhost:8080/pa165/rest/airports/create```
Now let's add Frankfurt, Germany airport to the Database
```json
{
  "id":1,
  "city":"Frankfurt",
  "country":"Germany",
  "links":[
            {
              "rel":"CREATE",
              "href":"http://localhost:8080/pa165/rest/airports/create"
            },
            { 
              "rel":"READ",
              "href":"http://localhost:8080/pa165/rest/airports/1"
            },
            {
              "rel":"UPDATE",
              "href":"http://localhost:8080/pa165/rest/airports/update"
            },
            {
              "rel":"DELETE",
              "href":"http://localhost:8080/pa165/rest/airports/1"
            }
          ]
}
```

``` curl -X POST -i -H "Content-Type: application/json" --data '{"id":"1", "city":"Frankfurt", "country":"Germany"}' http://localhost:8080/pa165/rest/airports/create```
If we try to insert the same airport twice, we get the following error.

```json
{
  "timestamp":"2020/05/11 15:02:56",
  "status":"FAIL",
  "message":"Airport AirportDTO{id=1, city='Frankfurt', country='Germany'} already exists in the database"
}
```

```curl -X GET -i http://localhost:8080/pa165/rest/airports/1``` Getting airport with ID 1 return the airport
we created previously.
```json
{
  "id":1,
  "city":"Frankfurt",
  "country":"Germany",
  "links":[
            {
              "rel":"CREATE",
              "href":"http://localhost:8080/pa165/rest/airports/create"
            },
            { 
              "rel":"READ",
              "href":"http://localhost:8080/pa165/rest/airports/1"
            },
            {
              "rel":"UPDATE",
              "href":"http://localhost:8080/pa165/rest/airports/update"
            },
            {
              "rel":"DELETE",
              "href":"http://localhost:8080/pa165/rest/airports/1"
            }
          ]
}
```

From an unspecified reason, we suddenly stop being fond of the Frankfurt airport, and we want
to say goodbye to it. 
``` curl -X DELETE -i http://localhost:8080/pa165/rest/airports/1 ```
Let's see if it accidentally did not survive.
``` curl -X GET -i http://localhost:8080/pa165/rest/airports/1 ```
```json
{
    "timestamp":"2020/05/11 14:33:38",
    "status":"FAIL",
    "message":"Airport with ID 1 is not existing in the database"
}
```
:-)