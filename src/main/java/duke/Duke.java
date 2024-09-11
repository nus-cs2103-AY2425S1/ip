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
    private static final String GOODBYE_MESSAGE = "Farewell! Until we meet again.\n";
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
                System.out.println(e.getMessage());
            } catch (EmptyCommandException e) {
                System.out.println(e.getMessage());
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Ah, esteemed inquirer, the date format you have provided is not correct."
                        + " It must be expressed as \"yyyy-mm-dd\".");
            } catch (TaskListOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            storage.writeToFile(FILE_NAME, tasks);
        } catch (IOException e) {
            System.out.println("The endeavor to create the storage file has encountered an impediment."
                    + " I implore you to attempt this task once more in due course. Till then: ");
        }
        System.out.println(GOODBYE_MESSAGE);

    }

    /**
     * Takes in the user's response as a String and returns the information to be displayed back to the User.
     * @param userResponse The string holding the user's response.
     * @return A response to the user's response.
     */
    public String getResponse(String userResponse) {
        ui.greet();
        if (!Objects.equals(userResponse, "bye")) {
            try {
                Parser ps = new Parser(userResponse);
                return ps.stringProcess(tasks, ui);
            } catch (EmptyTaskException e) {
                return e.getMessage();
            } catch (EmptyCommandException e) {
                return e.getMessage();
            } catch (InvalidCommandException e) {
                return e.getMessage();
            } catch (DateTimeParseException e) {
                return "Ah, esteemed inquirer, the date format you have provided is not correct."
                        + " It must be expressed as \"yyyy-mm-dd\".";
            } catch (TaskListOutOfBoundsException e) {
                return e.getMessage();
            }
        }
        try {
            storage.writeToFile(FILE_NAME, tasks);
        } catch (IOException e) {
            return "The endeavor to create the storage file has encountered an impediment."
                    + " I implore you to attempt this task once more in due course. Till then: \n" + GOODBYE_MESSAGE;
        }
        return GOODBYE_MESSAGE;
    }

    public static void main(String[] args) {
        new Duke(FILE_NAME).run();
    }
}
