package colress;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;

import colress.command.AddCommand;
import colress.command.Command;
import colress.command.DateCommand;
import colress.exception.EmptyInputException;
import colress.exception.UnknownCommandException;
import colress.exception.UnknownTaskTypeException;

/**
 * Represents the Ui of the Colress chatbot.
 */
public final class Ui {
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
    public static final String PROMPT_TASK_NUMBER = "Enter the task number."
            + "You can enter multiple numbers with spaces between them";
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    /**
     * Checks current status of the Ui, which is an indication of what type of input is being expected from the user,
     * and calls the corresponding processing method to process the user input.
     *
     * @param taskList A TaskList object that is passed to the processing methods that require them to correctly
     *                 process user input.
     */
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
     * Parses command input by the user using its Parser object and starts the processing of the command.
     * The method catches an UnknownCommandException thrown by the parser if an invalid input is received, and alerts
     * the user that an invalid command has been detected.
     *
     * @param taskList A TaskList object that is passed to the start method of the commands to allow the commands to
     *                 perform operations on the list of tasks.
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
     * If the given TaskList is empty, return an empty list message.
     * Otherwise, set status of the UI to expect a keyword for the user's next input and returns a prompt to the user
     * for a keyword to find in the list of tasks.
     */
    public String promptKeyword(TaskList taskList) {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }
        setStatus(Status.KEYWORD);
        return PROMPT_KEYWORD;
    }

    /**
     * Stores the keyword given by the user in the FindCommand Object and then executes the command.
     * The method catches an EmptyInputException if user input is empty, and returns an error message to the user.
     * @param input The user input.
     * @param taskList The TaskList to print the list of tasks from.
     */
    public String processKeyword(String input, TaskList taskList) {
        try {
            input = parser.getString(input);
            currCommand.initialise(input);
            return currCommand.execute(this, taskList);
        } catch (EmptyInputException e) {
            return e.getMessage();
        }
    }

    /**
     * Set status of the UI to expect a task type for the user's next input and returns a prompt to the user
     * for a task type to add to the list of tasks.
     */
    public String promptTaskType() {
        setStatus(Status.TASKTYPE);
        return PROMPT_TASK_TYPE;
    }

    /**
     * Parses the user input using the Parser Object, and stores the task type given by the user in the
     * AddCommand Object. Then, return a prompt to the user for a description of the task to add to the list of tasks.
     * The method catches an IllegalArgumentException if user input is not a recognisable task type, and returns an
     * error message to the user.
     */
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
     * Set status of the UI to expect a task description for the user's next input and returns a prompt to the user
     * for a description of the task to add to the list of tasks. The prompt returned depends on the type of task
     * to be added, indicated by the TaskType argument.
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

    /**
     * Parses the user input using the Parser Object, and stores the task description given by the user in the
     * AddCommand Object. Checks the type of task to be added. If task type is a to-do, then call the AddCommand's
     * execute method. Else, return a prompt to the user for a date of the deadline or event.
     * The method catches an EmptyInputException if user input is empty, and returns an error message to the user.
     *
     * @param input The user input.
     * @param taskList The TaskList to add the task to.
     */
    public String processDescription(String input, TaskList taskList) {
        try {
            // A typecast is required here because not all command objects have the getTaskType method.
            // The only command that will lead to this method being called is the AddCommand command.
            // Therefore, this is a safe typecast.
            assert currCommand instanceof AddCommand : "Current Command should be an AddCommand";
            AddCommand c = (AddCommand) currCommand;
            input = parser.getString(input);
            c.initialise(input);
            TaskType currTaskType = c.getTaskType();
            switch (currTaskType) {
            case TODO:
                return c.execute(this, taskList);
            case DEADLINE, EVENT:
                return promptDate(currTaskType, taskList);
            default:
                return "There is an error. Try again.";
            }
        } catch (EmptyInputException e) {
            return e.getMessage();
        }
    }

    /**
     * If the current command to be executed is the date command, check if the given TaskList is empty.
     * If so, return an empty list message.
     * Otherwise, set status of the UI to expect a date for the user's next input and returns a prompt to the user
     * for a date. The prompt returned depends on the current command to be executed and the type of task to be added.
     *
     * @param taskType The type of the task to be added.
     * @param taskList The TaskList to add the task to.
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

    /**
     * Parses the user input using the Parser Object, and stores the date given by the user in the command object.
     * If the current command to be executed is the add command, and the task to be added is an event,
     * return a prompt to the user for a starting time of the event.
     * Otherwise, call the command's execute method and return its value.
     * The method catches DateTimeParseException and returns an error message.
     *
     * @param input The user input.
     * @param taskList The TaskList to add the task to or print from.
     */
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
     * Checks whether a start time or an end time is expected using the timeType argument and sets the status of the UI
     * to expect the right time. The corresponding prompt is then returned.
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

    /**
     * Parses the user input using the Parser Object, and stores the starting time given by the user in the
     * command object. Return a prompt to the user for the ending time of the event.
     * The method catches DateTimeParseException and returns an error message.
     *
     * @param input The user input.
     */
    public String processStartTime(String input) {
        try {
            LocalTime result = parser.readTime(input);
            currCommand.initialise(result);
            return promptTime("to");
        } catch (DateTimeParseException e) {
            return MESSAGE_NOT_A_VALID_DATE_TIME_ERROR;
        }
    }

    /**
     * Parses the user input using the Parser Object, and stores the ending time given by the user in the
     * command object. Call the execute method of the command object and return its result.
     * The method catches DateTimeParseException and returns an error message.
     *
     * @param input The user input.
     */
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
     */
    public String promptTaskNumber(TaskList taskList) {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }
        setStatus(Status.TASKNUMBER);
        return PROMPT_TASK_NUMBER;
    }

    /**
     * Parses the user input using the Parser Object, and check if the given task number is valid.
     * If so, store the task number in the command object, call the command object's execute method and returns
     * its value.
     *
     * @param input The user input.
     * @param taskList The TaskList to perform operations on.
     */
    public String processTaskNumber(String input, TaskList taskList) {
        try {
            int[] result = parser.getTaskNumber(input);
            for (int i: result) {
                if (taskList.isOutOfBounds(i)) {
                    return MESSAGE_NOT_A_VALID_NUMBER_ERROR;
                }
            }
            Arrays.sort(result);
            currCommand.initialise(result);
            return currCommand.execute(this, taskList);
        } catch (NumberFormatException e) {
            return MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        }
    }

    /**
     * Sets status of the UI to expect a command for the user's next input and returns a String illustration of the list
     * of tasks in the given TaskList.
     */
    public String printTasks(TaskList taskList) {
        setStatus(Status.COMMAND);
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }

        return taskList.retrieveTasks();
    }

    /**
     * Sets status of the UI to expect a command for the user's next input and returns a String illustration of the list
     * of tasks in the given TaskList that falls on the specified date.
     */
    public String printTasks(TaskList taskList, LocalDate date) {
        setStatus(Status.COMMAND);
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }

        String result = taskList.retrieveTasks(date);
        return Objects.equals(result, "") ? MESSAGE_LIST_EMPTY : result;
    }

    /**
     * Sets status of the UI to expect a command for the user's next input and returns a String illustration of the list
     * of tasks in the given TaskList whose description contains the specified keyword.
     */
    public String printTasks(TaskList taskList, String keyword) {
        setStatus(Status.COMMAND);
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
    public String printConfirmationMessage(TaskList taskList, String message) {
        String result = message + "\n\n" + printTasks(taskList);
        setStatus(Status.WRITE);
        return result;
    }
}
