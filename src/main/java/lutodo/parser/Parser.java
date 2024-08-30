package lutodo.parser;

import lutodo.commands.Command;
import lutodo.commands.AddCommand;
import lutodo.commands.DeleteCommand;
import lutodo.commands.ShowListCommand;
import lutodo.commands.ExitCommand;
import lutodo.commands.UnknownCommand;
import lutodo.commands.MarkCommand;

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
        return message.trim().split("\\s+", 2);
    }

    public static Command parse(String fullCommand) {
        if (fullCommand.isBlank()) {
            return new UnknownCommand(fullCommand);
        } else {
            String taskType = splitTaskInfo(fullCommand)[0];
            if(taskType.equalsIgnoreCase("bye")) {
                return new ExitCommand();
            } else if (taskType.equalsIgnoreCase("delete")) {
                try {
                    int taskIndex = parseInt(divideMessage(fullCommand)[1]) - 1;
                    return new DeleteCommand(taskIndex);
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("The task you want to delete is not in task list, please try again.");
                }
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
            } else {
                return new UnknownCommand(fullCommand);
            }
        }
        return new UnknownCommand(fullCommand);
    }
}
