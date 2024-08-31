package colress;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import colress.command.Command;
import colress.exception.UnknownCommandException;
import colress.exception.UnknownTaskTypeException;

/**
 * Represents the Ui of the Colress chatbot.
 */
public final class Ui {
    private static final String MESSAGE_FAREWELL = "Well then, I'll see you soon.";
    private static final String MESSAGE_FILE_CREATED_CONFIRMATION = "New task list file created.";
    private static final String MESSAGE_FILE_EXISTS_CONFIRMATION = "Task list file exists. Retrieving file.";
    private static final String MESSAGE_GREETING = "Hello. My name is Colress.\n"
            + "What brings you here?";
    private static final String MESSAGE_LIST_EMPTY = "Your list is empty.";
    private static final String MESSAGE_NOT_A_VALID_DATE_TIME_ERROR =
            "You did not seem to have entered a valid date/time. Try Again.";
    private static final String MESSAGE_NOT_A_VALID_NUMBER_ERROR =
            "You did not seem to have entered a valid number. Try Again.";
    private static final String PROMPT_DATE = "Enter the date (in the form yyyy-mm-dd).";
    private static final String PROMPT_DEADLINE = "Enter the deadline (in the form yyyy-mm-dd).";
    private static final String PROMPT_DEADLINE_DESCRIPTION = "Enter the description of the deadline.";
    private static final String PROMPT_EVENT_DATE = "Enter the date of the event (in the form yyyy-mm-dd).";
    private static final String PROMPT_EVENT_DESCRIPTION = "Enter the description of the event.";
    private static final String PROMPT_EVENT_END_TIME = "Enter the ending time of the event (in the form hh:mm).";
    private static final String PROMPT_EVENT_START_TIME = "Enter the starting time of the event (in the form hh:mm).";
    private static final String PROMPT_TASK_DESCRIPTION = "Enter the description of the task.";
    private static final String PROMPT_TASK_NUMBER = "Enter the task number.";
    private static final String PROMPT_TASK_TYPE = "Enter the type of task you wish to add to your list.";
    private static final String SPACER = "____________________________________________________________________\n";
    private final Parser parser;
    private boolean hasCalledExitCommand;

    /**
     * Constructor for the Ui class.
     * The Ui object has a Parser object which reads user input and throws exceptions if invalid inputs are detected.
     * The Ui object also has a boolean field that reflects whether the exit command has been called by the user.
     */
    public Ui() {
        this.parser = new Parser();
        this.hasCalledExitCommand = false;
    }

    public boolean getHasCalledExitCommand() {
        return hasCalledExitCommand;
    }

    public void print(String s) {
        System.out.println(SPACER + s + "\n" + SPACER);
    }

    public void welcome() {
        print(MESSAGE_GREETING);
    }

    /**
     * Facilitates exiting the program.
     * The Ui prints a farewell message to the user.
     * The boolean field is assigned true to reflect that an exit command has been called.
     */
    public void exit() {
        print(MESSAGE_FAREWELL);
        hasCalledExitCommand = true;
    }

    /**
     * Prints a message to the user that reflects whether a new file has been created or if an existing file exists and
     * tasks have been loaded.
     *
     * @param hasCreatedNewFile A boolean argument that reflects whether a new file has been created.
     */
    public void printLoadTaskStatus(boolean hasCreatedNewFile) {
        if (hasCreatedNewFile) {
            print(MESSAGE_FILE_CREATED_CONFIRMATION);
        } else {
            print(MESSAGE_FILE_EXISTS_CONFIRMATION);
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
    public void processInput(TaskList taskList) {
        Command command = null;
        while (command == null) {
            try {
                command = parser.getCommand();
            } catch (UnknownCommandException e) {
                print(String.valueOf(e));
            }
        }
        command.execute(this, taskList);
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
        String result = null;
        while (result == null) {
            try {
                print(PROMPT_TASK_TYPE);
                result = parser.getTaskType();
            } catch (UnknownTaskTypeException e) {
                print(String.valueOf(e));
            }
        }
        return result;
    }

    /**
     * Prompts the user to enter a description for the task, reads it using its Parser object and returns it.
     *
     * @param taskType A String that represents the type of task, and prints the corresponding prompt.
     * @return A String that represents the description of the task indicated by the user.
     */
    public String promptDescription(String taskType) {
        switch (taskType) {
        case "deadline":
            print(PROMPT_DEADLINE_DESCRIPTION);
            break;
        case "event":
            print(PROMPT_EVENT_DESCRIPTION);
            break;
        default:
            print(PROMPT_TASK_DESCRIPTION);
        }
        return parser.getDescription();
    }

    /**
     * Prompts the user to enter a date for the task, reads it using its Parser object and returns it.
     * The method catches a DateTimeParseException thrown by the parser if an invalid input is received, and alerts
     * the user that an invalid date has been detected.
     *
     * @param taskType A String that represents the type of task, and prints the corresponding prompt.
     * @return A LocalDate object that represents the date of the task indicated by the user.
     */
    public LocalDate promptDate(String taskType) {
        String prompt;
        if (taskType.equals("deadline")) {
            prompt = PROMPT_DEADLINE;
        } else if (taskType.equals("event")) {
            prompt = PROMPT_EVENT_DATE;
        } else {
            prompt = PROMPT_DATE;
        }

        LocalDate result = null;
        while (result == null) {
            try {
                print(prompt);
                result = parser.readDate();
            } catch (DateTimeParseException e) {
                print(MESSAGE_NOT_A_VALID_DATE_TIME_ERROR);
            }
        }
        return result;
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
    public LocalTime promptTime(String timeType) {
        String prompt;
        if (timeType.equals("from")) {
            prompt = PROMPT_EVENT_START_TIME;
        } else {
            prompt = PROMPT_EVENT_END_TIME;
        }

        LocalTime result = null;
        while (result == null) {
            try {
                print(prompt);
                result = parser.readTime();
            } catch (DateTimeParseException e) {
                print(MESSAGE_NOT_A_VALID_DATE_TIME_ERROR);
            }
        }
        return result;
    }

    /**
     * Prompts the user to enter the task number of the task to operate on, reads it using its Parser object
     * and returns it.
     * The method catches a NumberFormatException thrown by the parser if a number is not received, and alerts
     * the user.
     * The method checks against the TaskList object if the input is valid, and alerts the user if it is not.
     */
    public int promptTaskNumber(TaskList taskList) {
        int result = -1;
        if (taskList.isEmpty()) {
            print(MESSAGE_LIST_EMPTY);
        } else {
            while (result == -1) {
                try {
                    print(PROMPT_TASK_NUMBER);
                    result = parser.getTaskNumber();
                    if (taskList.isOutOfBounds(result)) {
                        print(MESSAGE_NOT_A_VALID_NUMBER_ERROR);
                    }
                } catch (NumberFormatException e) {
                    print(MESSAGE_NOT_A_VALID_NUMBER_ERROR);
                }
            }
        }
        return result;
    }

    /**
     * Facilitates printing the list of tasks to the user.
     *
     * @param taskList A TaskList object representing the current list of tasks, for the method to retrieve the tasks
     *     to print.
     * @param printsAll A boolean argument to indicate whether the user wishes to print every task or not.
     */
    public void printTasks(TaskList taskList, boolean printsAll) {
        if (taskList.isEmpty()) {
            print(MESSAGE_LIST_EMPTY);
        } else if (!printsAll) {
            String result = taskList.retrieveTasks(promptDate("date"));
            print(Objects.equals(result, "") ? MESSAGE_LIST_EMPTY : result);
        } else {
            String result = taskList.retrieveTasks();
            print(Objects.equals(result, "") ? MESSAGE_LIST_EMPTY : result);
        }
    }

    /**
     * Prints the message and the current list of tasks.
     */
    public void printConfirmationMessage(TaskList taskList, String message) {
        print(message);
        printTasks(taskList, true);
    }
}
