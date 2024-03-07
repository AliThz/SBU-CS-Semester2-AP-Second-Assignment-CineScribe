# Project Title

AP Second Assignment : CineScribe

## Description

This is a report of how i fulfilled this assignment.

## Json Parsing

* When I send a get request to the api and the respond is a json including all data about the actor. For using that information and mapping it into our class, we have to parse it.
* There are few approaches of parsing Json in Java. As you can see in the code I chose to work with JsonObject class.
```
JSONObject jsonObject = new JSONObject(moviesInfoJson);
```
* When there is a single value for a particular key, it's very easy to parse that value and use it. For instance to parse a string in this situation :
```
String string = jsonObject.getString("string");
```
* If there is multiple values for a particular key, we should use JsonArray. This is an example of parsing JsonArray from the project:
```
JSONObject jsonObject = new JSONObject(actorsInfoJson);
JSONArray allOccupations = jsonObject.getJSONArray("occupation");
allOccupations.forEach(o -> this.occupations.add(o.toString()));
```
* In my opinion the most tricky examples of parsing Json is when we face nested Jsons. Here is part of the code parsing nested Jsons: in movie Json there is another Json about ratings and each rating has a source and a value. Ratings is itself an array of keys and values so I assign the whole ratings to a JsonArray and assign my desired(accessed that by index) rating to a Json object and then get the value of that:   
```
JSONObject jsonObject = new JSONObject(moviesInfoJson);
JSONArray ratings = jsonObject.getJSONArray("Ratings");
JSONObject rating = ratings.getJSONObject(0);
String result = rating.getString("Value");
return result;
```

## Actors Class

### Extending Class

* I add seven new attributes to the class including name, gender, nationality, occupations, height, birthday, isAlive.
* So I implemented a get method for each of the new attributes.
* Therefore, overloaded the constructor with one explicit parameter that is name. This constructor gets the Json including all the information of the actor from api and initialize every instance variables with their get methods. 

### Bugs

* During some get requests, the Json response was a list and has "[]" at the beginning and the end of it so I pass a substring of my Json string to the JsonObject constructor that starts from index 1 and ends at an index before the last.

## Movie Class

### Extending Class

* I add eight new attributes to the class including title, year, contentRating, releaseDate, runtimeInMinutes, genres, director, plot.
* So I implemented a get method for each of the new attributes.
* Therefore, overloaded the constructor with one explicit parameter that is title. This constructor gets the Json including all the information of the actor from api and initialize every instance variables with their get methods.

### Bugs

* a little difficulty about nested Jsons that was discussed in the "Json Parsing" section 

### Main Class

* I created a menu that the user can choose weather an actor or a movie to get their information.
* The information of actors and movies are displayed with related unicodes.
* When the information of a movie is displayed you can choose one of its actors and see him/her information too.

## Thanks to

* [StackOverFlow](https://stackoverflow.com)
* [W3Schools](https://www.w3schools.com/Java)