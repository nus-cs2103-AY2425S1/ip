package knight2103;

import knight2103.command.InvalidCommandException;
import knight2103.command.Parser;
import knight2103.command.Command;
import knight2103.files.InvalidFileContentsException;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;
import knight2103.tasks.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Knight2103 {
    private String welcomeMessage;
    private String errorMessage;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Knight2103(String filePath) {
        this.welcomeMessage = "Hello! I'm Knight2103\nWhat can I do for you?";
        this.errorMessage = "";
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            Pair<ArrayList<Task>, String> tasksWithErrorMsg = storage.load();
            this.tasks = new TaskList(tasksWithErrorMsg.getFirstItem());
            this.errorMessage = tasksWithErrorMsg.getSecondItem();
        } catch (FileNotFoundException e) { // file be loaded regardless
            this.errorMessage += "\nNote: the Storage File to initialise is not found. "
                    + "Will create new file instead\n. Existing storage file should be"
                    + "named as \"savedTaskList.txt\" in root directory";
            this.tasks = new TaskList();
        }
    }

    /**
     * Trigger a welcome message of the chatbot.
     */
    public String triggerWelcome() { // or directly return this.welcomeMessage?
        return ui.showWelcome(this.welcomeMessage + this.errorMessage);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String output;
            try {
                Command c = Parser.parse(input);
                output = c.execute(tasks, ui, storage);
                return output;
            } catch (InvalidCommandException e) { // what exceptions comes here?
                return e.getMessage();
            }

            // input to allow chatbot to exit the application?
            // if exception error, should say unsuccessful addition something like this
            // javaDoc comments; Formatting; Code quality.
            // adding assertions
            // welcome message - state all commands that can be used.
    }
}