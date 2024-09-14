package luffy;

import java.io.FileNotFoundException;
import java.util.Scanner;

import luffy.command.Command;
import luffy.command.ExitCommand;
import luffy.gui.LuffyLauncher;
import luffy.parser.LuffyParser;
import luffy.storage.Storage;
import luffy.task.TaskList;
import luffy.ui.LuffyUI;


/**
 * Represents a chatbot that has various commands
 * for users to note down tasks and check them off
 */
public class Luffy {

    private static final String DEST_FILE = "./LuffyData/TaskData";
    private final LuffyUI luffyBot;
    private final Storage taskCache;
    private final LuffyParser luffyParser;
    private TaskList taskList;
    private boolean isRunning;
    /**
     * Constructs a Luffy Bot object
     */
    public Luffy() {
        this.isRunning = true;
        this.luffyBot = new LuffyUI();
        this.taskCache = new Storage(DEST_FILE);
        this.luffyParser = new LuffyParser(new Scanner(System.in));
        this.taskList = null;
    }


    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--cli")) {
            Luffy luffy = new Luffy();
            luffy.startLuffy();
        } else {
            LuffyLauncher.main(args);
        }
    }
    /**
     * Command to start Luffy bot
     */
    private void startLuffy() {
        try {
            taskList = taskCache.loadFromFile();
            taskList = taskCache.loadFromFile();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        while (isRunning) {
            String userInput = luffyBot.readNextCommand();
            Command userCommand = LuffyParser.parse(userInput);
            userCommand.executeCmd(luffyBot, taskCache, taskList);
            if (userCommand instanceof ExitCommand) {
                isRunning = false;
            }
        }
    }
    /**
     * This method generates a response for the user's chat message.
     * @param input user's input that will be parsed
     */
    public String getResponse(String input) {
        String response = luffyParser.handleInputFromGui(input);
        assert !response.isEmpty();
        if (response.equals("Goodbye! See you soon!")) {
            isRunning = false;
        }
        return response;
    }
}
