package mryapper.parser;

import mryapper.command.Command;
import mryapper.command.CommandList;
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
            return CommandList.bye();
        case "list":
            return CommandList.listTasks();
        case "delete":
            try {
                if (processedInput.length <= 1) {
                    throw new InvalidSyntaxException("You have to give me a valid task number!\n"
                            + "e.g. delete 2");
                } else {
                    int taskNumber = Integer.parseInt(processedInput[1]);
                    return CommandList.deleteTask(taskNumber);
                }
            } catch (NumberFormatException e) {
                // if the string after the mark command is not an integer
                throw new IllegalArgumentException(
                        "You have to give me a valid task number!\n e.g. delete 2");
            }
        case "find":
            if (processedInput.length <= 1) {
                throw new InvalidSyntaxException("You need to enter a search input!\n"
                        + "e.g. find do project");
            } else {
                return CommandList.findTask(processedInput[1]);
            }
        case "mark":
            try {
                if (processedInput.length <= 1) {
                    throw new InvalidSyntaxException(
                            "You have to give me a valid task number!\n e.g. mark 2");
                } else {
                    int taskNumber = Integer.parseInt(processedInput[1]);
                    return CommandList.markTask(taskNumber);
                }
            } catch (NumberFormatException e) {
                // if the string after the mark command is not an integer
                throw new IllegalArgumentException(
                        "You have to give me a valid task number!\n e.g. mark 2");
            }
        case "unmark":
            try {
                if (processedInput.length <= 1) {
                    throw new InvalidSyntaxException(
                            "You have to give me a valid task number!\n e.g. unmark 2");
                } else {
                    int taskNumber = Integer.parseInt(processedInput[1]);
                    return CommandList.unmarkTask(taskNumber);
                }
            } catch (NumberFormatException e) {
                // if the string after the mark command is not an integer
                throw new IllegalArgumentException(
                        "You have to give me a valid task number!\n e.g. unmark 2");
            }
        case "todo":
            if (processedInput.length < 2) {
                throw new IllegalTaskException(command, "You need to provide the task details!");
            }
            return CommandList.addTodoTask(processedInput[1]);
        case "deadline":
            if (processedInput.length < 2) {
                throw new IllegalTaskException(command, "You need to provide the task details!");
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

            return CommandList.addDeadlineTask(deadlineDescription, deadlineTime);
        case "event":
            if (processedInput.length < 2) {
                throw new IllegalTaskException(command, "You need to provide the task details!");
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

            return CommandList.addEventTask(eventDescription, eventStart, eventEnd);
        default:
            throw new InvalidSyntaxException("Hmm... I'm not sure what you're trying to do :(");
        }
    }
}
