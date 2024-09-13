package diomon.parser;

import diomon.command.*;
import diomon.task.Deadline;
import diomon.task.Event;
import diomon.task.Task;
import diomon.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * The {@code Parser} class provides methods to parse stored task data
 * into {@link Task} objects and to format dates according to the application's
 * requirements.
 */
public class Parser {

    /**
     * The {@code DateTimeFormatter} used to format and parse dates
     * in the "dd-MM-yyyy" pattern.
     */
    public static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static Command parse(String input){
        String[] inputArray = input.split(" ", 2);
        String inputCommand = inputArray[0];
        String inputContent = inputArray.length == 1 ? null: inputArray[1];
        Command.Types t = Command.checkType(inputCommand);
        switch (t) {
        case LIST:
            return new ListCommand();
        case BYE:
            return new ByeCommand();
        case HELP:
            return new HelpCommand();
        case TODO:
            return new AddTodoCommand(inputContent);
        case DEADLINE:
            return new AddDeadlineCommand(inputContent);
        case EVENT:
            return new AddEventCommand(inputContent);
        case MARK:
            return new MarkCommand(inputContent);
        case UNMARK:
            return new UnmarkCommand(inputContent);
        case DELETE:
            return new DeleteCommand(inputContent);
        case FIND:
            return new FindCommand(inputContent);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Parses a string of stored task data and returns the corresponding {@link Task} object.
     * The task type is determined by the type icon at the beginning of the string.
     *
     * @param data The stored task data, formatted as a string with fields separated by the "|" symbol.
     * @return The corresponding {@code Task} object.
     * @throws RuntimeException If the task type is unrecognized or the data is invalid.
     */
    public static Task loadTask(String data) {
        String[] dataArr = data.split("\\|");
        boolean completed = false;
        if (dataArr[1].equals("X")) {
            completed = true;
        }
        switch (dataArr[0]) {
        case Todo.TYPEICON:
            return parseStoredTodo(dataArr);
        case Event.TYPEICON:
            return parseStoredEvent(dataArr);
        case Deadline.TYPEICON:
            return parseStoredDeadline(dataArr);
        default:
            throw new RuntimeException("Error loading task");
        }
    }

    /**
     * Parses stored data for a {@link Todo} task.
     *
     * @param dataArr An array of strings representing the stored fields for the todo task.
     * @return A {@code Todo} task object.
     * @throws RuntimeException If the completion status is invalid or the data format is incorrect.
     */
    public static Task parseStoredTodo(String[] dataArr) {
        if (checkStoredStatus(dataArr[1])) {
            throw new RuntimeException("CompletionStatus seem to be wrong");
        }
        if (dataArr.length == 3) {
            return new Todo(dataArr[1].equals(Task.COMPLETEICON), dataArr[2]);
        }
        throw new RuntimeException("Error loading todo task, data stored is wrong");
    }

    /**
     * Parses stored data for a {@link Deadline} task.
     *
     * @param dataArr An array of strings representing the stored fields for the deadline task.
     * @return A {@code Deadline} task object.
     * @throws RuntimeException If the completion status is invalid or the data format is incorrect.
     */
    public static Task parseStoredDeadline(String[] dataArr) {
        if (checkStoredStatus(dataArr[1])) {
            throw new RuntimeException("CompletionStatus seem to be wrong");
        }
        if (dataArr.length == 4) {
            return new Deadline(dataArr[1].equals(Task.COMPLETEICON), dataArr[2], LocalDate.parse(dataArr[3], DATEFORMATTER));
        } else {
            throw new RuntimeException("Error loading deadline task, data stored is wrong");
        }
    }

    /**
     * Parses stored data for an {@link Event} task.
     *
     * @param dataArr An array of strings representing the stored fields for the event task.
     * @return An {@code Event} task object.
     * @throws RuntimeException If the completion status is invalid or the data format is incorrect.
     */
    public static Task parseStoredEvent(String[] dataArr) {
        if (checkStoredStatus(dataArr[1])) {
            throw new RuntimeException("CompletionStatus seem to be wrong");
        }
        if (dataArr.length == 5) {
            return new Event(dataArr[1].equals(Task.COMPLETEICON),
                    dataArr[2],
                    LocalDate.parse(dataArr[3], DATEFORMATTER),
                    LocalDate.parse(dataArr[4], DATEFORMATTER));
        } else {
            throw new RuntimeException("Error loading event task, data stored is wrong");
        }
    }

    /**
     * Checks whether the stored status is valid (either complete or incomplete).
     *
     * @param status The stored status string to be checked.
     * @return {@code true} if the status is invalid, {@code false} if valid.
     */
    public static boolean checkStoredStatus(String status) {
        return !(status.equals(Task.COMPLETEICON) || status.equals(Task.INCOMPLETEICON));
    }

    public static Event parseEvent(String task){
        String[] taskArr = task.split("/", 3);
        if (taskArr.length == 3){
            String[] from = taskArr[1].split(" ", 2);
            String[] to = taskArr[2].split(" ", 2);
            if (from[0].equalsIgnoreCase("from") && from.length == 2 && to[0].equalsIgnoreCase("to") && to.length == 2) {
                return new Event(taskArr[0],
                        LocalDate.parse(from[1].replaceAll(" ",""), Parser.DATEFORMATTER),
                        LocalDate.parse(to[1].replaceAll(" ",""),Parser.DATEFORMATTER));
            }
        }
        throw new RuntimeException("Error parsing Event");
    }
    public static Deadline parseDeadline(String task){
        String[] taskArray = task.split("/", 2);
        if (taskArray.length == 2){
            String[] by = taskArray[1].split(" ", 2);
            if (by[0].equalsIgnoreCase("by") && by.length == 2) {
                return new Deadline(taskArray[0], LocalDate.parse(by[1], Parser.DATEFORMATTER));
            }
        }
        throw new RuntimeException("Error creating Deadline, Please check your format is correct");
    }

    public static Todo parseTodo(String task){
        return new Todo(task);
    }
}
