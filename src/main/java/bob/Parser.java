package bob;

/**
 * The Parser class processes user commands and translates them into actions performed by the TaskList.
 * It handles input commands for task management and parses tasks from saved files.
 */
public class Parser {

    /**
     * Processes and interprets the user's input command, directing it to the appropriate method in TaskList.
     *
     * @param phrase The input command from the user.
     * @param taskList The list of tasks where actions like adding, marking, or deleting tasks are performed.
     * @param ui The UI instance used to display messages to the user.
     * @return The result message of the command execution.
     * @throws ChatBotException If an invalid command or format is provided, or if parsing fails.
     */
    public static String handleInput(String phrase, TaskList taskList, Ui ui) throws ChatBotException {
        String[] tmp = phrase.split(" ");
        switch (tmp[0]) {
        case "list":
            return taskList.listTasks();
        case "mark":
            return taskList.markTask(Integer.parseInt(tmp[1]) - 1, ui);
        case "unmark":
            return taskList.unmarkTask(Integer.parseInt(tmp[1]) - 1, ui);
        case "todo":
            return taskList.addToDo(phrase, ui);
        case "deadline":
            return taskList.addDeadline(phrase, ui);
        case "event":
            return taskList.addEvent(phrase, ui);
        case "fixed":
            return taskList.addFixed(phrase, ui);
        case "delete":
            return taskList.deleteTask(Integer.parseInt(tmp[1]) - 1, ui);
        case "on":
            return taskList.printTasksOnDate(tmp[1]);
        case "find":
            return taskList.searchKeyword(phrase, ui);
        default:
            throw new ChatBotException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses a task string from the saved file and converts it into a Task object.
     *
     * @param taskString The string representation of a task from the saved file.
     * @return The Task object created from the string.
     * @throws ChatBotException If the string format is incorrect or the task type is invalid.
     */
    public static Task parseTaskFromFile(String taskString) throws ChatBotException {
        String[] tmp = taskString.split(" \\| ");
        boolean isDone = !tmp[1].equals("0");

        if (!tmp[1].equals("0") && !tmp[1].equals("1")) {
            throw new ChatBotException("Corrupted task marking entry.");
        }

        switch (tmp[0]) {
        case "T":
            return new ToDo(tmp[2], isDone, TaskType.TODO);
        case "D":
            return new Deadline(tmp[2], isDone, tmp[3], TaskType.DEADLINE);
        case "E":
            return new Event(tmp[2], isDone, tmp[3], tmp[4], TaskType.EVENT);
        case "F":
            return new Fixed(tmp[2], isDone, Integer.parseInt(tmp[3]), TaskType.FIXED);
        default:
            throw new ChatBotException("I'm sorry, there was an error reading the file. Invalid task type.");
        }
    }
}
