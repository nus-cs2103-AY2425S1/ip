package nayana;
import nayana.command.Command;

/**
 * Nayana is a class that demonstrates a simple console-based interaction.
 * It manages user interaction, command processing, and task management.
 */
public class Nayana {

    private Storage storage;
    private TaskList tasks;
    private Ui ui; // Handles user interface operations.

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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Nayana heard: " + input;
    }

    public Ui getUi() {
        return this.ui;
    }


    public void parseCommand(String command){
        try {
            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage); // Executes the command, modifying tasks and UI as necessary.
            isExit = c.isExit();
        } catch (NayanaException e) {
            ui.showError(e.getMessage());
        }
    }

}
