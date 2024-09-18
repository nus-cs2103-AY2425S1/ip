package mryapper.parser;

import mryapper.command.*;
import mryapper.exception.IllegalTaskException;
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
     * @throws IllegalTaskException If the user attempts to create a task without valid parameters.
     * @throws InvalidSyntaxException If the input's syntax does not match any commands correctly.
     */
    public static Command parse(String userInput) throws IllegalTaskException, InvalidSyntaxException {
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
            throw new InvalidSyntaxException("Hmm... I'm not sure what you're trying to do :(");
        }
    }

    private static Command parseDeleteCommand(String[] processedInput) throws InvalidSyntaxException {
        try {
            if (processedInput.length <= 1) {
                throw new InvalidSyntaxException("You have to give me a valid task number!\n"
                        + "e.g. delete 2");
            }

            int taskNumber = Integer.parseInt(processedInput[1]);
            return new DeleteTask(taskNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "You have to give me a valid task number!\n e.g. delete 2");
        }
    }

    private static Command parseFindCommand(String[] processedInput) throws InvalidSyntaxException {
        if (processedInput.length <= 1) {
            throw new InvalidSyntaxException("You need to enter a search input!\n"
                    + "e.g. find do project");
        }
        return new FindTask(processedInput[1]);
    }

    private static Command parseMarkCommand(String[] processedInput) throws InvalidSyntaxException {
        try {
            if (processedInput.length <= 1) {
                throw new InvalidSyntaxException(
                        "You have to give me a valid task number!\n e.g. mark 2");
            }

            int taskNumber = Integer.parseInt(processedInput[1]);
            return new MarkTask(taskNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "You have to give me a valid task number!\n e.g. mark 2");
        }
    }

    private static Command parseUnmarkCommand(String[] processedInput) throws InvalidSyntaxException {
        try {
            if (processedInput.length <= 1) {
                throw new InvalidSyntaxException(
                        "You have to give me a valid task number!\n e.g. unmark 2");
            }

            int taskNumber = Integer.parseInt(processedInput[1]);
            return new UnmarkTask(taskNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "You have to give me a valid task number!\n e.g. unmark 2");
        }
    }

    private static Command parseTodoTask(String[] processedInput) throws IllegalTaskException {
        if (processedInput.length < 2) {
            throw new IllegalTaskException(processedInput[0], "You need to provide the task details!");
        }

        return new AddTodoTask(processedInput[1]);
    }

    private static Command parseDeadlineTask(String[] processedInput) throws IllegalTaskException {
        if (processedInput.length < 2) {
            throw new IllegalTaskException(processedInput[0], "You need to provide the task details!");
        }

        String[] deadlineParams = processedInput[1].split("/by");
        if (deadlineParams.length != 2) {
            throw new IllegalTaskException("deadline",
                    "I'll need you to format your details properly");
        }

        String deadlineDescription = deadlineParams[0].trim();
        String deadlineTime = deadlineParams[1].trim();
        if (deadlineDescription.isEmpty()) {
            throw new IllegalTaskException("deadline", "Your description cannot be empty!");
        } else if (deadlineTime.isEmpty()) {
            throw new IllegalTaskException("deadline", "Your deadline cannot be empty!");
        }

        return new AddDeadline(deadlineDescription, deadlineTime);
    }

    private static Command parseEventTask(String[] processedInput) throws IllegalTaskException {
        if (processedInput.length < 2) {
            throw new IllegalTaskException(processedInput[0], "You need to provide the task details!");
        }

        String[] eventParams = processedInput[1].split("/from");
        if (eventParams.length != 2) {
            throw new IllegalTaskException("event",
                    "I'll need you to format your details properly");
        }
        String[] eventTimings = eventParams[1].trim().split("/to");
        if (eventTimings.length != 2) {
            throw new IllegalTaskException("event",
                    "I'll need you to format your details properly");
        }

        String eventDescription = eventParams[0].trim();
        String eventStart = eventTimings[0].trim();
        String eventEnd = eventTimings[1].trim();
        if (eventDescription.isEmpty()) {
            throw new IllegalTaskException("event",
                    "Your description cannot be empty!");
        } else if (eventStart.isEmpty()) {
            throw new IllegalTaskException("event",
                    "Your start time cannot be empty!");
        } else if (eventEnd.isEmpty()) {
            throw new IllegalTaskException("event",
                    "Your end time cannot be empty!");
        }

        return new AddEvent(eventDescription, eventStart, eventEnd);
    }
}
