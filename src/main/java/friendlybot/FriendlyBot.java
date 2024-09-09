package friendlybot;

import java.util.Scanner;

import friendlybot.command.Command;
import friendlybot.task.TaskList;

/**
 * FriendlyBot is a simple task management bot that can list, mark, and unmark tasks.
 */
public class FriendlyBot {
    private static final String TASK_LIST_FILE_PATH = "./data/task_list.txt";
    private static final String TASK_LIST_FILE_PATH_WITHOUT_FILE = "./data";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for FriendlyBot.
     *
     * @param filePath Path of the TaskList file stored locally.
     * @param filePathWithoutFile Path of the TaskList file stored locally, excluding the file.
     *                            (Used to create relevant directories if they do not exist.)
     */
    public FriendlyBot(String filePath, String filePathWithoutFile) {
        assert !filePath.isEmpty() : "filePath should not be empty!";
        assert !filePathWithoutFile.isEmpty() : "filePathWithoutFile should not be empty!";
        this.ui = new Ui();
        this.storage = new Storage(filePath, filePathWithoutFile);
        this.tasks = new TaskList(this.storage.load());
    }

    /**
     * The main method to run FriendlyBot.
     *
     * @param args friendlybot.command.Command line arguments.
     */
    public static void main(String[] args) {
        FriendlyBot friendlyBot = new FriendlyBot(TASK_LIST_FILE_PATH, TASK_LIST_FILE_PATH_WITHOUT_FILE);
        // Initialise variables
        Scanner reader = new Scanner(System.in);
        // Start of Chat Bot
        friendlyBot.ui.showWelcome();
        String response = "";
        boolean isExit = false;
        // Commands
        while (!isExit) {
            response = reader.nextLine();
            friendlyBot.ui.printHorizontalBar();
            Command cmd = Parser.parse(response);
            cmd.execute(friendlyBot.tasks, friendlyBot.ui, friendlyBot.storage);
            isExit = cmd.isExit();
            friendlyBot.ui.printHorizontalBar();
        }
        reader.close();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command cmd = Parser.parse(input);
        return cmd.execute(this.tasks, this.ui, this.storage);
    }
}
