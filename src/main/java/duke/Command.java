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
     * @throws MentalHealthException If an error occurs while processing the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MentalHealthException {
        String message = this.message.strip().toLowerCase();
        String[] parts = message.split(" ");

        switch (this.command) {
        case "bye":
            return;
        case "list":
            tasks.getTasks(tasks.getListTask());
            return;
        case "mark":
            handleMarkCommand(parts, tasks, storage);
            return;
        case "delete":
            handleDeleteCommand(parts, tasks, storage);
            return;
        case "find":
            handleFindCommand(parts, tasks);
            return;
        default:
            if (!this.message.isEmpty()) {
                processCustomCommand(tasks, storage);
            }
        }
    }

    /**
     * Marks the task as either done or not done.
     * If it is done, it is shown as "X".
     *
     * @param tasks    The {@code TaskList} object that contains all the tasks.
     * @param storage  The {@code Storage} object for saving the task list to a file.
     * @throws MentalHealthException If an error occurs while processing the command.
     */
    private void handleMarkCommand(String[] parts, TaskList tasks, Storage storage)
            throws MentalHealthException {
        if (parts.length > 1) {
            int number = Integer.parseInt(parts[parts.length - 1]);
            String checkMarkOrUnmark = parts[0];
            System.out.println("Extracted Task number: " + number);
            IndividualTask curTask = tasks.getListTask().get(number - 1);

            if (checkMarkOrUnmark.equals("mark")) {
                curTask.markOrUnmark("mark");
                System.out.println(
                        INDENT + "Okays! I've marked this task as done:\n"
                               + formatMessage(curTask, tasks.getListTask().size())
                );
            } else if (checkMarkOrUnmark.equals("unmark")) {
                curTask.markOrUnmark("unmark");
                System.out.println(
                        INDENT + "Okay! I've marked this task as not done:\n"
                               + formatMessage(curTask, tasks.getListTask().size())
                );
            } else {
                System.out.println("Not a valid command.");
            }
        } else {
            System.out.println("No Task found after 'mark'.");
        }
        storage.saveTasksToFile(tasks.getListTask());
    }

    /**
     * Deletes the task from the {@code TaskList}.
     *
     * @param tasks    The {@code TaskList} object that contains all the tasks.
     * @param storage  The {@code Storage} object for saving the task list to a file.
     */
    private void handleDeleteCommand(String[] parts, TaskList tasks, Storage storage) {
        if (parts.length > 1 && parts[0].equals("delete")) {
            int number = Integer.parseInt(parts[parts.length - 1]);
            System.out.println("Extracted Task number: " + number);
            IndividualTask curTask = tasks.getListTask().get(number - 1);
            tasks.deleteTask(number - 1);
            System.out.println(
                    INDENT + "Alrighty! I will remove the task:\n"
                           + formatMessage(curTask, tasks.getListTask().size())
            );
        } else {
            System.out.println("No Task found.");
        }
        storage.saveTasksToFile(tasks.getListTask());
    }

    /**
     * Finds the task that contains the keyword.
     *
     * @param tasks The {@code TaskList} object that contains all the tasks.
     * @param parts The keyword that the user wants to find.
     */
    private void handleFindCommand(String[] parts, TaskList tasks) {
        String keyWord = parts[parts.length - 1];
        ArrayList<IndividualTask> allTasks = tasks.getListTask();
        ArrayList<IndividualTask> matchingTasks = new ArrayList<>();
        for (IndividualTask task : allTasks) {
            if (task.getTaskDescription().toLowerCase().contains(keyWord.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        System.out.println(INDENT + "Here are your matching tasks!");
        tasks.getTasks(matchingTasks);
    }

    private void processCustomCommand(TaskList tasks, Storage storage) {
        try {
            processMessage(this.message, tasks, storage);
        } catch (MentalHealthException e) {
            System.out.println(INDENT + SEPARATOR);
            System.out.println(INDENT + "OOPS!!! " + e.getMessage());
            System.out.println(INDENT + SEPARATOR);
        }
    }

    /**
     * Processes specific task-related commands such as "todo", "deadline", and "event".
     * This method parses the message to extract task details
     * and then creates and adds the appropriate task
     * to the {@code TaskList}.
     *
     * @param msg      The message containing the command and task details.
     * @param tasks    The {@code TaskList} object to which the new task will be added.
     * @param storage  The {@code Storage} object for saving the updated task list to a file.
     * @throws MentalHealthException If an error occurs while processing the task command.
     */
    public void processMessage(String msg, TaskList tasks, Storage storage)
            throws MentalHealthException {

        String[] message = msg.split(" ");
        String commandType = message[0].toLowerCase();

        switch (CommandType.valueOf(commandType.toUpperCase())) {
        case TODO:
            processTodoCommand(message, tasks);
            break;
        case DEADLINE:
            processDeadlineCommand(message, tasks);
            break;
        case EVENT:
            processEventCommand(message, tasks);
            break;
        default:
            throw new MentalHealthException("I'm sorry, but I don't know what that means :-(");
        }
        storage.saveTasksToFile(tasks.getListTask());
    }

    /**
     * Processes "todo" commands.
     *
     * @param message  The message containing the command and task details.
     * @param tasks    The {@code TaskList} object to which the new task will be added.
     * @throws MentalHealthException If an error occurs while processing the task command.
     */
    private void processTodoCommand(String[] message, TaskList tasks)
            throws MentalHealthException {
        if (message.length < 2 || message[1].trim().isEmpty()) {
            throw new MentalHealthException("The description of a todo cannot be empty.");
        }
        String type = "todo";
        String todo = this.message.substring(type.length()).trim();
        ToDo newTodo = new ToDo(todo);
        tasks.addTask(newTodo);
        System.out.println(
                INDENT + "Okays! I've added this task:\n"
                       + formatMessage(newTodo, tasks.getListTask().size())
        );
    }

    /**
     * Processes "deadline" commands.
     *
     * @param message  The message containing the command and task details.
     * @param tasks    The {@code TaskList} object to which the new task will be added.
     * @throws MentalHealthException If an error occurs while processing the task command.
     */
    private void processDeadlineCommand(String[] message, TaskList tasks)
            throws MentalHealthException {
        String[] parts = this.message.split(" /by ", 2);
        if (parts.length == 2) {
            String type = "deadline";
            String description = parts[0].substring(type.length()).trim();
            String by = parts[1].trim();
            Deadline newDeadline = new Deadline(description, by);
            tasks.addTask(newDeadline);
            System.out.println(
                    INDENT + "Okays! I've added this task:\n"
                           + formatMessage(newDeadline, tasks.getListTask().size())
            );
        } else {
            throw new MentalHealthException("The string doesn't contain the expected format for a deadline.");
        }
    }

    /**
     * Processes "event" commands.
     *
     * @param message  The message containing the command and task details.
     * @param tasks    The {@code TaskList} object to which the new task will be added.
     * @throws MentalHealthException If an error occurs while processing the task command.
     */
    private void processEventCommand(String[] message, TaskList tasks)
            throws MentalHealthException {
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
                System.out.println(
                        INDENT + "Okays! I've added this task:\n"
                               + formatMessage(newEvent, tasks.getListTask().size())
                );
            } else {
                throw new MentalHealthException("The string doesn't contain the '/to' part.");
            }
        } else {
            throw new MentalHealthException("The string doesn't contain the '/from' part.");
        }
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
