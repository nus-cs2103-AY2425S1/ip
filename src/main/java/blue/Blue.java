package blue;

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
    }

    /**
     * Runs the Blue application by greeting the user, processing user input, and saying farewell.
     */
    public void run() {
        UI.greet();
        parser.parse(tasklist);
        UI.farewell();
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
