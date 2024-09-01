package bob;

/**
 * Represents the Bob chatbot application. This class initializes the chatbot's components,
 * handles user input, and interacts with storage and UI components to process tasks and generate responses.
 */
public class Bob {
    /** The name of the chatbot */
    private static final String NAME = "Bob";

    /** UI component for displaying messages and handling user interactions */
    private Ui ui;

    /** Storage component for saving and loading tasks */
    private Storage storage;

    /** Task list component for managing tasks */
    private TaskList taskList;

    /**
     * Constructs a Bob instance and initializes its components.
     * Sets up the UI, storage, and task list, and loads any existing tasks from storage.
     */
    public Bob() {
        ui = new Ui();
        storage = new Storage("./data/bob.txt");
        taskList = new TaskList();

        try {
            taskList.setTasks(storage.load());
        } catch (ChatBotException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Returns the UI component associated with this Bob instance.
     *
     * @return The UI component.
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Processes the user's input and generates a response from the chatbot.
     * Handles any exceptions that occur during processing and saves the updated task list to storage.
     *
     * @param input The user's input as a string.
     * @return The chatbot's response as a string. In case of an error, an error message is returned.
     */
    public String getResponse(String input) {
        String response;
        try {
            response = Parser.handleInput(input, taskList, ui);
            storage.save(taskList.getTasks());
        } catch (ChatBotException e) {
            response = "Error: " + e.getMessage();
        }
        return response;
    }

    /**
     * Returns the chatbot's welcome message.
     * This message is displayed when the application starts.
     *
     * @return The welcome message as a string.
     */
    public String getWelcomeMessage() {
        return String.format("Hello! I'm %s!\nWhat can I do for you?", NAME);
    }

    /**
     * Returns the chatbot's goodbye message.
     * This message is displayed when the user exits the application.
     *
     * @return The goodbye message as a string.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }
}
