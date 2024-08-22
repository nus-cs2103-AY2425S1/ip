package wolfie;
import wolfie.command.Command;
import wolfie.exception.WolfieException;
import wolfie.util.Parser;
import wolfie.util.Storage;
import wolfie.util.Ui;
import wolfie.task.TaskList;

import java.io.IOException;

public class Wolfie {
    private final Storage storage; // Add this line
    private TaskList tasks;
    private Ui ui;

    public Wolfie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath); // If the file does not exist, it will be created
        try {
            tasks = new TaskList(storage.load()); // Load existing tasks
        } catch (IOException e) {
            ui.showLoadingError(); // Show error message
            tasks = new TaskList(); // Start with an empty list
        }
    }

    public void run() {
        ui.showWelcome(); // Show welcome message
        boolean isExit = false; // Initialize exit status to false
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(); // Read the command
                ui.showLine(); // Show the line divider
                Command c = Parser.parse(fullCommand); // Parse the command
                c.execute(tasks, ui, storage); // Execute the command
                isExit = c.isExit();
            } catch (WolfieException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Wolfie("data/tasks.txt").run();
    }
}