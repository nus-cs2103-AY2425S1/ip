import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The {@code Snipe} class represents a task management system that interacts with the user via command-line input.
 * It supports commands for adding, deleting, marking, unmarking, and listing tasks.
 */
public class Snipe {
    private static final String HORIZONTAL_LINE = "_".repeat(60);

    /** The list of tasks managed by the application. */
    private static ArrayList<Task> list = new ArrayList<Task>();

    private Storage taskListStorage;
    private TaskList tasks;
    private Ui ui;

    public Snipe(String filePath) {
        this.ui = new Ui();
        this.taskListStorage = new Storage(filePath);
        try {
            this.tasks = new TaskList(taskListStorage.readTaskList());
        } catch (SnipeException | IOException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Initializes the chat, greeting the user and handling user input.
     */
    public void initChat() throws IOException, SnipeException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, taskListStorage);
                isExit = c.isExit();
            } catch (SnipeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * The main method that starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) throws IOException, SnipeException {
        Snipe snipe = new Snipe("src/main/txt/taskList.txt");
        snipe.initChat();
    }
}


