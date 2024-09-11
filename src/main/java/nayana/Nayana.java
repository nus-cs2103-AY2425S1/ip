package nayana;
import nayana.command.Command;

/**
 * Nayana is a class that demonstrates a simple console-based interaction.
 * It manages user interaction, command processing, and task management.
 */
public class Nayana {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    boolean isExit = false;

    /**
     * Constructs a Nayana instance with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Nayana(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NayanaException e) {
            System.out.println("no correct file");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application, processing user commands until exit is requested.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NayanaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String filePath = "data/nayana.txt";
        new Nayana(filePath).run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Nayana heard: " + input;
    }

    public Ui getUi() {
        return this.ui;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public TaskList getTaskList() {
        return this.tasks;
    }

    public boolean parseCommand(String command){
        try {
            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return isExit;
        } catch (NayanaException e) {
            ui.showError(e.getMessage());
        }
        return isExit;
    }

}
