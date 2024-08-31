package luffy;

import java.io.FileNotFoundException;

/**
 * Represents a chatbot that has various commands
 * for users to note down tasks and check them off
 */
public class Luffy {

    private static final String DEST_FILE = "./LuffyData/TaskData";

    public static void main(String[] args) {

        // Variables
        LuffyUI luffyBot = new LuffyUI();
        Storage taskCache = new Storage(DEST_FILE);
        TaskList taskList = new TaskList();
        boolean wantToExit = false;

        try {
            taskList = taskCache.loadFromFile();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Luffy greets the user!
        luffyBot.showWelcomeMessage();

        while (!wantToExit) {

            String retrievedCommand = luffyBot.readNextCommand();
            Command userCommand = LuffyParser.parse(retrievedCommand);
            userCommand.executeCmd(luffyBot, taskCache, taskList);


            if (userCommand instanceof ExitCommand) {
                wantToExit = true;
            }
        }

        // Luffy says goodbye!
        luffyBot.showExitMessage();
    }
}
