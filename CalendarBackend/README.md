# Spotify Calendar Backend Documentation



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


### Parameters

| Name    | Type      | Description                          |
|---------|-----------|--------------------------------------|
| q			| String			| **optional** <p>Query to search.</p>							|
| page			| Number			| **optional** <p>Page number.</p>							|
| limit			| Number			| **optional** <p>Amount of returned items.</p>							|
| sort			| String[]			| **optional** <p>Order of returned items.</p>							|
| fields			| String[]			| **optional** <p>Fields to be returned.</p>							|

## Update events



	PUT /events/:id


### Parameters

| Name    | Type      | Description                          |
|---------|-----------|--------------------------------------|
| title			| 			|  <p>Events's title.</p>							|
| date			| 			|  <p>Events's date.</p>							|
| description			| 			|  <p>Events's description.</p>							|
| time			| 			|  <p>Events's time.</p>							|


