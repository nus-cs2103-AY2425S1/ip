package joe;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import joe.command.Command;
import joe.task.TaskList;

/**
 * The {@code Joe} class is the main class for the application that runs the
 * task management system. It initializes the necessary components such as
 * {@code Storage}, {@code TaskList}, and {@code Ui} and controls the flow of
 * the program.
 */
public class Joe {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code Joe} object and initializes the UI, storage, and task list.
     * Loads existing tasks from the file specified by the file path.
     *
     * @param filePath The file path where the tasks are stored.
     */
    public Joe(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.loadData());
        } catch (JoeException e) {
            this.ui.printLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the application. This method runs the program,
     * continually reading user commands and executing them until the user
     * decides to exit.
     */
    private void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JoeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (JoeException e) {
            ui.printError(e.getMessage());
        } finally {
            System.setOut(originalOut);
            return baos.toString();
        }
    }

    /**
     * The entry point of the application. Initializes the {@code Joe} object
     * and starts the application by calling the {@code run} method.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Joe("./data/joe.txt").run();
    }
}
