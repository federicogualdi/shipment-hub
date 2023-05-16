# Shipment-Hub

# Backend Info
In this demo, we chose to use SqLite as the DB to manage the default values of certain entities and to showcase the capability of using an ORM.  
Specifically, we utilized Panache, which allows us to develop this demo more rapidly as it provides "built-in" methods for querying the database.  
> **_NOTE:_**  In order to correctly utilize SqLite, I had to create queries suitable for that engine.  
> 
> With other engines such as MySQL, PostgreSQL, etc., the choices for data types would have been different.  
> Specifically, the choices related to the data type for primary-keys in tables.


## Build Docker Image
A new parameter has been added to `application.properties` that automatically builds a Docker image by executing that command.
```shell script
 ./mvnw clean install
``` 

## Run Docker Image
```shell script
 docker run -p 8080:8080 <USER>/shipment-hub:1.0.0-SNAPSHOT
```

## Running the application in dev mode
You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
___
# Database Model
|   | Entity   |           Short Description | 
|---|----------|----------------------------:|
| 1 | Order    |                       Order |  
| 2 | Supplier | Supplier holding the orders |
| 3 | Package  |      Packages inside Orders |  
| 4 | Depot    |                       Depot | 

## Database Default Data

`Depot`

| id | name   |  latitude  | longitude  | 
|----|--------|:----------:|:----------:|
| 1  | Milano | 45.4639627 |  9.189885  |  
| 2  | Torino | 45.0684587 | 7.6748147  |
| 3  | Roma   | 41.8992204 | 12.5090232 |

`Supplier`

| id | name       |  
|----|------------|
| 1  | supplier-1 |  


---
# Endpoint

|   | Path                      | Method |             Short Description |
|---|---------------------------|:------:|------------------------------:|
| 1 | `/orders`                 |  POST  |              Insert new Order |
| 2 | `/orders/{order_id}`      |  PUT   |    Update some order's fields |
| 3 | `/orders/plan/{depot_id}` |  POST  | Start route planning by Depot |
| 4 | `/orders`                 |  GET   |               Get orders data |



1. Insert new Order
```bash
curl -X POST -H "Content-Type: application/json" -d '{
    "depotId": {depot_id},
    "packages": [
        {
            "latitude": 41.9028,
            "longitude": 12.4964
        },
        {
            "latitude": 48.8566,
            "longitude": 2.3522
        },
        {
            "latitude": 51.5074,
            "longitude": -0.1278
        },
        {
            "latitude": 52.5200,
            "longitude": 13.4050
        }
    ]
}' http://localhost:8080/orders
```


2. Update some order's fields
```bash
curl -X PUT -H "Content-Type: application/json" -d '{
    "depotId": 2,
    "packages": [
        {
            "coordinate": {
                "latitude": 998,
                "longitude": 0
            }
        },
        {
        },
        {
            "status": "IN_TRANSIT"
        },
        {
            "status": "LOST",
            "coordinate": {
                "latitude": 9.0,
                "longitude": 5.4
            }
        }
    ]
}' http://localhost:8080/orders/{order_id}
```


3. Start route planning by Depot
```bash
curl -X POST -H "Content-Type: application/json" http://localhost:8080/orders/plan/{depot_id}
```


4. Get orders data
```bash
curl -X GET -H "Content-Type: application/json" \
"localhost:8080/orders?orderBy={orderBy}" \
"&orderDirection={orderDirection}" \
"&skip={skip}" \
"&top={top}"
```
```bash
curl -X GET -H "Content-Type: application/json" "localhost:8080/orders?orderBy=id&orderDirection=asc&skip=0&top=20"
```


