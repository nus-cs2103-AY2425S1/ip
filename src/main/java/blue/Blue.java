package blue;

import java.util.Scanner;
import blue.task.TaskList;

/**
 * The main class for the Blue application, which manages tasks using a {@link TaskList} and a {@link Parser}.
 * The application starts by greeting the user, then parsing user input to manage tasks, and finally saying farewell.
 */
public class Blue {
    /** The task list managed by the application. */
    private TaskList tasklist;

    /** The parser that handles user input. */
    private Parser parser;

    /**
     * Constructs a Blue application, initializing the task list and parser.
     */
    public Blue() {
        this.tasklist = new TaskList();
        this.parser = new Parser();

        // Use assertions to check that the tasklist and parser are initialized properly.
        assert tasklist != null : "TaskList should be initialized.";
        assert parser != null : "Parser should be initialized.";
    }

    /**
     * Runs the Blue application by greeting the user, processing user input, and saying farewell.
     */
    public void run() {
        UI.greet();
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            String response = parser.parse(input, tasklist);
            System.out.println(response);
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
        }
        UI.farewell();
    }

    public String getResponse(String input) {
        // Assert that input is not null to avoid potential issues.
        assert input != null : "Input cannot be null.";
        return parser.parse(input, tasklist);
    }

    /**
     * The main method that starts the Blue application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Blue().run();
    }
}
