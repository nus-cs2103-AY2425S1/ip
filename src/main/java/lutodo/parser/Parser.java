package lutodo.parser;

import lutodo.commands.*;

import static java.lang.Integer.parseInt;

public class Parser {

    private String command;

    public Parser(String command) {
        this.command = command;
    }

    public static String[] divideMessage(String message) {
        return message.trim().split("\\s+");
    }

    public static String[] splitTaskInfo(String message) {
        String trimmedMessage = message.trim();
        String[] taskDescription = trimmedMessage.split("\\s+", 2);
        return taskDescription;
    }


    /**
     * Parses the command according to the message.
     *
     * @param fullCommand the entire command message user inputs.
     * @return According command.
     */
    public static Command parse(String fullCommand) {
        if (fullCommand.isBlank()) {
            return new UnknownCommand(fullCommand);
        } else {
            String taskType = splitTaskInfo(fullCommand)[0];
            if(taskType.equalsIgnoreCase("bye")) {
                return new ExitCommand();
            } else if (taskType.equalsIgnoreCase("delete")) {
                int taskIndex = parseInt(divideMessage(fullCommand)[1]) - 1;
                return new DeleteCommand(taskIndex);
            } else if (taskType.equalsIgnoreCase("list")) {
                return new ShowListCommand();
            } else if (taskType.equalsIgnoreCase("mark")) {
                int taskIndex = parseInt(divideMessage(fullCommand)[1]) - 1;
                return new MarkCommand(taskIndex, true);
            } else if (taskType.equalsIgnoreCase("unmark")) {
                int taskIndex = parseInt(divideMessage(fullCommand)[1]) - 1;
                return new MarkCommand(taskIndex, false);
            } else if (taskType.equalsIgnoreCase("todo") ||
                    taskType.equalsIgnoreCase("deadline") ||
                    taskType.equalsIgnoreCase("event")) {
                return new AddCommand(fullCommand);
            } else if (taskType.equalsIgnoreCase("find")
            || taskType.equalsIgnoreCase("search")) {
                return new FindCommand(splitTaskInfo(fullCommand)[1]);
            } else {
                return new UnknownCommand(fullCommand);
            }
        }
    }
}
