package knight2103;

import knight2103.files.Storage;
import knight2103.tasks.TaskList;
import knight2103.tasks.Task;
import knight2103.command.Parser;
import knight2103.command.Command;
import knight2103.command.InvalidCommandException;

import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * Builds the bot's characteristics.
 */
public class Knight2103 {
    private String welcomeMessage;
    private String errorMessage;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs the bot's instance object which helps to manage the list of task.
     * The bot contains the filepath to the text file in which the list of tasks will be stored.
     *
     * @param filePath The relative location of the text file which contains the list of tasks.
     */
    public Knight2103(String filePath) {
        this.welcomeMessage = "Hello! I'm Knight2103\nWhat can I do for you?";
        this.errorMessage = "";
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            Pair<ArrayList<Task>, String> tasksWithErrorMsg = storage.loadFileContents();
            this.tasks = new TaskList(tasksWithErrorMsg.getFirstItem());
            this.errorMessage = tasksWithErrorMsg.getSecondItem();
        } catch (FileNotFoundException e) { // file be loaded regardless, exception from Storage
            this.errorMessage += "\nNote: the Storage File to initialise is not found. "
                    + "Will create new file instead\n. Existing storage file should be"
                    + "named as \"savedTaskList.txt\" in root directory";
            this.tasks = new TaskList();
        }
    }

    /**
     * Triggers a welcome message of the chatbot.
     *
     * @return Bot's welcome message which will be shown every time the bot application first starts.
     */
    public String triggerWelcome() { // or directly return this.welcomeMessage?
        return ui.showWelcome(this.welcomeMessage + this.errorMessage);
    }

    /**
     * Generates a response for the user's chat message input.
     *
     * @return Bot's response.
     * @param input User's input.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage);
            return output;
        } catch (InvalidCommandException e) { // Exceptions from commands
            return "Failed to execute Command:\n" + e.getMessage();
        }

        // javaDoc comments; Formatting; Code quality.
        // welcome message - state all commands that can be used.
        // adding assertions

    }
}