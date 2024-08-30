package jeff;

import jeff.command.Command;
import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * Represents a chatbot.
 */
public class Jeff {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Jeff Class.
     * Uses the given file path input to get the task list from the text file.
     * Also initialises the user interface.
     *
     * @param filePath File path of the task list text file.
     */
    public Jeff(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (JeffException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     * The chatbot terminates when the user says "bye".
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
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
