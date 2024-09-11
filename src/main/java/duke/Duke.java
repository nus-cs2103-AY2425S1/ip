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
    public Duke() {
        this(FILE_NAME);
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
            } catch (InvalidCommandException e) {
                System.out.println("The instruction provided is deemed invalid.");
            } catch (DateTimeParseException e) {
                System.out.println("Ah, esteemed inquirer, the date format you have provided is not correct."
                        + " It must be expressed as \"yyyy-mm-dd\".");
            } catch (TaskListOutOfBoundsException e) {
                System.out.println("Please select an item number from within the current list for deletion.");
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

    /**
     * Takes in the user's response as a String and returns the information to be displayed back to the User.
     * @param userResponse The string holding the user's response.
     * @return A response to the user's response.
     */
    public String getResponse(String userResponse) {
        ui.greet();
        //String userResponse = ui.out();
        if (!Objects.equals(userResponse, "bye")) {
            try {
                Parser ps = new Parser(userResponse);
                return ps.stringProcess(tasks, ui);
                //userResponse = ui.out();
                //add return statement
            } catch (EmptyTaskException e) {
                return "The description of the task must contain some substance; it cannot be void.";
            } catch (EmptyCommandException e) {
                return "An empty command has been received.";
            } catch (InvalidCommandException e) {
                return "The instruction provided is deemed invalid.";
            } catch (DateTimeParseException e) {
                return "Ah, esteemed inquirer, the date format you have provided is not correct."
                        + " It must be expressed as \"yyyy-mm-dd\".";
            } catch (TaskListOutOfBoundsException e) {
                System.out.println("Please select an item number from within the current list for deletion.");
            }
        } else {
            try {
                storage.writeToFile(FILE_NAME, tasks);
            } catch (IOException e) {
                return "The endeavor to create the storage file has encountered an impediment."
                        + " I implore you to attempt this task once more in due course. Till then: ";
            }
            return "Farewell! Until we meet again.\n";
        }
        assert(true);
        return "";
    }

    public static void main(String[] args) {
        new Duke(FILE_NAME).run();
    }
}
