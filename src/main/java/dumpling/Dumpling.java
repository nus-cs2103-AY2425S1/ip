package dumpling;

import dumpling.command.Command;
import dumpling.task.TaskList;
import dumpling.ui.Ui;

/**
 * Dumpling chatbot class
 */
public class Dumpling {

    private Storage storage;
    private TaskList todoList;
    private Ui ui;
    private String saveDataPath;

    /**
     * Default constructor. Uses default datapath.
     */
    public Dumpling() {
        // create default data folder
        this("data/dumplingData.txt");
    }

    /**
     * Constructor that takes in a filepath as where the data is stored
     *
     * @param filePath Filepath of data file
     */
    public Dumpling(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.todoList = new TaskList(storage.load());
        } catch (DumplingException e) {
            ui.showError(e);
            this.todoList = new TaskList();
        }
    }

    /**
     * Obtain response from Dumpling when given the user input. Used for GUI version.
     *
     * @param input User input string
     * @return Message from Dumpling, which is the output from Dumpling upon completing the task.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        return command.executeAndReturnLog(this.todoList, this.storage);
    }

    /**
     * Runs the chatbot in the CLI version.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        String userInput;
        while (!isExit) {
            try {
                userInput = this.ui.readCommand();
                this.ui.showLine();
                Command command = Parser.parse(userInput);
                command.execute(this.todoList, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DumplingException e) {
                this.ui.showError(e);
            } finally {
                if (!isExit) {
                    this.ui.showLine();
                }
            }
        }
    }
}
