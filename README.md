# Wikipedia Json Parser

## Project Summary

### Fetch Wikipedia

The purpose of fetch wikipedia is to use a URLConnection to get the raw data of a wikipedia website based on a string subject
This raw data is turned into a JSON. The public fetchwikipedia function allows for this, where the private methods handle error exceptions and other internal features

### Json Convert

this class has two public functions, one being a getter and the other called jsonconvert with will take the json from fetch wikipedia and return one table of three fields, id, title, and revisions
Internally, this class uses Gson to convert the json into a string 

```java

        Set<Map.Entry<String, JsonElement>> entrySet = pages.getAsJsonObject().entrySet();

        Optional<Map.Entry<String, JsonElement>> first = entrySet.stream().findFirst();

```

As seen here, theyre stored as maps with a key string and json element as the value, first being the first entry of this set of data


### Main

Main uses a scanner to allow the user to input a string to first select a page they would like to go to.
Main also uses three private functions to make a while loop continuously go, unless input is 0 to exit the program.
GetAction and GetPageAction should both return ints to determine what data we should get/ to end the program.
These classes use exceptions to check for ints and the proper input exceptions, like 1 or 0 for exit/ get page data
GetJsonData is the class inside main that will get our data based on the scanner subject




## Authors

### Lucas Workman, Mason Parker

## Build

To build the project, there are two options

### Git Clone
1) Open a new project in IDE of your choice
2) run https://github.com/LucasW962/First-lucas.workman-mason.parker in the CLI
3) execute the program

### ZIP download
1) Click the code button on the github master branch
2) Click download branch
3) Extract the folder
4) Run the main java file
