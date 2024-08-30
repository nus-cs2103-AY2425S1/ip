package duke;

import java.util.ArrayList;

/**
 * Represents a command issued by the user.
 * It handles various commands such as adding, marking,
 * deleting tasks, and handling custom commands.
 */
public class Command {

    private static final String INDENT = "      ";
    private static final String SEPARATOR = "------------------------------";
    private String command;
    private String message;

    /**
     * Constructs a {@code Command} object with the specified command and message.
     *
     * @param command The command to be executed (e.g., "bye", "list", "mark", etc.).
     * @param message The full message provided by the user, which may contain additional information.
     */
    public Command(String command, String message) {
        this.command = command;
        this.message = message;
    }

    /**
     * Executes the command on the given {@code TaskList},
     * using the provided {@code Ui} and {@code Storage} objects.
     * The command could be one of several options, such as listing tasks, marking tasks as done,
     * deleting tasks, or processing specific task commands like "todo", "deadline", or "event".
     *
     * @param tasks    The {@code TaskList} object that contains all the tasks.
     * @param ui       The {@code Ui} object for displaying messages to the user.
     * @param storage  The {@code Storage} object for saving the task list to a file.
     * @return The result of executing the command as a {@code String}.
     * @throws MentalHealthException If an error occurs while processing the command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MentalHealthException {
        String message = this.message.strip().toLowerCase();
        String[] parts = message.split(" ");

        switch (this.command) {
        case "bye":
            return "Goodbye";
        case "list":
            return tasks.getTasks(tasks.getListTask());
        case "mark":
            return handleMarkCommand(parts, tasks, storage);
        case "delete":
            return handleDeleteCommand(parts, tasks, storage);
        case "find":
            return handleFindCommand(parts, tasks);
        default:
            if (!this.message.isEmpty()) {
                return processCustomCommand(tasks, storage);
            }
        }
        return "Unknown message";
    }

    /**
     * Marks the task as either done or not done.
     * If it is done, it is shown as "X".
     *
     * @param parts    The parts of the command, containing the task number and type (mark/unmark).
     * @param tasks    The {@code TaskList} object that contains all the tasks.
     * @param storage  The {@code Storage} object for saving the task list to a file.
     * @return A message indicating the result of the mark/unmark command.
     * @throws MentalHealthException If an error occurs while processing the command.
     */
    private String handleMarkCommand(String[] parts, TaskList tasks, Storage storage)
            throws MentalHealthException {
        StringBuilder result = new StringBuilder();

        if (parts.length > 1) {
            int number = Integer.parseInt(parts[parts.length - 1]);
            String checkMarkOrUnmark = parts[0];
            result.append("Extracted Task number: ").append(number).append("\n");
            IndividualTask curTask = tasks.getListTask().get(number - 1);

            if (checkMarkOrUnmark.equals("mark")) {
                curTask.markOrUnmark("mark");
                result.append(INDENT)
                        .append("Okays! I've marked this task as done:\n")
                        .append(formatMessage(curTask, tasks.getListTask().size()))
                        .append("\n");
            } else if (checkMarkOrUnmark.equals("unmark")) {
                curTask.markOrUnmark("unmark");
                result.append(INDENT)
                        .append("Okay! I've marked this task as not done:\n")
                        .append(formatMessage(curTask, tasks.getListTask().size()))
                        .append("\n");
            } else {
                result.append("Not a valid command.\n");
            }
        } else {
            result.append("No Task found after 'mark'.\n");
        }

        storage.saveTasksToFile(tasks.getListTask());

        return result.toString();
    }

    /**
     * Deletes the task from the {@code TaskList}.
     *
     * @param parts    The parts of the command, containing the task number.
     * @param tasks    The {@code TaskList} object that contains all the tasks.
     * @param storage  The {@code Storage} object for saving the task list to a file.
     * @return A message indicating the result of the delete command.
     */
    private String handleDeleteCommand(String[] parts, TaskList tasks, Storage storage) {
        StringBuilder result = new StringBuilder();

        if (parts.length > 1 && parts[0].equals("delete")) {
            int number = Integer.parseInt(parts[parts.length - 1]);
            result.append("Extracted Task number: ").append(number).append("\n");
            IndividualTask curTask = tasks.getListTask().get(number - 1);
            tasks.deleteTask(number - 1);
            result.append(INDENT)
                    .append("Alrighty! I will remove the task:\n")
                    .append(formatMessage(curTask, tasks.getListTask().size()))
                    .append("\n");
        } else {
            result.append("No Task found.\n");
        }

        storage.saveTasksToFile(tasks.getListTask());

        return result.toString();
    }

    /**
     * Finds the tasks that contain the specified keyword.
     *
     * @param parts The parts of the command, containing the keyword to search for.
     * @param tasks The {@code TaskList} object that contains all the tasks.
     * @return A message listing the tasks that match the keyword.
     */
    private String handleFindCommand(String[] parts, TaskList tasks) {
        StringBuilder result = new StringBuilder();

        String keyWord = parts[parts.length - 1];
        ArrayList<IndividualTask> allTasks = tasks.getListTask();
        ArrayList<IndividualTask> matchingTasks = new ArrayList<>();
        for (IndividualTask task : allTasks) {
            if (task.getTaskDescription().toLowerCase().contains(keyWord.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (!matchingTasks.isEmpty()) {
            result.append(INDENT).append("Here are your matching tasks!\n");
            result.append(tasks.getTasks(matchingTasks));
        } else {
            result.append(INDENT).append("No matching tasks found.\n");
        }

        return result.toString();
    }

    /**
     * Processes custom commands that do not match predefined commands like "mark", "delete", etc.
     *
     * @param tasks    The {@code TaskList} object that contains all the tasks.
     * @param storage  The {@code Storage} object for saving the task list to a file.
     * @return The result of processing the custom command as a {@code String}.
     */
    private String processCustomCommand(TaskList tasks, Storage storage) {
        try {
            return processMessage(this.message, tasks, storage);
        } catch (MentalHealthException e) {
            StringBuilder result = new StringBuilder();
            result.append(INDENT).append(SEPARATOR).append("\n")
                    .append(INDENT).append("OOPS!!! ").append(e.getMessage()).append("\n")
                    .append(INDENT).append(SEPARATOR);

            return result.toString();
        }
    }

    /**
     * Processes specific task-related commands such as "todo", "deadline", and "event".
     * This method parses the message to extract task details
     * and then creates and adds the appropriate task to the {@code TaskList}.
     *
     * @param msg      The message containing the command and task details.
     * @param tasks    The {@code TaskList} object to which the new task will be added.
     * @param storage  The {@code Storage} object for saving the updated task list to a file.
     * @return The result of processing the task-related command as a {@code String}.
     * @throws MentalHealthException If an error occurs while processing the task command.
     */
    public String processMessage(String msg, TaskList tasks, Storage storage) throws MentalHealthException {
        StringBuilder result = new StringBuilder();

        String[] message = msg.split(" ");
        String commandType = message[0].toLowerCase();

        switch (CommandType.valueOf(commandType.toUpperCase())) {
        case TODO:
            result.append(processTodoCommand(message, tasks));
            break;
        case DEADLINE:
            result.append(processDeadlineCommand(message, tasks));
            break;
        case EVENT:
            result.append(processEventCommand(message, tasks));
            break;
        default:
            throw new MentalHealthException("I'm sorry, but I don't know what that means :-(");
        }
        storage.saveTasksToFile(tasks.getListTask());

        return result.toString();
    }

    /**
     * Processes "todo" commands.
     *
     * @param message  The message containing the command and task details.
     * @param tasks    The {@code TaskList} object to which the new task will be added.
     * @return The result of processing the "todo" command as a {@code String}.
     * @throws MentalHealthException If an error occurs while processing the task command.
     */
    private String processTodoCommand(String[] message, TaskList tasks) throws MentalHealthException {
        StringBuilder result = new StringBuilder();

        if (message.length < 2 || message[1].trim().isEmpty()) {
            throw new MentalHealthException("The description of a todo cannot be empty.");
        }
        String type = "todo";
        String todo = this.message.substring(type.length()).trim();
        ToDo newTodo = new ToDo(todo);
        tasks.addTask(newTodo);
        result.append(INDENT)
                .append("Okays! I've added this task:\n")
                .append(formatMessage(newTodo, tasks.getListTask().size()))
                .append("\n");

        return result.toString();
    }

    /**
     * Processes "deadline" commands.
     *
     * @param message  The message containing the command and task details.
     * @param tasks    The {@code TaskList} object to which the new task will be added.
     * @return The result of processing the "deadline" command as a {@code String}.
     * @throws MentalHealthException If an error occurs while processing the task command.
     */
    private String processDeadlineCommand(String[] message, TaskList tasks) throws MentalHealthException {
        StringBuilder result = new StringBuilder();

        String[] parts = this.message.split(" /by ", 2);
        if (parts.length == 2) {
            String type = "deadline";
            String description = parts[0].substring(type.length()).trim();
            String by = parts[1].trim();
            Deadline newDeadline = new Deadline(description, by);
            tasks.addTask(newDeadline);
            result.append(INDENT)
                    .append("Okays! I've added this task:\n")
                    .append(formatMessage(newDeadline, tasks.getListTask().size()))
                    .append("\n");
        } else {
            throw new MentalHealthException("The string doesn't contain the expected format for a deadline.");
        }

        return result.toString();
    }

    /**
     * Processes "event" commands.
     *
     * @param message  The message containing the command and task details.
     * @param tasks    The {@code TaskList} object to which the new task will be added.
     * @return The result of processing the "event" command as a {@code String}.
     * @throws MentalHealthException If an error occurs while processing the task command.
     */
    private String processEventCommand(String[] message, TaskList tasks) throws MentalHealthException {
        StringBuilder result = new StringBuilder();

        String[] parts = this.message.split(" /from ", 2);
        if (parts.length == 2) {
            String type = "event";
            String description = parts[0].substring(type.length()).trim();
            String[] secondPart = parts[1].split(" /to ", 2);
            if (secondPart.length == 2) {
                String from = secondPart[0].trim();
                String to = secondPart[1].trim();
                Event newEvent = new Event(description, from, to);
                tasks.addTask(newEvent);
                result.append(INDENT)
                        .append("Okays! I've added this task:\n")
                        .append(formatMessage(newEvent, tasks.getListTask().size()))
                        .append("\n");
            } else {
                throw new MentalHealthException("The string doesn't contain the '/to' part.");
            }
        } else {
            throw new MentalHealthException("The string doesn't contain the '/from' part.");
        }

        return result.toString();
    }

    /**
     * Checks if the command is an exit command.
     * The exit command is identified as "bye".
     *
     * @return true if the command is "bye", false otherwise.
     */
    public boolean isExit() {
        return this.command.equals("bye");
    }

    /**
     * Formats a task message for display, including the task's number in the list and the task details.
     * The formatted message is surrounded by a separator for clarity.
     *
     * @param task The task to be formatted.
     * @param num  The number of tasks currently in the list.
     * @return The formatted task message.
     */
    public String formatMessage(IndividualTask task, int num) {
        return INDENT + SEPARATOR + "\n" + INDENT + task + "\n"
                + INDENT + "Now you have " + num + " tasks in the list.\n"
                + INDENT + SEPARATOR;
    }
}
