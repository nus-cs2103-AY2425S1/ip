package colress;

import java.time.LocalDate;
import java.util.Objects;

import colress.parser.Parsable;
import colress.parser.Parser;
import colress.tasklist.TaskListable;

/**
 * Represents the Ui of the Colress chatbot.
 */
public abstract class Ui {
    public static final String MESSAGE_GREETING = "Oh, excuse me! My name is Colress.\n"
            + "May I have a look at your tasks?";
    public static final String MESSAGE_LIST_EMPTY = "It looks like your list is empty.";
    public static final String MESSAGE_NOT_A_VALID_DATE_TIME_ERROR =
            "What is this?! You did not seem to have entered a valid date/time! Try Again.";
    public static final String MESSAGE_NOT_A_VALID_NUMBER_ERROR =
            "What is this?! You did not seem to have entered a valid number! Try Again.";
    public static final String PROMPT_DATE = "Come! Enter the date (in the form yyyy-mm-dd).";
    public static final String PROMPT_DEADLINE = "Come! Enter the deadline (in the form yyyy-mm-dd).";
    public static final String PROMPT_DEADLINE_DESCRIPTION = "Come! Enter the description of the deadline.";
    public static final String PROMPT_EVENT_DATE = "Come! Enter the date of the event (in the form yyyy-mm-dd).";
    public static final String PROMPT_EVENT_DESCRIPTION = "Come! Enter the description of the event.";
    public static final String PROMPT_EVENT_END_TIME =
            "Come! Enter the ending time of the event (in the form hh:mm).";
    public static final String PROMPT_EVENT_START_TIME =
            "Come! Enter the starting time of the event (in the form hh:mm).";
    public static final String PROMPT_KEYWORD = "Come! Enter the keyword to find in the list.";
    public static final String PROMPT_TASK_DESCRIPTION = "Come! Enter the description of the task.";
    public static final String PROMPT_TASK_NUMBER = "Come! Enter the task number."
            + "You can enter multiple numbers with a space between them";
    public static final String PROMPT_TASK_TYPE = "Come! Enter the type of task you wish to add to your list.";

    private final Colress colress;
    private final Parsable parser;
    private Status status;

    /**
     * Instantiates colress and status field of Ui.
     * The Ui object has a Parsable object which reads user input and throws exceptions if invalid inputs are detected.
     * The Ui object has a status field which is an indication of what type of input is being expected from the user.
     */
    public Ui(Colress colress) {
        this.colress = colress;
        this.parser = new Parser();
        this.status = Status.COMMAND;
    }

    public String welcome() {
        return MESSAGE_GREETING;
    }

    public Colress getColress() {
        return colress;
    }

    public Parsable getParser() {
        return parser;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setCommandType(String commandType) {
        colress.setCommandType(commandType);
    }

    /**
     * Returns a String illustration of the list of tasks in the given TaskListable.
     */
    public String printTasks(TaskListable taskList) {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }

        return taskList.retrieveTasks();
    }

    /**
     * Returns a String illustration of the list of tasks in the given TaskListable that falls on the specified date.
     */
    public String printTasks(TaskListable taskList, LocalDate date) {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }

        String result = taskList.retrieveTasks(date);
        return Objects.equals(result, "") ? MESSAGE_LIST_EMPTY : result;
    }

    /**
     * Returns a String illustration of the list of tasks in the given TaskListable whose description contains
     * the specified keyword.
     */
    public String printTasks(TaskListable taskList, String keyword) {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }

        String result = taskList.retrieveTasks(keyword);
        return Objects.equals(result, "") ? MESSAGE_LIST_EMPTY : result;
    }

    /**
     * Sets the status of the UI to expect a call to storage to write the modified task list to the task file.
     * Return the given message and the current list of tasks.
     */
    public String printConfirmationMessage(TaskListable taskList, String message) {
        setStatus(Status.WRITE);
        return message + "\n\n" + printTasks(taskList);
    }

    public boolean toggleMode() {
        return colress.toggleMode();
    }

    public abstract String processInput(String input, TaskListable taskList);
}
