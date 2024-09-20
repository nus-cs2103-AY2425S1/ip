package mryapper.parser;

import mryapper.command.AddDeadline;
import mryapper.command.AddEvent;
import mryapper.command.AddTodoTask;
import mryapper.command.Command;
import mryapper.command.DeleteTask;
import mryapper.command.EditTask;
import mryapper.command.FindTask;
import mryapper.command.ListTasks;
import mryapper.command.MarkTask;
import mryapper.command.SayGoodbye;
import mryapper.command.UnmarkTask;

import mryapper.exception.InvalidSyntaxException;
import mryapper.task.TaskField;

/**
 * Responsible for parsing the inputs sent from the user to the Chatbot.
 */
public class Parser {

    /**
     * Parses the string user input into a command.
     *
     * @param userInput The user input to be parsed.
     * @return The command to be executed based on the user input.
     * @throws InvalidSyntaxException If the input's syntax does not match any commands correctly.
     */
    public static Command parseInput(String userInput) throws InvalidSyntaxException {
        String[] processedInput = userInput.trim().split("\\s+", 2);
        String command = processedInput[0];

        switch (command) {
        case "bye":
            return new SayGoodbye();
        case "list":
            return new ListTasks();
        case "delete":
            return parseDeleteCommand(processedInput);
        case "find":
            return parseFindCommand(processedInput);
        case "mark":
            return parseMarkCommand(processedInput);
        case "unmark":
            return parseUnmarkCommand(processedInput);
        case "todo":
            return parseTodoTask(processedInput);
        case "deadline":
            return parseDeadlineTask(processedInput);
        case "event":
            return parseEventTask(processedInput);
        case "edit":
            return parseEditCommand(processedInput);
        default:
            throw new InvalidSyntaxException("Sorry, I'm not sure what you're trying to do :(");
        }
    }

    private static Command parseDeleteCommand(String[] processedInput) throws InvalidSyntaxException {
        if (processedInput.length <= 1) {
            throw new InvalidSyntaxException("You have to give me a valid task number!",
                    DeleteTask.SYNTAX);
        }

        try {
            int taskNumber = Integer.parseInt(processedInput[1]);
            isTaskNumberPositive(taskNumber);
            return new DeleteTask(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException("You have to give me a valid task number!",
                    DeleteTask.SYNTAX);
        }
    }

    private static Command parseFindCommand(String[] processedInput) throws InvalidSyntaxException {
        if (processedInput.length <= 1) {
            throw new InvalidSyntaxException("You need to enter a search input!", FindTask.SYNTAX);
        }

        return new FindTask(processedInput[1]);
    }

    private static Command parseMarkCommand(String[] processedInput) throws InvalidSyntaxException {
        if (processedInput.length <= 1) {
            throw new InvalidSyntaxException(
                    "You have to give me a valid task number!", MarkTask.SYNTAX);
        }

        try {
            int taskNumber = Integer.parseInt(processedInput[1]);
            isTaskNumberPositive(taskNumber);
            return new MarkTask(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException(
                    "You have to give me a valid task number!", MarkTask.SYNTAX);
        }
    }

    private static Command parseUnmarkCommand(String[] processedInput) throws InvalidSyntaxException {
        if (processedInput.length <= 1) {
            throw new InvalidSyntaxException(
                    "You have to give me a valid task number!", UnmarkTask.SYNTAX);
        }

        try {
            int taskNumber = Integer.parseInt(processedInput[1]);
            isTaskNumberPositive(taskNumber);
            return new UnmarkTask(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException(
                    "You have to give me a valid task number!", UnmarkTask.SYNTAX);
        }
    }

    private static Command parseTodoTask(String[] processedInput) throws InvalidSyntaxException {
        if (processedInput.length < 2) {
            throw new InvalidSyntaxException("You need to provide the task details!",
                    AddTodoTask.SYNTAX);
        }

        return new AddTodoTask(processedInput[1]);
    }

    private static Command parseDeadlineTask(String[] processedInput) throws InvalidSyntaxException {
        if (processedInput.length < 2) {
            throw new InvalidSyntaxException("You need to provide the task details!",
                    AddDeadline.SYNTAX);
        }

        String[] deadlineParams = processedInput[1].split("/by");
        if (deadlineParams.length != 2) {
            throw new InvalidSyntaxException("You need to provide a deadline!",
                    AddDeadline.SYNTAX);
        }

        String deadlineDescription = deadlineParams[0].trim();
        String deadlineTime = deadlineParams[1].trim();
        if (deadlineDescription.isEmpty()) {
            throw new InvalidSyntaxException("Your description cannot be empty!",
                    AddDeadline.SYNTAX);
        } else if (deadlineTime.isEmpty()) {
            throw new InvalidSyntaxException("You need to provide a deadline!",
                    AddDeadline.SYNTAX);
        }

        return new AddDeadline(deadlineDescription, deadlineTime);
    }

    private static Command parseEventTask(String[] processedInput) throws InvalidSyntaxException {
        if (processedInput.length < 2) {
            throw new InvalidSyntaxException("You need to provide the task details!",
                    AddEvent.SYNTAX);
        }

        String[] eventParams = processedInput[1].split("/from");
        if (eventParams.length != 2) {
            throw new InvalidSyntaxException("You need to provide a start time!",
                    AddEvent.SYNTAX);
        }
        String[] eventTimings = eventParams[1].trim().split("/to");
        if (eventTimings.length != 2) {
            throw new InvalidSyntaxException("You need to provide an end time!",
                    AddEvent.SYNTAX);
        }

        String eventDescription = eventParams[0].trim();
        String eventStart = eventTimings[0].trim();
        String eventEnd = eventTimings[1].trim();
        if (eventDescription.isEmpty()) {
            throw new InvalidSyntaxException("Your description cannot be empty!",
                    AddEvent.SYNTAX);
        } else if (eventStart.isEmpty()) {
            throw new InvalidSyntaxException("Your start time cannot be empty!",
                    AddEvent.SYNTAX);
        } else if (eventEnd.isEmpty()) {
            throw new InvalidSyntaxException("Your end time cannot be empty!",
                    AddEvent.SYNTAX);
        }

        return new AddEvent(eventDescription, eventStart, eventEnd);
    }

    private static Command parseEditCommand(String[] processedInput) throws InvalidSyntaxException {
        String[] params = verifyEditCommandSyntax(processedInput);
        int taskNumber = parseEditCommandTaskNumber(params[0]);
        TaskField field = parseForFieldToEdit(params[1]);

        String newString = params[2];
        if (isFieldADate(field)) {
            newString = DateTimeParser.parseDateTime(newString);
        }

        return new EditTask(taskNumber, field, newString);
    }

    private static String[] verifyEditCommandSyntax(
            String[] processedInput) throws InvalidSyntaxException {
        if (processedInput.length < 2) {
            throw new InvalidSyntaxException("You need to give me a valid task number"
                    + " followed by the edit parameters", EditTask.SYNTAX);
        }

        String[] params = processedInput[1].trim().split("\\s+", 3);
        if (params.length < 3) {
            throw new InvalidSyntaxException("You need to give me a valid task number"
                    + " followed by the edit parameters", EditTask.SYNTAX);
        }

        return params;
    }

    private static int parseEditCommandTaskNumber(String number) throws InvalidSyntaxException {
        try {
            int taskNumber = Integer.parseInt(number);
            isTaskNumberPositive(taskNumber);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException("You need to give me a valid task number" +
                    " followed by the edit parameters", EditTask.SYNTAX);
        }
    }

    private static TaskField parseForFieldToEdit(String field) throws InvalidSyntaxException {
        String possibleFields = "Possible parameters: /description, /by, /from, /to";

        switch (field) {
        case "/description":
            return TaskField.DESCRIPTION;
        case "/by":
            return TaskField.DEADLINE;
        case "/from":
            return TaskField.START_TIME;
        case "/to":
            return TaskField.END_TIME;
        default:
            throw new InvalidSyntaxException("There is no such parameter to edit in any task",
                    possibleFields);
        }
    }

    private static void isTaskNumberPositive(int taskNumber) {
        if (taskNumber <= 0) {
            throw new IllegalArgumentException("Task number should be a positive integer");
        }
    }

    private static boolean isFieldADate(TaskField field) {
        return field != TaskField.DESCRIPTION;
    }
}
