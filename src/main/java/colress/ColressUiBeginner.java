package colress;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import colress.command.AddCommand;
import colress.command.Command;
import colress.exception.EmptyInputException;
import colress.exception.EndTimeException;
import colress.exception.UnknownCommandException;
import colress.exception.UnknownTaskTypeException;
import colress.tasklist.TaskList;

/**
 * Represents the Beginner mode Ui of the Colress chatbot.
 */
public final class ColressUiBeginner extends UiBeginner {

    private Command currCommand;

    public ColressUiBeginner(Chatbot colress) {
        super(colress);
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
            this.currCommand = getParser().getCommand(input);
            setCommandType(currCommand.toString());
            return currCommand.start(this, taskList);
        } catch (UnknownCommandException e) {
            setCommandType("error");
            return e.getMessage();
        }
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
            input = getParser().getString(input);
            currCommand.initialise(input);
            return currCommand.execute(this, taskList);
        } catch (EmptyInputException e) {
            setCommandType("error");
            return e.getMessage();
        }
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
            TaskType result = getParser().getTaskType(input);
            currCommand.initialise(result);
            return promptDescription(result);
        } catch (IllegalArgumentException e) {
            setCommandType("error");
            return new UnknownTaskTypeException().getMessage();
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
            input = getParser().getString(input);
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
            result = getParser().readDate(input);
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
     * Parses the user input using the Parser Object, and stores the starting time given by the user in the
     * command object. Return a prompt to the user for the ending time of the event.
     * The method catches DateTimeParseException and returns an error message.
     *
     * @param input The user input.
     */
    @Override
    public String processStartTime(String input) {
        try {
            LocalTime result = getParser().readTime(input);
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
            LocalTime result = getParser().readTime(input);
            // A typecast is required here because not all command objects have the getTaskType method.
            // The only command that will lead to this method being called is the AddCommand command.
            // Therefore, this is a safe typecast.
            AddCommand c = (AddCommand) currCommand;
            if (c.isInvalidEndTime(result)) {
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
     * Parses the user input using the Parser Object, and check if the given task number is valid.
     * If so, store the task number in the command object, call the command object's execute method and returns
     * its value.
     *
     * @param input The user input.
     * @param taskList The ColressTaskList to perform operations on.
     */
    @Override
    public String processTaskNumber(String input, TaskList taskList) {
        try {
            int[] result = getParser().getTaskNumber(input);
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
}
