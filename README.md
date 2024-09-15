# Appleaster project template

This is a project Appleasterlate for a greenfield Java project. It's named after the Java mascot _Appleaster_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Appleaster.java` file, right-click it, and choose `Run Appleaster.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
      _____                .__                          __                
   /  _  \ ______ ______ |  |   ____ _____    _______/  |_  ___________ 
   /  /_\  \\____ \\____ \|  | _/ __ \\__  \  /  ___/\   __\/ __ \_  __ \
   /    |    \  |_> >  |_> >  |_\  ___/ / __ \_\___ \  |  | \  ___/|  | \/
   \____|__  /   __/|   __/|____/\___  >____  /____  > |__|  \___  >__|   
         \/|__|   |__|             \/     \/     \/            \/          
   ```
