package utilities;

import java.net.URL;

import commands.Command;

/**
 * The TaskFairy chatbot main class that initiates the program, loads tasks, and handles user input.
 */
public class TaskFairy {

    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public TaskFairy() {
        this.storage = new Storage(getFileURL());
        storage.loadFromFile();
        this.tasks = (TaskList) storage.getTasks();
        this.ui = new Ui();
        this.parser = new Parser(ui, tasks, storage);
    }
    public static void main(String[] args) {
        System.out.println("Hello!");
    }


    /**
     * Retrieves the file URL where tasks are stored for loading and saving.
     *
     * @return The path to the tasks file.
     */
    public static String getFileURL() {
        final URL fileURL = TaskFairy.class.getProtectionDomain().getCodeSource().getLocation();
        String path = fileURL.getPath();
        String rootPath = path.substring(0, path.indexOf("ip") + 3) + "/data/utilities.TaskFairy.txt";
        return rootPath;
    }

    public String greetUser() {
        return "Hey girlie! Use me to organise your tasks \u2728 Just tell " +
                "me what to do and I'll add it to your list! You can also " +
                "type 'help' if you're not sure what to enter \u2728";
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command command = parser.parseInput(input);
        if (command.isTypeGoodbye()) {
            return "Nice talk, girl. Now, get back to that hustle\u2728!";
        }
        return command.getResponse();
    }
}
