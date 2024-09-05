package james;

import java.time.LocalDateTime;

/**
 * Handles parsing and executing user commands.
 * <p>
 * The Parser class interprets user input, performs the necessary actions
 * on the TaskList and Storage, and interacts with the Ui
 * to provide feedback to the user.
 * </p>
 */
class Parser {
    private Ui ui;
    private Storage storage;

    /**
     * Creates a new Parser instance.
     *
     * @param ui The Ui instance used for user interactions
     * @param storage The Storage instance used for loading and saving tasks
     */
    public Parser(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses the user command and executes the appropriate action.
     *
     * @param command The user command to be parsed and executed
     * @param taskList The TaskList instance to modify based on the command
     * @return true if the command is "bye" and the application should exit, false otherwise
     * @throws JamesException If an error occurs during command execution
     */
    public String parseAndExecute(String command, TaskList taskList) throws JamesException {
        String[] words = command.split(" ");
        String action = words[0].toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();

        switch (action) {
        case "bye":
            return ui.showExitMessage();

        case "list":
            return taskList.printTasks();

        case "mark":
            int markTaskNum = Integer.parseInt(words[1]);
            taskList.markTask(markTaskNum - 1);
            stringBuilder.append("Task marked as done:\n");
            stringBuilder.append(taskList.getTask(markTaskNum - 1).printTask());
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();

        case "unmark":
            int unmarkTaskNum = Integer.parseInt(words[1]);
            taskList.unmarkTask(unmarkTaskNum - 1);
            stringBuilder.append("Task marked as not done:\n");
            stringBuilder.append(taskList.getTask(unmarkTaskNum - 1).printTask());
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();

        case "todo":
            String todoDescription = command.substring(4).trim();
            Task todoTask = new Todo(todoDescription, false);
            taskList.addTask(todoTask);
            stringBuilder.append("Task added:\n" + todoTask.printTask() + "\n");
            stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();

        case "deadline":
            String deadlineDescription = command.substring(8, command.lastIndexOf("/by")).trim();
            String deadline = command.substring(command.lastIndexOf("/by") + 4).trim();
            Task deadlineTask = new Deadline(deadlineDescription, false, LocalDateTime.parse(deadline));
            taskList.addTask(deadlineTask);
            stringBuilder.append("Task added:\n" + deadlineTask.printTask() + "\n");
            stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();

        case "event":
            String eventDescription = command.substring(5, command.lastIndexOf("/from")).trim();
            String start = command.substring(command.lastIndexOf("/from") + 6,
                    command.lastIndexOf("/to")).trim();
            String end = command.substring(command.lastIndexOf("/to") + 4).trim();
            Task eventTask = new Event(eventDescription, false, LocalDateTime.parse(start),
                    LocalDateTime.parse(end));
            taskList.addTask(eventTask);
            stringBuilder.append("Task added:\n" + eventTask.printTask() + "\n");
            stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();

        case "delete":
            int deleteTaskNum = Integer.parseInt(words[1]);
            stringBuilder.append("Task removed:\n" + taskList.getTask(deleteTaskNum - 1).printTask() + "\n");
            taskList.deleteTask(deleteTaskNum - 1);
            stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();

        case "find":
            String keyWord = words[1];
            stringBuilder.append("Here are the matching tasks in your list:\n");
            stringBuilder.append(taskList.printMatchingTasks(keyWord));
            return stringBuilder.toString();

        default:
            throw new CommandNotFoundException("Sorry! I don't understand what you mean by (" + command + "). " +
                    "Please try a different command!");
        }
    }
}
