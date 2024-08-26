package friendlybot;

import friendlybot.task.TaskList;

import friendlybot.command.Command;

import java.util.Scanner;

/**
 * FriendlyBot is a simple task management bot that can list, mark, and unmark tasks.
 */
public class FriendlyBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String taskListFilePath = "./data/task_list.txt";
    private static final String taskListFilePathWithoutFile = "./data";

    public FriendlyBot(String filePath, String filePathWithoutFile) {
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
        FriendlyBot friendlyBot = new FriendlyBot(taskListFilePath, taskListFilePathWithoutFile);
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
}
