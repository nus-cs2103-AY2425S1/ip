package colress;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import colress.command.AddCommand;
import colress.command.Command;
import colress.command.DateCommand;
import colress.exception.EmptyInputException;
import colress.exception.EndTimeException;
import colress.exception.UnknownCommandException;
import colress.exception.UnknownTaskTypeException;

/**
 * Represents the Beginner mode Ui of the Colress chatbot.
 */
public final class UiBeginner extends Ui {

    private final Parser parser;
    private Command currCommand;

    /**
     * Constructs Ui for Beginner mode.
     * The Ui object has a Parser object which reads user input and throws exceptions if invalid inputs are detected.
     */
    public UiBeginner(Colress colress) {
        super(colress);
        this.parser = new Parser();
    }

    /**
     * Checks current status of the Ui and calls the corresponding processing method to process the user input.
     *
     * @param taskList A TaskList object that is passed to the processing methods that require them to correctly
     *                 process user input.
     */
    @Override
    public String processInput(String input, TaskList taskList) {
        switch(getStatus()) {
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
            setCommandType("error");
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
    @Override
    public String processCommand(String input, TaskList taskList) {
        try {
            this.currCommand = parser.getCommand(input);
            setCommandType(currCommand.toString());
            return currCommand.start(this, taskList);
        } catch (UnknownCommandException e) {
            setCommandType("error");
            return e.getMessage();
        }
    }

    /**
     * Sets status of the UI to expect a keyword for the user's next input and returns a prompt to the user
     * for a keyword to find in the list of tasks.
     * If the given TaskList is empty, return an empty list message.
     */
    @Override
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
     *
     * @param input The user input.
     * @param taskList The TaskList to print the list of tasks from.
     */
    @Override
    public String processKeyword(String input, TaskList taskList) {
        try {
            input = parser.getString(input);
            currCommand.initialise(input);
            return currCommand.execute(this, taskList);
        } catch (EmptyInputException e) {
            setCommandType("error");
            return e.getMessage();
        }
    }

    /**
     * Sets status of the UI to expect a task type for the user's next input and returns a prompt to the user
     * for a task type to add to the list of tasks.
     */
    @Override
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
    @Override
    public String processTaskType(String input) {
        try {
            TaskType result = parser.getTaskType(input);
            currCommand.initialise(result);
            return promptDescription(result);
        } catch (IllegalArgumentException e) {
            setCommandType("error");
            return new UnknownTaskTypeException().getMessage();
        }
    }

    /**
     * Sets status of the UI to expect a task description for the user's next input and returns a prompt to the user
     * for a description of the task to add to the list of tasks. The prompt returned depends on the type of task
     * to be added, indicated by the TaskType argument.
     */
    @Override
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
    @Override
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
                setCommandType("error");
                return "There is an error. Try again.";
            }
        } catch (EmptyInputException e) {
            setCommandType("error");
            return e.getMessage();
        }
    }

    /**
     * Sets status of the UI to expect a date for the user's next input and returns a prompt to the user
     * for a date. The prompt returned depends on the current command to be executed and the type of task to be added.
     * If the current command to be executed is the date command, check if the given TaskList is empty.
     * If so, return an empty list message.
     *
     * @param taskType The type of the task to be added.
     * @param taskList The TaskList to add the task to.
     */
    @Override
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
    @Override
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
            setCommandType("error");
            return MESSAGE_NOT_A_VALID_DATE_TIME_ERROR;
        }
    }

    /**
     * Checks whether a start time or an end time is expected using the timeType argument and sets the status of the UI
     * to expect the right time. The corresponding prompt is then returned.
     */
    @Override
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
    @Override
    public String processStartTime(String input) {
        try {
            LocalTime result = parser.readTime(input);
            currCommand.initialise(result);
            return promptTime("to");
        } catch (DateTimeParseException e) {
            setCommandType("error");
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
    @Override
    public String processEndTime(String input, TaskList taskList) {
        try {
            LocalTime result = parser.readTime(input);
            // A typecast is required here because not all command objects have the getTaskType method.
            // The only command that will lead to this method being called is the AddCommand command.
            // Therefore, this is a safe typecast.
            AddCommand c = (AddCommand) currCommand;
            if (!c.isValidEndTime(result)) {
                throw new EndTimeException();
            }
            currCommand.initialise(result);
            return currCommand.execute(this, taskList);
        } catch (EndTimeException e) {
            setCommandType("error");
            return e.getMessage();
        } catch (DateTimeParseException e) {
            setCommandType("error");
            return MESSAGE_NOT_A_VALID_DATE_TIME_ERROR;
        }
    }

    /**
     * Prompts the user to enter the task number of the task to operate on, reads it using its Parser object
     * and returns it.
     */
    @Override
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
    @Override
    public String processTaskNumber(String input, TaskList taskList) {
        try {
            int[] result = parser.getTaskNumber(input);
            for (int i: result) {
                if (taskList.isOutOfBounds(i)) {
                    setCommandType("error");
                    return MESSAGE_NOT_A_VALID_NUMBER_ERROR;
                }
            }
            Arrays.sort(result);
            currCommand.initialise(result);
            return currCommand.execute(this, taskList);
        } catch (NumberFormatException e) {
            setCommandType("error");
            return MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        }
    }

    /**
     * Sets status of the UI to expect a command for the user's next input and returns a String illustration of the list
     * of tasks in the given TaskList.
     */
    @Override
    public String printTasks(TaskList taskList) {
        setStatus(Status.COMMAND);
        return super.printTasks(taskList);
    }

    /**
     * Sets status of the UI to expect a command for the user's next input and returns a String illustration of the list
     * of tasks in the given TaskList that falls on the specified date.
     */
    @Override
    public String printTasks(TaskList taskList, LocalDate date) {
        setStatus(Status.COMMAND);
        return super.printTasks(taskList, date);
    }

    /**
     * Sets status of the UI to expect a command for the user's next input and returns a String illustration of the list
     * of tasks in the given TaskList whose description contains the specified keyword.
     */
    @Override
    public String printTasks(TaskList taskList, String keyword) {
        setStatus(Status.COMMAND);
        return super.printTasks(taskList, keyword);
    }

    /**
     * Sets the status of the UI to expect a call to storage to write the modified task list to the task file.
     * Return the given message and the current list of tasks.
     */
    @Override
    public String printConfirmationMessage(TaskList taskList, String message) {
        setStatus(Status.WRITE);
        return super.printConfirmationMessage(taskList, message);
    }
}
