package bestie;


import bestie.command.Command;
import bestie.command.ExitCommand;


/**
 * Creates an instance of the Bestie chatbot.
 */
public class Bestie {
    // load the bestie.txt file in the same directory
    //private static final String FILE_PATH = "bestie.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of the Bestie chatbot, initialising the ui, storage and tasklist.
     *
     * @param filePath path of the bestie.txt file where tasks are stored.
     */
    public Bestie(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasksFromFile());
    }

    /**
     * Provides welcome message that Bestie displays when application is first opened.
     *
     * @return Welcome message when bestie first starts.
     */
    public String welcomeMessage() {
        return this.ui.greetUser();
    }

    /**
     * Bestie's response to what the user has entered as a command.
     *
     * @param userInput Command that user has entered
     * @return Response to command to be displayed by bestie.
     */
    public String getResponse(String userInput) {
        Command instruction = Parser.parse(userInput);
        return instruction.execute(tasks, ui, storage);
    }

}
