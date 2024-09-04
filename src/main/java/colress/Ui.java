package colress;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import colress.command.AddCommand;
import colress.command.Command;
import colress.command.DateCommand;
import colress.exception.UnknownCommandException;
import colress.exception.UnknownTaskTypeException;

/**
 * Represents the Ui of the Colress chatbot.
 */
public final class Ui {
    public static final String MESSAGE_FILE_CREATED_CONFIRMATION = "New task list file created.";
    public static final String MESSAGE_FILE_EXISTS_CONFIRMATION = "Task list file exists. Retrieving file.";
    public static final String MESSAGE_GREETING = "Hello. My name is Colress.\n"
            + "What brings you here?";
    public static final String MESSAGE_LIST_EMPTY = "Your list is empty.";
    public static final String MESSAGE_NOT_A_VALID_DATE_TIME_ERROR =
            "You did not seem to have entered a valid date/time. Try Again.";
    public static final String MESSAGE_NOT_A_VALID_NUMBER_ERROR =
            "You did not seem to have entered a valid number. Try Again.";
    public static final String PROMPT_DATE = "Enter the date (in the form yyyy-mm-dd).";
    public static final String PROMPT_DEADLINE = "Enter the deadline (in the form yyyy-mm-dd).";
    public static final String PROMPT_DEADLINE_DESCRIPTION = "Enter the description of the deadline.";
    public static final String PROMPT_EVENT_DATE = "Enter the date of the event (in the form yyyy-mm-dd).";
    public static final String PROMPT_EVENT_DESCRIPTION = "Enter the description of the event.";
    public static final String PROMPT_EVENT_END_TIME = "Enter the ending time of the event (in the form hh:mm).";
    public static final String PROMPT_EVENT_START_TIME = "Enter the starting time of the event (in the form hh:mm).";
    public static final String PROMPT_KEYWORD = "Enter the keyword to find in the list.";
    public static final String PROMPT_TASK_DESCRIPTION = "Enter the description of the task.";
    public static final String PROMPT_TASK_NUMBER = "Enter the task number.";
    public static final String PROMPT_TASK_TYPE = "Enter the type of task you wish to add to your list.";
    private final Parser parser;
    private Status status;
    private Command currCommand;

    /**
     * Constructor for the Ui class.
     * The Ui object has a Parser object which reads user input and throws exceptions if invalid inputs are detected.
     * The Ui object also has a boolean field that reflects whether the exit command has been called by the user.
     */
    public Ui() {
        this.parser = new Parser();
        this.status = Status.COMMAND;
    }

    public String welcome() {
        return MESSAGE_GREETING;
    }

    /**
     * Prints a message to the user that reflects whether a new file has been created or if an existing file exists and
     * tasks have been loaded.
     *
     * @param hasCreatedNewFile A boolean argument that reflects whether a new file has been created.
     */
    public String printLoadTaskStatus(boolean hasCreatedNewFile) {
        if (hasCreatedNewFile) {
            return MESSAGE_FILE_CREATED_CONFIRMATION;
        } else {
            return MESSAGE_FILE_EXISTS_CONFIRMATION;
        }
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String processInput(String input, TaskList taskList) {
        switch(this.status) {
        case COMMAND:
            return processCommand(input, taskList);
        case TASKTYPE:
            return processTaskType(input);
        case DESCRIPTION:
            return processDescription(input, taskList);
        case DATE:
            return processDate(input, taskList);
        case STARTTIME:
            return processStartTime(input);
        case ENDTIME:
            return processEndTime(input, taskList);
        case TASKNUMBER:
            return processTaskNumber(input, taskList);
        case KEYWORD:
            return processKeyword(input, taskList);
        default:
            return "There is an error. Try again.";
        }
    }

    /**
     * Reads command input by the user using its Parser object and executes the command.
     * The method catches an UnknownCommandException thrown by the parser if an invalid input is received, and alerts
     * the user that an invalid command has been detected.
     *
     * @param taskList A TaskList object that is passed to the execute method of the commands to allow the commands to
     *     do operations on the list of tasks.
     */
    public String processCommand(String input, TaskList taskList) {
        try {
            this.currCommand = parser.getCommand(input);
            return currCommand.start(this, taskList);
        } catch (UnknownCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Prompts the user for a keyword to find in the list of tasks and returns it.
     */
    public String promptKeyword(TaskList taskList) {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }
        setStatus(Status.KEYWORD);
        return PROMPT_KEYWORD;
    }

    public String processKeyword(String input, TaskList taskList) {
        currCommand.initialise(input);
        return currCommand.execute(this, taskList);
    }

    /**
     * Prompts the user to enter a task type to create and add to the list, reads the task type input by the user
     * using its Parser object and returns it.
     * The method catches an UnknownTaskTypeException thrown by the parser if an invalid input is received, and alerts
     * the user that an invalid task type has been detected.
     *
     * @return A String that represents the type of task indicated by the user.
     */
    public String promptTaskType() {
        setStatus(Status.TASKTYPE);
        return PROMPT_TASK_TYPE;
    }

    public String processTaskType(String input) {
        try {
            TaskType result = parser.getTaskType(input);
            currCommand.initialise(result);
            return promptDescription(result);
        } catch (IllegalArgumentException e) {
            return new UnknownTaskTypeException().getMessage();
        }
    }

    /**
     * Prompts the user to enter a description for the task, reads it using its Parser object and returns it.
     *
     * @param taskType A String that represents the type of task, and prints the corresponding prompt.
     * @return A String that represents the description of the task indicated by the user.
     */
    public String promptDescription(TaskType taskType) {
        setStatus(Status.DESCRIPTION);
        switch (taskType) {
        case DEADLINE:
            return PROMPT_DEADLINE_DESCRIPTION;
        case EVENT:
            return PROMPT_EVENT_DESCRIPTION;
        default:
            return PROMPT_TASK_DESCRIPTION;
        }
    }

    public String processDescription(String input, TaskList taskList) {
        AddCommand c = (AddCommand) currCommand;
        c.initialise(input);
        TaskType currTasktype = c.getTaskType();
        switch (currTasktype) {
        case TODO:
            return c.execute(this, taskList);
        case DEADLINE, EVENT:
            return promptDate(currTasktype, taskList);
        default:
            return "There is an error. Try again.";
        }
    }

    /**
     * Prompts the user to enter a date for the task, reads it using its Parser object and returns it.
     * The method catches a DateTimeParseException thrown by the parser if an invalid input is received, and alerts
     * the user that an invalid date has been detected.
     *
     * @param taskType A String that represents the type of task, and prints the corresponding prompt.
     * @return A LocalDate object that represents the date of the task indicated by the user.
     */
    public String promptDate(TaskType taskType, TaskList taskList) {
        if (currCommand instanceof DateCommand && taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }
        setStatus(Status.DATE);
        switch (taskType) {
        case DEADLINE:
            return PROMPT_DEADLINE;
        case EVENT:
            return PROMPT_EVENT_DATE;
        default:
            return PROMPT_DATE;
        }
    }

    public String processDate(String input, TaskList taskList) {
        LocalDate result;
        try {
            result = parser.readDate(input);
            currCommand.initialise(result);

            if (currCommand instanceof AddCommand && ((AddCommand) currCommand).getTaskType() == TaskType.EVENT) {
                return promptTime("from");
            }
            return currCommand.execute(this, taskList);
        } catch (DateTimeParseException e) {
            return MESSAGE_NOT_A_VALID_DATE_TIME_ERROR;
        }
    }

    /**
     * Prompts the user to enter a time for the event, reads it using its Parser object and returns it.
     * The method catches a DateTimeParseException thrown by the parser if an invalid input is received, and alerts
     * the user that an invalid time has been detected.
     *
     * @param timeType A String that represents whether a starting time or an ending time is being requested,
     *     and prints the corresponding prompt.
     * @return A LocalTime object that represents the time of the event indicated by the user.
     */
    public String promptTime(String timeType) {
        if (timeType.equals("from")) {
            setStatus(Status.STARTTIME);
            return PROMPT_EVENT_START_TIME;
        } else {
            setStatus(Status.ENDTIME);
            return PROMPT_EVENT_END_TIME;
        }
    }

    public String processStartTime(String input) {
        try {
            LocalTime result = parser.readTime(input);
            currCommand.initialise(result);
            return promptTime("to");
        } catch (DateTimeParseException e) {
            return MESSAGE_NOT_A_VALID_DATE_TIME_ERROR;
        }
    }

    public String processEndTime(String input, TaskList taskList) {
        try {
            LocalTime result = parser.readTime(input);
            currCommand.initialise(result);
            return currCommand.execute(this, taskList);
        } catch (DateTimeParseException e) {
            return MESSAGE_NOT_A_VALID_DATE_TIME_ERROR;
        }
    }

    /**
     * Prompts the user to enter the task number of the task to operate on, reads it using its Parser object
     * and returns it.
     * The method catches a NumberFormatException thrown by the parser if a number is not received, and alerts
     * the user.
     * The method checks against the TaskList object if the input is valid, and alerts the user if it is not.
     */
    public String promptTaskNumber(TaskList taskList) {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }
        setStatus(Status.TASKNUMBER);
        return PROMPT_TASK_NUMBER;
    }

    public String processTaskNumber(String input, TaskList taskList) {
        try {
            int result = parser.getTaskNumber(input);
            if (taskList.isOutOfBounds(result)) {
                return MESSAGE_NOT_A_VALID_NUMBER_ERROR;
            } else {
                currCommand.initialise(result);
                return currCommand.execute(this, taskList);
            }
        } catch (NumberFormatException e) {
            return MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        }
    }

    public String printTasks(TaskList taskList) {
        setStatus(Status.COMMAND);
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }

        String result = taskList.retrieveTasks();
        return Objects.equals(result, "") ? MESSAGE_LIST_EMPTY : result;
    }

    public String printTasks(TaskList taskList, LocalDate date) {
        setStatus(Status.COMMAND);
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }

        String result = taskList.retrieveTasks(date);
        return Objects.equals(result, "") ? MESSAGE_LIST_EMPTY : result;
    }

    public String printTasks(TaskList taskList, String keyword) {
        setStatus(Status.COMMAND);
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }

        String result = taskList.retrieveTasks(keyword);
        return Objects.equals(result, "") ? MESSAGE_LIST_EMPTY : result;
    }

    /**
     * Prints the message and the current list of tasks.
     */
    public String printConfirmationMessage(TaskList taskList, String message) {
        return message + "\n\n" + printTasks(taskList);
    }
}
