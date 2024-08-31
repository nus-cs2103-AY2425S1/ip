package michaelscott;

import michaelscott.command.Command;
import michaelscott.command.CommandParser;
import michaelscott.task.TaskList;
import michaelscott.utils.MichaelScottException;
import michaelscott.utils.Storage;
import michaelscott.utils.Ui;

/**
 * This class represents the entire chatbot named MichaelScott.
 * It manages the main execution loop, user interface, command parsing,
 * task storage, and error handling.
 */
public class MichaelScott {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final CommandParser parser;

    /**
     * Constructs a new MichaelScott chatbot instance.
     * Initializes the UI, command parser, task list, and storage components.
     */
    public MichaelScott() {
        ui = new Ui();
        parser = new CommandParser();
        tasks = new TaskList();
        storage = new Storage(tasks);
    }

    /**
     * Runs the main execution loop of the chatbot.
     * Handles user input, command execution, and output display.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command cmd = parser.parse(fullCommand); //
                String response = cmd.execute(tasks); //

                ui.showResponse(response);
                storage.saveTasks(tasks.getTasks()); //
                isRunning = !cmd.isExit();
            } catch (MichaelScottException e) {
                Ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new MichaelScott().run();
    }
}
