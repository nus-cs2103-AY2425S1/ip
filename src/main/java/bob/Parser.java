package bob;

/**
 * Handles parsing and processing of user inputs and tasks for the Bob chatbot.
 * The Parser class translates user commands into actions that modify or display tasks.
 */
public class Parser {

    /**
     * Processes the user input and executes the corresponding task-related command.
     * This method handles commands such as listing tasks, marking tasks, adding todos, deadlines, and events.
     *
     * @param phrase The user input phrase that specifies a command and any associated arguments.
     * @param taskList The TaskList object that manages the list of tasks.
     * @param ui The Ui object that handles interactions with the user.
     * @throws ChatBotException If the command is unrecognized or invalid.
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
            default:
                throw new ChatBotException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses a task from the file and returns it as a Task object.
     * The task is represented as a string formatted in the format used for storage.
     *
     * @param taskString The string representation of the task from the storage file.
     * @return The corresponding Task object (ToDo, Deadline, or Event) based on the task data.
     * @throws ChatBotException If the task data is corrupted or invalid.
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
