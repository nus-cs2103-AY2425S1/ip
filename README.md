# Quack Setup Guide
---
<div style="display: flex; align-items: flex-start;">
  <image src="src\main\resources\images\duck.png" width="450" height="400">
  <p style="margin-left: 10px; padding-top: 100px;">Hello nice to meet you I am your personal assistant</p>
</div>

## What is Quack?

   Quack is a user friendly assistant which helps you keep track of pesky tasks and ensures you don't miss out on anything important!

# Setting up Quack
---

If you are using the `.jar` to run the program please skip to the [[#Quick Start by Running the JAR file|quick start]] portion of the guide.

## InteliJ
   Prerequisites: JDK 17, update Intellij to the most recent version.

   1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
   2. Open the project into Intellij as follows:
      - Click `Open`.
      - Select the project directory, and click `OK`.
      - If there are any further prompts, accept the defaults.
   3. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
      In the same dialog, set the **Project language level** field to the `SDK default` option.
   4. Ensure Gradle is installed
   5. After that, run gradle either by CLI `gradle run` or using the inbuilt Gradle GUI in IntelliJ. Running `gradle build` is optional but recommended

## VSCode
   For VSCode :
   1. Open VSCode
   2. Open the project into VSCode as follows:
      - Click `File` then `Open Folder`.
      - Select the project directory, and click `OK`.
      - If there are any further prompts, accept the defaults.
   3. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   4. Ensure Gradle is installed
   5. After that, run gradle either by CLI `gradle run` or using the inbuilt Gradle GUI on the side pannel to the left/right. Running `gradle build` is optional but recommended

## Quick Start by Running the JAR file

   1) Ensure you have JDK-17.0.12 or equivalent is installed locally in your Computer
   2) Download the latest `.jar` file from here
   3) Copy the file to the folder you want to use as the home folder for Quack.
   4) Open a command terminal, `cd` into the folder you put the `.jar` file in, and use the following command`java -jar quack.jar` to run the application.

# Features
---
Here is a list of features:

* Adding a task
   - ToDo
   - Event
   - Deadline
* Listing all possible commands (Help me!)
* Listing tasks
* Marking & unmarking tasks
* Deleting a task
* Tagging a Task
* Finding Tasks

# References
---

## Website to generate the ASCII Logo
The website to generate the ASCII Logo can be found [here]((https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjNkMjnx_6HAxVN6zgGHSk9NLYQFnoECBwQAQ&url=https%3A%2F%2Fpatorjk.com%2Fsoftware%2Ftaag%2F&usg=AOvVaw1rmNDfu2i-RQ4_TslxEwcR&opi=89978449)).

## Profile Pictures

### Duck Icon

For the duck icon it was retrieved [here](https://www.vecteezy.com/vector-art/25668392-simple-and-adorable-flat-colored-white-duck-illustration).

### Person Icon

For the person icon it was retrieved [here](https://www.flaticon.com/free-icon/man_4140061).