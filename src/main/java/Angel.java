import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Angel chatbot application.
 * Handles user commands, task management, and data persistence.
 */
public class Angel {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final String FILE_PATH = "./data/Angel.txt";
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an Angel instance and initializes the task list.
     * Loads tasks from the storage file if it exists.
     */
    public Angel() {
        taskList = new TaskList(FILE_PATH);
        ui = new Ui();
    }

    /**
     * Runs the chatbot application.
     * Continuously reads user input, processes commands, and updates task list.
     */
    public void run() {
        ui.print("Hello! I'm Angel\nWhat can I do for you?");

        while (true) {
            try {
                String input = ui.readInput();
                String[] parts = input.split(" ", 2);
                String command = parts[0];

                switch (command) {
                case "bye":
                    ui.print("Bye. Hope to see you again soon!");
                    ui.close();
                    return;
                case "list":
                    ui.printTasks(taskList);
                    break;
                case "todo":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new InvalidCommandException(
                                "The description of a todo cannot be empty. Please specify a task after 'todo'."
                        );
                    }
                    taskList.addTask(new ToDo(parts[1]));
                    ui.print("Got it. I've added this task:\n  " + parts[1]);
                    break;
                case "deadline":
                    if (parts.length < 2 || !parts[1].contains(" /by ")) {
                        throw new InvalidCommandException(
                                "The description of a deadline task must include a '/by' followed by the due date/time. Example: 'deadline return book /by 2019-10-10 1800'."
                        );
                    }
                    String[] deadlineDetails = parts[1].split(" /by ");
                    LocalDateTime deadlineDate = parseDate(deadlineDetails[1]);
                    taskList.addTask(new Deadline(deadlineDetails[0], deadlineDate));
                    ui.print("Got it. I've added this task:\n  " + deadlineDetails[0] + " by: " + deadlineDate.format(INPUT_FORMAT));
                    break;
                case "event":
                    if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
                        throw new InvalidCommandException(
                                "The description of an event must include '/from' and '/to' followed by the start and end times. Example: 'event project meeting /from 2019-10-10 1800 /to 2019-10-10 2000'."
                        );
                    }
                    String[] eventDetails = parts[1].split(" /from ");
                    String[] times = eventDetails[1].split(" /to ");
                    LocalDateTime fromDate = parseDate(times[0]);
                    LocalDateTime toDate = parseDate(times[1]);
                    taskList.addTask(new Event(eventDetails[0], fromDate, toDate));
                    ui.print("Got it. I've added this task:\n  " + eventDetails[0] + " from: " + fromDate.format(INPUT_FORMAT) + " to: " + toDate.format(INPUT_FORMAT));
                    break;
                case "mark":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new InvalidCommandException(
                                "Please specify the task number to mark."
                        );
                    }
                    try {
                        int taskIndex = Integer.parseInt(parts[1]) - 1;
                        taskList.markTask(taskIndex);
                        ui.print("Nice! I've marked this task as done:\n  " + taskList.listTasks().get(taskIndex));
                    } catch (NumberFormatException e) {
                        throw new InvalidCommandException(
                                "Invalid task number for 'mark'. Please enter a valid number after 'mark'."
                        );
                    }
                    break;
                case "unmark":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new InvalidCommandException(
                                "Please specify the task number to unmark."
                        );
                    }
                    try {
                        int taskIndex = Integer.parseInt(parts[1]) - 1;
                        taskList.unmarkTask(taskIndex);
                        ui.print("OK, I've marked this task as not done yet:\n  " + taskList.listTasks().get(taskIndex));
                    } catch (NumberFormatException e) {
                        throw new InvalidCommandException(
                                "Invalid task number for 'unmark'. Please enter a valid number after 'unmark'."
                        );
                    }
                    break;
                case "delete":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new InvalidCommandException(
                                "Please specify the task number to delete."
                        );
                    }
                    try {
                        int taskIndex = Integer.parseInt(parts[1]) - 1;
                        taskList.deleteTask(taskIndex);
                        ui.print("Noted. I've removed this task:\n  " + taskList.listTasks().get(taskIndex));
                    } catch (NumberFormatException e) {
                        throw new InvalidCommandException(
                                "Invalid task number for 'delete'. Please enter a valid number after 'delete'."
                        );
                    }
                    break;
                default:
                    throw new InvalidCommandException(
                            "Unknown command: " + command + ". Please enter a valid command (e.g., list, todo, deadline, event, mark, unmark, delete, bye)."
                    );
                }
            } catch (InvalidCommandException e) {
                ui.printError(e.getMessage());
            } catch (TaskNotFoundException e) {
                ui.printError(e.getMessage() + " Please check the task number and try again.");
            } catch (AngelException e) {
                ui.printError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Parses a string into a LocalDateTime object using the predefined input format.
     *
     * @param dateTimeString The string to be parsed.
     * @return The corresponding LocalDateTime object.
     * @throws InvalidCommandException If the string cannot be parsed.
     */
    private LocalDateTime parseDate(String dateTimeString) throws InvalidCommandException {
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.");
        }
    }

    /**
     * Entry point of the Angel application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Angel().run();
    }
}
