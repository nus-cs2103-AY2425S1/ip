package luffy;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a chatbot that has various commands
 * for users to note down tasks and check them off
 */
public class Luffy {

    private static final String DEST_FILE = "./LuffyData/TaskData";

    // Variables
    private final LuffyUI luffyBot;
    private final Storage taskCache;
    private TaskList taskList;
    private boolean wantToExit = false;
    private final LuffyParser luffyParser;


    /**
     */
    public Luffy() {
        this.luffyBot = new LuffyUI();
        this.taskCache = new Storage(DEST_FILE);
        this.taskList = new TaskList();
        this.luffyParser = new LuffyParser(new Scanner(System.in));
    }


    public static void main(String[] args) {

        Luffy luffy = new Luffy();

        try {
            luffy.taskList = luffy.taskCache.loadFromFile();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        LuffyLauncher.main(args);

        // Luffy greets the user!
        luffy.luffyBot.showWelcomeMessage();

        while (!luffy.wantToExit) {

            String retrievedCommand = luffy.luffyBot.readNextCommand();
            Command userCommand = LuffyParser.parse(retrievedCommand);
            userCommand.executeCmd(luffy.luffyBot, luffy.taskCache, luffy.taskList);


            if (userCommand instanceof ExitCommand) {
                luffy.wantToExit = true;
            }
        }

        // Luffy says goodbye!
        luffy.luffyBot.showExitMessage();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Duke Heard: " + input;
        /*
        try {
            String luffyResponse = this.luffyParser.handleInputFromGui();
        } */
    }
}
