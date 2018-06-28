# Calendar Backend Documentation
## Endpoint
```https://spotical.herokuapp.com/events```


- [Events](#events)
	- [Create events](#create-events)
	- [Delete events](#delete-events)
	- [Retrieve events](#retrieve-events)
	- [Update events](#update-events)
	


# Events

## Create events



	POST /events


### Parameters

| Name    | Type      | Description                          |
|---------|-----------|--------------------------------------|
| title			| 			|  <p>Events's title.</p>							|
| date			| 			|  <p>Events's date.</p>							|
| description			| 			|  <p>Events's description.</p>							|
| time			| 			|  <p>Events's time.</p>							|

## Delete events



	DELETE /events/:id


## Retrieve events



	GET /events


## Update events



	PUT /events/:id


### Parameters

| Name    | Type      | Description                          |
|---------|-----------|--------------------------------------|
| title			| String			|  <p>Events's title.</p>							|
| date			| String			|  <p>Events's date.</p>							|
| description			| String			|  <p>Events's description.</p>							|
| time			| String			|  <p>Events's time.</p>							|


