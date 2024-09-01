package bob;

/**
 * The Parser class handles user input and parses commands.
 * It directs commands to the appropriate methods for task creation, modification, or other actions.
 */
public class Parser {

    /**
     * Parses and handles the user's input command, directing it to the appropriate method in TaskList.
     *
     * @param phrase The input command from the user.
     * @param taskList The list of tasks where actions like adding, marking, or deleting are performed.
     * @param ui The UI instance used to display messages to the user.
     * @throws ChatBotException If an invalid command or format is provided.
     */
    public static void handleInput(String phrase, TaskList taskList, Ui ui) throws ChatBotException {
        String[] tmp = phrase.split(" ");
        switch (tmp[0]) {
        case "list":
            taskList.listTasks(ui);
            break;
        case "mark":
            taskList.markTask(Integer.parseInt(tmp[1]) - 1, ui);
            break;
        case "unmark":
            taskList.unmarkTask(Integer.parseInt(tmp[1]) - 1, ui);
            break;
        case "todo":
            taskList.addToDo(phrase, ui);
            break;
        case "deadline":
            taskList.addDeadline(phrase, ui);
            break;
        case "event":
            taskList.addEvent(phrase, ui);
            break;
        case "delete":
            taskList.deleteTask(Integer.parseInt(tmp[1]) - 1, ui);
            break;
        case "on":
            taskList.printTasksOnDate(tmp[1], ui);
            break;
        case "find":
            taskList.searchKeyword(phrase, ui);
            break;
        default:
            throw new ChatBotException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses a task from the string representation in the saved file and converts it back into a Task object.
     *
     * @param taskString The string representation of a task in the saved file.
     * @return The Task object created from the string.
     * @throws ChatBotException If the string is corrupted or the task type is invalid.
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
        default:
            throw new ChatBotException("I'm sorry, there was an error reading the file. Invalid task Type.");
        }
    }
}
