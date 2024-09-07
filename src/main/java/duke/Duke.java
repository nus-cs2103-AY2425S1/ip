package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * This is the main Object that runs the chatbot, making use of the various objects to interact with the user, process
 * the information and display information accordingly for the user to see.
 */
public class Duke {
    private static final String FILE_NAME = "data/tasks.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    //private static void addItem(String inp) {
    //    userInputs.add(new duke.Task(inp));
    //}

    /**
     * Constructor for the main Duke class which handles the overall functionality of task managing program.
     *
     * @param filePath Stores the file path to the link.txt file storing the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Parameterless function that handles the various objects for storing, manipulating and displaying the data on
     * the terminal.
     */
    public void run() {
        ui.greet();
        String userResponse = ui.out();
        while (!Objects.equals(userResponse, "bye")) {
            try {
                Parser ps = new Parser(userResponse);
                ps.process(tasks, ui);
                userResponse = ui.out();
            } catch (EmptyTaskException e) {
                System.out.println("The description of the task must contain some substance; it cannot be void.");
            } catch (EmptyCommandException e) {
                System.out.println("An empty command has been received.");
            } catch (InvalidInstructionException e) {
                System.out.println("The instruction provided is deemed invalid.");
            } catch (DateTimeParseException e) {
                System.out.println("Ah, esteemed inquirer, the date format you have provided is not correct."
                        + " It must be expressed as \"yyyy-mm-dd\".");
            }
        }
        try {
            storage.writeToFile(FILE_NAME, tasks);
        } catch (IOException e) {
            System.out.println("The endeavor to create the storage file has encountered an impediment."
                    + " I implore you to attempt this task once more in due course. Till then: ");
        }
        System.out.println("Farewell! Until we meet again.\n");

    }

    public static void main(String[] args) {
        new Duke(FILE_NAME).run();
    }
}
