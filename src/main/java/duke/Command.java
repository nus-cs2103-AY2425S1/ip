package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a command issued by the user.
 * It handles various commands such as adding, marking,
 * deleting tasks, and handling custom commands.
 */
public class Command {

    private static final String ERROR_MESSAGE_UNKNOWN_COMMAND = "I'm sorry, but I don't know what that means :-(";
    private final String command;
    private final String message;

    /**
     * Constructs a {@code Command} object with the specified command and message.
     *
     * @param command The command to be executed (e.g., "bye", "list", "mark", etc.).
     * @param message The full message provided by the user, which may contain additional information.
     */
    public Command(String command, String message) {
        assert command != null && !command.isEmpty() : "Command should not be null or empty";
        assert message != null : "Message should not be null";
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

        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        String message = this.message.strip().toLowerCase();
        String[] messageParts = message.split(" ");

        switch (this.command) {
        case "bye":
            return "Goodbye";
        case "list":
            return tasks.getTasks(tasks.getListTask());
        case "mark":
            return handleMarkCommand(messageParts, tasks, storage);
        case "delete":
            return handleDeleteCommand(messageParts, tasks, storage);
        case "find":
            return handleFindCommand(messageParts, tasks);
        case "update":
            return handleUpdateCommand(messageParts, tasks, storage);
        default:
            return handleUnknownCommand(tasks, storage);
        }
    }


    /**
     * Marks the task as either done or not done.
     * If it is done, it is shown as "X".
     *
     * @param parts    The parts of the command, containing the task number and type (mark/unmark).
     * @param tasks    The {@code TaskList} object that contains all the tasks.
     * @param storage  The {@code Storage} object for saving the task list to a file.
     * @return A message indicating the result of the mark/unmark command.
     */
    private String handleMarkCommand(String[] parts, TaskList tasks, Storage storage) {
        if (parts.length <= 1) {
            return "No Task found after 'mark'.";
        }

        int index = Integer.parseInt(parts[parts.length - 1]);
        String checkMarkOrUnmark = parts[0];
        IndividualTask currentTask = tasks.getListTask().get(index - 1);

        return markOrUnmarkTask(tasks, storage, checkMarkOrUnmark, currentTask);
    }
    /**
     * Marks or unmarks a given task as done or not done based on the command ("mark" or "unmark").
     * Saves the updated task list to storage after marking/unmarking.
     *
     * @param tasks             The {@code TaskList} object containing all tasks.
     * @param storage           The {@code Storage} object for saving the task list to a file.
     * @param checkMarkOrUnmark A {@code String} indicating whether to mark ("mark") or unmark ("unmark") the task.
     * @param currentTask           The {@code IndividualTask} object to be marked/unmarked.
     * @return A {@code String} message indicating the result of the mark/unmark operation.
     */
    private String markOrUnmarkTask(TaskList tasks, Storage storage, String checkMarkOrUnmark,
                                    IndividualTask currentTask) {
        StringBuilder result = new StringBuilder();
        switch (checkMarkOrUnmark) {
        case "mark":
            currentTask.setMarkStatus("mark");
            result.append("Okays! I've marked this task as done:\n")
                    .append(formatMessage(currentTask, tasks.getListTask().size()));
            break;
        case "unmark":
            currentTask.setMarkStatus("unmark");
            result.append("Okay! I've marked this task as not done:\n")
                    .append(formatMessage(currentTask, tasks.getListTask().size()));
            break;
        default:
            result.append("Not a valid command.");
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

        assert parts.length > 1 : "Delete command should have at least 2 parts (delete + task number)";

        int number = Integer.parseInt(parts[parts.length - 1]);

        // Ensure the task number is valid
        assert number > 0 && number <= tasks.getListTask().size() : "Task number out of bounds";
        IndividualTask currentTask = tasks.getListTask().get(number - 1);
        tasks.deleteTask(number - 1);
        storage.saveTasksToFile(tasks.getListTask());
        return "Alrighty! I will remove the task:\n" + formatMessage(currentTask, tasks.getListTask().size());
    }

    private String handleUpdateCommand(String[] parts, TaskList tasks, Storage storage) throws MentalHealthException {
        if (parts.length <= 1) {
            return "No Task found.";
        }

        int number = Integer.parseInt(parts[1]);

        // Ensure the task number is valid
        assert number > 0 && number <= tasks.getListTask().size() : "Task number out of bounds";

        IndividualTask currentTask = tasks.getListTask().get(number - 1);

        // Extract the specific field to update (e.g., "description", "time")
        String fieldToUpdate = parts[2]; // Example: "description", "by", "from", "to"
        String newValue = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));

        handleUpdateTask(currentTask, fieldToUpdate, newValue);

        storage.saveTasksToFile(tasks.getListTask());

        return "Alrighty! I have updated this task:\n" + formatMessage(currentTask, tasks.getListTask().size());
    }

    private void handleUpdateTask(IndividualTask currentTask, String fieldToUpdate, String newValue)
            throws MentalHealthException {
        if (currentTask instanceof ToDo) {
            updateToDoTask((ToDo) currentTask, fieldToUpdate, newValue);
        } else if (currentTask instanceof Deadline) {
            updateDeadlineTask((Deadline) currentTask, fieldToUpdate, newValue);
        } else if (currentTask instanceof Event) {
            updateEventTask((Event) currentTask, fieldToUpdate, newValue);
        } else {
            throw new MentalHealthException("Unknown task type");
        }
    }

    private void updateToDoTask(ToDo todoTask, String fieldToUpdate, String newValue)
            throws MentalHealthException {
        if ("/description".equalsIgnoreCase(fieldToUpdate)) {
            todoTask.setTaskDescription(newValue);
        } else {
            throw new MentalHealthException("Does not have relevant field");
        }
    }

    private void updateDeadlineTask(Deadline deadlineTask, String fieldToUpdate, String newValue)
            throws MentalHealthException {
        if ("/description".equalsIgnoreCase(fieldToUpdate)) {
            deadlineTask.setTaskDescription(newValue);
        } else if ("/by".equalsIgnoreCase(fieldToUpdate)) {
            deadlineTask.setReturnBy(newValue);
        } else {
            throw new MentalHealthException("Does not have relevant field");
        }
    }

    private void updateEventTask(Event eventTask, String fieldToUpdate, String newValue)
            throws MentalHealthException {
        if ("/description".equalsIgnoreCase(fieldToUpdate)) {
            eventTask.setTaskDescription(newValue);
        } else if ("/from".equalsIgnoreCase(fieldToUpdate)) {
            eventTask.setFrom(newValue);
        } else if ("/to".equalsIgnoreCase(fieldToUpdate)) {
            eventTask.setTo(newValue);
        } else {
            throw new MentalHealthException("Does not have relevant field");
        }
    }

    /**
     * Finds the tasks that contain the specified keyword.
     *
     * @param parts The parts of the command, containing the keyword to search for.
     * @param tasks The {@code TaskList} object that contains all the tasks.
     * @return A message listing the tasks that match the keyword.
     */
    private String handleFindCommand(String[] parts, TaskList tasks) {

        assert parts.length > 1 : "Find command should have at least 2 parts (find + keyword)";

        String keyword = parts[parts.length - 1];
        ArrayList<IndividualTask> matchingTasks = findMatchingTasks(tasks, keyword);

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }

        return "Here are your matching tasks!\n" + tasks.getTasks(matchingTasks);
    }

    /**
     * Finds tasks in the task list that match the given keyword. A task matches if its
     * description contains the keyword (case-insensitive).
     *
     * @param tasks   The {@code TaskList} object containing all tasks.
     * @param keyWord The keyword to search for within task descriptions.
     * @return A list of {@code IndividualTask} objects that match the keyword.
     */
    private ArrayList<IndividualTask> findMatchingTasks(TaskList tasks, String keyWord) {
        assert keyWord != null && !keyWord.isEmpty() : "Keyword should not be null or empty";

        return tasks.getListTask().stream()
                .filter(task -> task.getTaskDescription().toLowerCase().contains(keyWord.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Processes custom commands that do not match predefined commands like "mark", "delete", etc.
     *
     * @param tasks    The {@code TaskList} object that contains all the tasks.
     * @param storage  The {@code Storage} object for saving the task list to a file.
     * @return The result of processing the custom command as a {@code String}.
     */
    private String processCustomCommand(TaskList tasks, Storage storage) throws MentalHealthException {
        return processMessage(this.message, tasks, storage);
    }

    /**
     * Handles the case where an unknown command is received. If a message is present,
     * it processes the custom command; otherwise, returns an "Unknown message" response.
     *
     * @param tasks   The {@code TaskList} object containing all tasks.
     * @param storage The {@code Storage} object for saving the task list to a file.
     * @return The result of processing the custom command, or "Unknown message" if no message is present.
     * @throws MentalHealthException If an error occurs while processing the custom command.
     */
    private String handleUnknownCommand(TaskList tasks, Storage storage) throws MentalHealthException {
        if (!this.message.isEmpty()) {
            return processCustomCommand(tasks, storage);
        }
        return "Unknown message";
    }


    /**
     * Processes specific task-related commands such as "todo", "deadline", and "event".
     * This method parses the message to extract task details
     * and then creates and adds the appropriate task to the {@code TaskList}.
     *
     * @param message      The message containing the command and task details.
     * @param tasks    The {@code TaskList} object to which the new task will be added.
     * @param storage  The {@code Storage} object for saving the updated task list to a file.
     * @return The result of processing the task-related command as a {@code String}.
     * @throws MentalHealthException If an error occurs while processing the task command.
     */
    public String processMessage(String message, TaskList tasks, Storage storage) throws MentalHealthException {
        StringBuilder result = new StringBuilder();

        String[] messageList = message.split(" ");
        String commandType = messageList[0].toLowerCase();

        switch (commandType) {
        case "todo":
            result.append(processTodoCommand(messageList, tasks));
            break;
        case "deadline":
            result.append(processDeadlineCommand(tasks));
            break;
        case "event":
            result.append(processEventCommand(tasks));
            break;
        default:
            throw new MentalHealthException(ERROR_MESSAGE_UNKNOWN_COMMAND);
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
        result.append("Okays! I've added this task:\n")
                .append(formatMessage(newTodo, tasks.getListTask().size()));

        return result.toString();
    }

    /**
     * Processes "deadline" commands.
     *
     * @param tasks    The {@code TaskList} object to which the new task will be added.
     * @return The result of processing the "deadline" command as a {@code String}.
     * @throws MentalHealthException If an error occurs while processing the task command.
     */
    private String processDeadlineCommand(TaskList tasks) throws MentalHealthException {
        StringBuilder result = new StringBuilder();

        String[] parts = this.message.split(" /by ", 2);
        if (parts.length == 2) {
            String type = "deadline";
            String description = parts[0].substring(type.length()).trim();
            String by = parts[1].trim();
            Deadline newDeadline = new Deadline(description, by);
            tasks.addTask(newDeadline);
            result.append("Okays! I've added this task:\n")
                    .append(formatMessage(newDeadline, tasks.getListTask().size()));
        } else {
            throw new MentalHealthException("The string doesn't contain the expected format for a deadline.");
        }

        return result.toString();
    }

    /**
     * Processes "event" commands.
     *
     * @param tasks    The {@code TaskList} object to which the new task will be added.
     * @return The result of processing the "event" command as a {@code String}.
     * @throws MentalHealthException If an error occurs while processing the task command.
     */
    private String processEventCommand(TaskList tasks) throws MentalHealthException {
        String[] parts = this.message.split(" /from ", 2);
        if (parts.length != 2) {
            throw new MentalHealthException("The string doesn't contain the '/from' part.");
        }

        String description = parts[0].substring("event".length()).trim();
        String[] secondPart = parts[1].split(" /to ", 2);
        if (secondPart.length != 2) {
            throw new MentalHealthException("The string doesn't contain the '/to' part.");
        }

        String from = secondPart[0].trim();
        String to = secondPart[1].trim();
        Event newEvent = new Event(description, from, to);
        tasks.addTask(newEvent);

        return "Okays! I've added this task:\n" + formatMessage(newEvent, tasks.getListTask().size());
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
        assert task != null : "Task to be formatted should not be null";
        assert num >= 0 : "Number of tasks cannot be negative";
        return task + "\n" + "\n" + "Now you have " + num + " tasks in the list.\n";
    }
}
