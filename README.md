# Kat project template

This is a greenfield Java project based on [this project template](https://github.com/nus-cs2103-AY2425S1/ip). It's named after _Kat_ now (Before, it was named after the Java mascot _Duke_). Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/chatbot/Main.java` file, right-click it, and choose `Run Main.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    _         _   
   | | __ ___| |_
   | |/ / _  | __|
   |   < (_| | |_
   |_|\_\____|\__|
   ```

## Use of AI

1. The JavaDoc comments were partially done with Claude Sonnet 3.5.<br>
   Sample self-written JavaDoc comments and JavaDoc guidelines from the [Java coding standard](https://se-education.org/guides/conventions/java/index.html) were provided to the AI to generate more comments.
   Comments created by the AI are vetted and edited.
1. Git commit message were partially generated with Calude Sonnet 3.5.
   Sample self-written Git commit message body and [Git conventions](https://se-education.org/guides/conventions/git.html) were provided to the AI to refine and generate new commit message bodies. Messages created by the AI are vetted and edited.
1. [user.png](src/main/resources/images/user.png) and [chatbot.png](src/main/resources/images/chatbot.png) were both generated using DALL-E.
