## to run
cd to java
javac duke/Duke.java
java duke/Duke

## to run test:
cd text-ui-test
bash runtest.sh


# Testing
## add to taks list
todo buy groceries
deadline submit report /by Sunday
event team meeting /from 2pm /to 4pm

## error handling
hello -> OOPS!!! I'm sorry, but I don't know what that means :-(
deadline submit report -> OOPS!!! The description of a deadline cannot be empty.
event /from 2pm /to 4pm -> OOPS!!! The description of a event cannot be empty.

## testing misc. stuff
list -> lists out all tasks
mark 2 -> marks task as done
unmark 3 -> unmarks task as undone

## datetime
deadline return book /by 2019-10-15
deadline buy groceries /by 2019-10-15
deadline cook lunch /by 2019-10-14

## showing tasks on date
show on 2019-10-15

## find 
find cook

# gradle
## quitting gradle
:q!

# BEFORE RUNNING ANYTHING IN GRADLE, YOU MUST BE IN ip
## running build for gradle
./gradlew build 

## running tests for gradle
./gradlew compileJava
./gradlew test

# jar
## create jar file
./gradlew clean build

This command will:

	•	Clean any previous builds.
	•	Compile your project.
	•	Package your application as a JAR file.

After running this command, the JAR file will be created in the build/libs/ directory, typically named something like your-project-name-version.jar.


## java -jar ip.jar

