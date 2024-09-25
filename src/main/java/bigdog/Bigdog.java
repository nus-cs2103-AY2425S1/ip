package bigdog;

import java.time.format.DateTimeParseException;

/**
 * The {@code Bigdog} class represents the main application that manages tasks.
 * It handles user input, processes commands, interacts with storage, and manages the task list.
 */
public class Bigdog {

    /** Storage for tasks into external file */
    private final Storage storage;
    /** TaskList to manage tasks */
    private final TaskList tasks;
    /** Ui to handle interaction with users */
    private final Ui ui;

    /**
     * Constructs a Bigdog object.
     * Initializes the storage, task list, and UI components using the specified file path.
     *
     * @param file the file path where tasks are stored and loaded from.
     */
    public Bigdog(String file) {
        this.storage = new Storage(file);
        this.tasks = new TaskList(this.storage.load());
        this.ui = new Ui();
    }

    /**
     * Generates a response for the user's chat message.
     * This method reads user input, parses it, and executes the corresponding commands.
     * It handles exceptions that may arise during the execution of commands and ensures that tasks
     * are saved after each iteration of the loop.
     *
     * @param input User input in String format
     */
    public String getResponse(String input) {
        String botReply;
        try {
            String[] commands = Parser.parse(input);
            assert commands.length == 2 : "Insufficient arguments for the command.";
            botReply = processCommand(commands[0], commands[1]);
        } catch (BigdogException
                 | DateTimeParseException
                 | AssertionError e) {
            return e.getMessage();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Invalid index, please return a number within the list index!";
        }
        storage.save(this.tasks.get());
        return botReply;
    }

    /**
     * Processes a command and returns the appropriate response based on the command and its description.
     * This method takes a command and its associated description, executes the corresponding task,
     * and returns a response string. It supports various commands like marking tasks, adding tasks,
     * deleting tasks, etc.
     * <p>
     * The following commands are supported:
     * <ul>
     *     <li>"bye" - Ends the session with a farewell message.</li>
     *     <li>"list" - Displays all tasks.</li>
     *     <li>"mark" - Marks a task as completed.</li>
     *     <li>"unmark" - Unmarks a completed task.</li>
     *     <li>"delete" - Deletes a task from the list.</li>
     *     <li>"todo" - Adds a new Todo task.</li>
     *     <li>"deadline" - Adds a new Deadline task.</li>
     *     <li>"event" - Adds a new Event task.</li>
     *     <li>"find" - Finds tasks containing a specific keyword.</li>
     *     <li>"view" - View the schedule for a day (i.e. what tasks are due/on that day)</li>
     * </ul>
     *
     * @param command The command to execute (e.g., "mark", "delete", "todo").
     * @param description Additional details for the command (e.g., task index or task description).
     * @return A string containing the response message after executing the command.
     * @throws NumberFormatException if the description cannot be parsed as an integer (e.g., when marking a task).
     * @throws IndexOutOfBoundsException if an invalid task index is provided.
     * @throws BigdogException if an error specific to the Bigdog application occurs.
     */
    private String processCommand(String command, String description) throws RuntimeException {
        String reply = "";
        switch (command) {
        case "bye":
            ui.bye();
            break;
        case "list":
            reply = this.tasks.toString();
            break;
        case "mark":
            reply = this.tasks.mark(Integer.parseInt(description));
            break;
        case "unmark":
            reply = this.tasks.unmark(Integer.parseInt(description));
            break;
        case "delete":
            reply = this.tasks.delete(Integer.parseInt(description));
            break;
        case "todo":
            reply = this.tasks.add(Todo.of(description));
            break;
        case "deadline":
            reply = this.tasks.add(Deadline.of(description));
            break;
        case "event":
            reply = this.tasks.add(Event.of(description));
            break;
        case "find":
            reply = this.tasks.find(description);
            break;
        case "view":
            reply = this.tasks.viewSchedule(description);
            break;
        default:
            reply = "Unknown command";
        };
        return reply;
    }

    /**
     * The main entry point of the Bigdog application.
     * This method is required for the JVM to run the Bigdog application.
     * However, in this case, it does not perform any specific actions since it is not
     * needed for typical JavaFX applications, which use the {@code Application.launch} method.
     *
     * @param args Command-line arguments passed to the program (not used).
     */
    public static void main(String[] args) {}

}
