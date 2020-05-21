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

We can get the entry points to our REST api.

```curl -X GET -i http://localhost:8080/pa165/rest/``` 

```json
{
    "flights_uri": "/flights",
    "stewards_uri": "/stewards",
    "airplanes_uri": "/airplanes",
    "airports_uri": "/airports"
}
```

If we execute this command 

```curl -X GET -i http://localhost:8080/pa165/rest/airports``` ,
 
 we get a _401 unauthorized access response_. Of course, we forgot to include
the authorization header. In curl, we can for example use ```--user admin:admin```. If you are using postman,
select _Basic Auth_ under _Authorization_ tab, and enter admin username and admin password.


```curl --user admin:admin -X GET -i http://localhost:8080/pa165/rest/airports```
This gets us all the airports. Initially, there is one airport in Gbelany, Slovakia.

```json
{
    "links": [
        {
            "rel": "self",
            "href": "http://localhost:8080/pa165/rest/airports"
        }
    ],
    "content": [
        {
            "id": 3,
            "city": "Gbelany",
            "country": "Slovakia",
            "links": [
                {
                    "rel": "CREATE",
                    "href": "http://localhost:8080/pa165/rest/airports/create"
                },
                {
                    "rel": "READ",
                    "href": "http://localhost:8080/pa165/rest/airports/3"
                },
                {
                    "rel": "UPDATE",
                    "href": "http://localhost:8080/pa165/rest/airports/update"
                },
                {
                    "rel": "DELETE",
                    "href": "http://localhost:8080/pa165/rest/airports/3"
                }
            ]
        }
    ]
}
```
We can try to get an airport with non-existent ID, but
that will obviously fail.


```curl --user admin:admin -X GET -i http://localhost:8080/pa165/rest/airports/1``` 
```json
{   
    "timestamp":"2020/05/11 14:11:42",
    "status": "FAIL",
    "message": "Airport with ID 1 does not exist in the database"
}
```

Now let's add Frankfurt, Germany airport to the database.

```curl --user admin:admin -X POST -i -H "Content-Type: application/json" --data '{"city":"Frankfurt", "country":"Germany"}' http://localhost:8080/pa165/rest/airports/auth/create```

```json
{
    "id": 4,
    "city": "Frankfurt",
    "country": "Germany",
    "links": [
        {
            "rel": "CREATE",
            "href": "http://localhost:8080/pa165/rest/airports/create"
        },
        {
            "rel": "READ",
            "href": "http://localhost:8080/pa165/rest/airports/4"
        },
        {
            "rel": "UPDATE",
            "href": "http://localhost:8080/pa165/rest/airports/update"
        },
        {
            "rel": "DELETE",
            "href": "http://localhost:8080/pa165/rest/airports/4"
        }
    ]
}
``` 
 This time, when we try to get an airport with ID 4
 
  ```curl --user admin:admin -X GET -i http://localhost:8080/pa165/rest/airports/4```,
   
   it returns the correct airport we had created previously.
 

```json
{
    "id": 4,
    "city": "Frankfurt",
    "country": "Germany",
    "links": [
        {
            "rel": "CREATE",
            "href": "http://localhost:8080/pa165/rest/airports/create"
        },
        {
            "rel": "READ",
            "href": "http://localhost:8080/pa165/rest/airports/4"
        },
        {
            "rel": "UPDATE",
            "href": "http://localhost:8080/pa165/rest/airports/update"
        },
        {
            "rel": "DELETE",
            "href": "http://localhost:8080/pa165/rest/airports/4"
        }
    ]
}
```

From an unspecified reason, we suddenly stop being fond of the Frankfurt airport, and we want
to say goodbye to it. 

``` curl --user admin:admin -X DELETE -i http://localhost:8080/pa165/rest/airports/auth/delete/4 ```

Let's see if it accidentally did not survive.

``` curl --user admin:admin -X GET -i http://localhost:8080/pa165/rest/airports/4 ```
```json
{
    "timestamp":"2020/05/11 14:33:38",
     "status": "FAIL",
     "message": "Airport with ID 4 does not exist in the database"
}
```
:-)