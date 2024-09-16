# ned.Ned project template

This is a project template for a greenfield Java project. It's named after the Game Of Thrones character [Eddard Stark](https://gameofthrones.fandom.com/wiki/Eddard_Stark). Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run ned.Ned.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
     ____  _____              __  
    |_   \|_   _|            |  ] 
      |   \ | |  .---.   .--.| |  
      | |\ \| | / /__\\/ /'`\' |  
     _| |_\   |_| \__.,| \__/  |  
    |_____|\____|'.__.' '.__.;__]
   ```
# Credits
This project could not have been possible without the following works:
- (A Song of Ice and Fire for the character and theme)[https://en.wikipedia.org/wiki/A_Song_of_Ice_and_Fire]
- (kaixin-hc's ip bot for the theme)[https://github.com/kaixin-hc/ip]
- (Original ip bot code)[https://github.com/nus-cs2103-AY2425S1/ip/tree/master]
- (House Stark background)[https://i.pinimg.com/736x/0b/4d/b9/0b4db943493a88715fea9a09ad5fe1e9.jpg]
- (Ned Stark display photo)[https://en.wikipedia.org/wiki/Ned_Stark]
