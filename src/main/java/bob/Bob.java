package bob;

/**
 * The {@code Bob} class serves as the main entry point for the Bob chat application.
 * It handles the initialization of the application's components and the main program loop where it continuously reads
 * and executes user commands.
 */
public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code Bob} object by initializing its {@code Storage}, {@code TaskList}, and {@code Ui} components.
     * The {@code Storage} component is initialized with the specified file path for loading and saving tasks.
     *
     * @param filePath the file path where the tasks are stored.
     */
    public Bob(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = this.storage.loadFile();
        } catch (IllegalInputException e) {
            //ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the Bob application. This method displays the welcome message,
     * then enters the main loop where it reads, parses, and
     * executes user commands, and handles any errors.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                String response = c.execute(tasks, ui, storage);
                System.out.println(response);
                isExit = c.isExit();
            } catch (IllegalInputException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return response;
        } catch (IllegalInputException e) {
            return e.getMessage();
        }
    }

    /**
     * The main method to start the Bob application.
     * It initializes a new {@code Bob} instance with a predefined file path and starts the application.
     *
     */
    public static void main(String[] args) {
        new Bob("data/savedTasks.txt").run();
    }
}
