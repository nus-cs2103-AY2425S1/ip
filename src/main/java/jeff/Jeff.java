package jeff;

import jeff.command.Command;
import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

public class Jeff {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jeff(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        // Load the task list from the task list text file
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (JeffException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        // Print out the greetings message
        this.ui.printWelcome();
        boolean isExit = false;

        // Continue asking for user input until the user says "bye"
        while (!isExit) {
            try {
                // Read the user input
                String fullCommand = this.ui.readCommand();

                // Parse the user input into commands
                Command c = Parser.parse(fullCommand);

                // Execute the command
                c.execute(this.tasks, this.ui, this.storage);

                // Check if the program should exit
                isExit = c.isExit();

            } catch (JeffException e) {
                this.ui.showError(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Jeff("data/tasks.txt").run();
    }
}
