package utilities;

import java.net.URL;

import commands.Command;
import java.io.File;

public class TaskFairy {

    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public TaskFairy() {
        File file = new File(getFilePath());
        this.storage = new Storage(file.getAbsolutePath());
        storage.loadFromFile();
        this.tasks = (TaskList) storage.getTasks();
        this.ui = new Ui();
        this.parser = new Parser(ui, tasks, storage);
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }

    /**
     * Retrieves the file path where tasks are stored for loading and saving.
     *
     * @return The file path to the tasks file.
     */
    public static String getFilePath() {
        File file = new File("./data/utilities.TaskFairy.txt");
        return file.getAbsolutePath();
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
