package CommandLine;

import Task.TaskList;
import Task.Task;

import static java.lang.Integer.parseInt;

public class Parser {
    public Parser() {

    };

    /**
     * Parses the input supplied by user, adds it to the task list if possible.
     * This method helps to parse inputs, calling the appropriate commands where possible.
     *
     * @param  taskList the current taskList
     * @param  inputTokens the string that was parsed
     */
    public static String parse(TaskList taskList, String inputTokens) {
        Line line = new Line();
        String[] parts = inputTokens.split(" ", 2);
        String command = parts[0].toLowerCase();
        switch(command) {
        case "bye":
            NotAGPTExit();
            return "Goodbye";
        case "list":
            return taskList.list();
        case "mark":
            if (parts.length > 1) {
                String idx = parts[1];
                return taskList.markAsDone(idx);
            } else {
                return "Enter a task number";
            }
        case "unmark":
            if (parts.length > 1) {
                String idx = parts[1];
                return taskList.markAsUndone(idx);
            } else {
                return "Enter a task number";
            }
        case "todo":
            if (parts.length > 1) {
                return taskList.add(parts[1], Task.TaskType.T);
            } else {
                return "Enter a name for the To Do Task";
            }
        case "deadline":
            if (parts.length > 1) {
                return taskList.add(parts[1], Task.TaskType.D);
            } else {
                return "Incomplete command. Enter a deadline";
            }
        case "event":
            if (parts.length > 1) {
                return taskList.add(parts[1], Task.TaskType.E);
            } else {
                return "Incomplete command. Enter a start and end time";
            }
        case "delete":
            if (parts.length == 2) {
                try {
                    int idx = parseInt(parts[1]);
                    return taskList.delete(idx);
                } catch (NumberFormatException e) {
                    return "Enter a valid index to delete";
                }
            } else {
                return "Enter a valid argument";
            }
        case "find":
            if (parts.length == 2) {
                String word = parts[1];
                return taskList.find(word);
            } else {
                return "Enter a word";
            }
        default:
            return "Unknown command, type help for a list of available commands";

        }
    }

    /**
     * Exits the program.
     */
    public static void NotAGPTExit() {
        Line line = new Line();
        line.drawLine();
        System.out.println("Bye. Hope to see you again soon!");
        line.drawLine();
        System.exit(0);
    }
}
