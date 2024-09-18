package mryapper.parser;

import mryapper.command.*;
import mryapper.exception.InvalidSyntaxException;

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
    public static Command parse(String userInput) throws InvalidSyntaxException {
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
        default:
            throw new InvalidSyntaxException("Sorry, I'm not sure what you're trying to do :(");
        }
    }

    private static Command parseDeleteCommand(String[] processedInput) throws InvalidSyntaxException {
        try {
            if (processedInput.length <= 1) {
                throw new InvalidSyntaxException("You have to give me a valid task number!",
                        DeleteTask.SYNTAX);
            }

            int taskNumber = Integer.parseInt(processedInput[1]);
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
        try {
            if (processedInput.length <= 1) {
                throw new InvalidSyntaxException(
                        "You have to give me a valid task number!", MarkTask.SYNTAX);
            }

            int taskNumber = Integer.parseInt(processedInput[1]);
            return new MarkTask(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException(
                    "You have to give me a valid task number!", MarkTask.SYNTAX);
        }
    }

    private static Command parseUnmarkCommand(String[] processedInput) throws InvalidSyntaxException {
        try {
            if (processedInput.length <= 1) {
                throw new InvalidSyntaxException(
                        "You have to give me a valid task number!", UnmarkTask.SYNTAX);
            }

            int taskNumber = Integer.parseInt(processedInput[1]);
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
            throw new InvalidSyntaxException("I'll need you to format your details properly",
                    AddDeadline.SYNTAX);
        }

        String deadlineDescription = deadlineParams[0].trim();
        String deadlineTime = deadlineParams[1].trim();
        if (deadlineDescription.isEmpty()) {
            throw new InvalidSyntaxException("Your description cannot be empty!",
                    AddDeadline.SYNTAX);
        } else if (deadlineTime.isEmpty()) {
            throw new InvalidSyntaxException("Your deadline cannot be empty!",
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
            throw new InvalidSyntaxException("I'll need you to format your details properly",
                    AddEvent.SYNTAX);
        }
        String[] eventTimings = eventParams[1].trim().split("/to");
        if (eventTimings.length != 2) {
            throw new InvalidSyntaxException("I'll need you to format your details properly",
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
}
