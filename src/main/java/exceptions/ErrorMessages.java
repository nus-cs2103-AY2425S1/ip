package exceptions;

/**
 * ErrorMessages class contains all the error messages that are displayed to the user when an error occurs.
 */
public class ErrorMessages {
    public static final String ARRAY_OUT_OF_BOUNDS = "The number selected is out of range given the number of tasks!"
            + " Select a different task number that falls in the range";
    public static final String INCORRECT_FORMAT_FOR_ADDING_TODO_COMMAND = "The correct format for adding a new ToDo"
            + " task is: todo <TaskDescription>";
    public static final String INCORRECT_FORMAT_FOR_ADDING_DEADLINE_COMMAND = "The correct format for adding a new"
            + " Deadline Task is: deadline <TaskDescription> /by <YYYY-MM-DD>";
    public static final String INCORRECT_FORMAT_FOR_ADDING_EVENT_COMMAND = "The correct format for adding a new Event"
            + " Task is: event <TaskDescription> /from <YYYY-MM-DD> /to <YYYY-MM-DD>";
    public static final String INCORRECT_FORMAT_FOR_FIND_COMMAND = "The correct format for finding tasks with"
            + " associated characters is: find <keyword>. Note that it is case-sensitive!";
    public static final String INCORRECT_FORMAT_FOR_ON_COMMAND = "The correct format for finding tasks relevant to "
            + "a specific date is: on <date>";
    public static final String INCORRECT_FORMAT_FOR_CHANGEPRIORITY_COMMAND = "The correct format for changing task"
            + " priority is: cp <Task index> <Task priority level>";
    public static final String INCORRECT_FORMAT_FOR_DELETE_COMMAND = "The correct format for deleting task is: "
            + " delete <Task Index>";
    public static final String INCORRECT_ASCII_VALUE_INPUT = "Should be entering a number, "
            + "no other characters are allowed!";
    public static final String NO_TASKS_ARE_RELEVANT_TO_INPUT_DATE = "Looks like there are no tasks on this date!";
    public static final String INVALID_COMMAND = "The command you just entered is invalid: Try these "
            + "various commands \n"
            + "list - To list all tasks\n"
            + "cp - To change priority of specific tasks\n"
            + "deadline - To add a new task with a deadline\n"
            + "event - To add a new task with a starting time and ending time\n"
            + "mark - To mark a specific task as done\n"
            + "unmark - To unmark a specific task as done\n"
            + "todo - To add a new task without a deadline\n"
            + "delete - To delete a specific task\n"
            + "find - To find tasks with specific keywords\n"
            + "on - To find tasks relevant to the specific date\n";
}
