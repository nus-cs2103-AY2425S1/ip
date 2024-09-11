package jay;

import java.util.Scanner;

import jay.command.Command;
import jay.command.InvalidCommandException;
import jay.parser.Parser;
import jay.storage.DataIOException;
import jay.task.InvalidTaskException;
import jay.task.Task;

/**
 * Jay is a personal assistant chatbot that helps users to keep track of various tasks.
 */
public class Jay {
    private final String name;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Jay object.
     *
     * @param name The name of the user.
     */
    public Jay(String name) {
        this.tasks = new TaskList("tasks.txt");
        this.ui = new Ui();
        this.name = name;
    }

    public static void main(String[] args) {
        new Jay("Jay").startCli();
    }

    /**
     * Starts the chatbot for cli application.
     */
    public void startCli() {
        ui.output(this.greet());
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                ui.quit();
                break;
            } else {
                ui.output(this.processCommand(command));
            }
        }
    }

    /**
     * Starts the chatbot for gui application.
     */
    public String showHighPriorityTasks() {
        TaskList highPriorityTasks = this.tasks.getHighPriorityTasks();
        if (highPriorityTasks.isEmpty()) {
            return "You have no high priority tasks in the list.";
        } else {
            return highPriorityTasks.getHighPriorityTasksCount() + "\n" + highPriorityTasks;
        }
    }

    /**
     * Greets the user.
     *
     * @return The greeting message.
     */
    public String greet() {
        return "Hello! I'm " + this.name + "\n" + " What can I do for you? "
                + "Can type 'help' command to see the list of commands.";
    }

    /**
     * Handles the user input.
     *
     * @param input The user input.
     * @return The response to the user.
     */
    public String handleInput(String input) {
        return this.processCommand(input);
    }

    /**
     * Processes the command and returns the response.
     *
     * @param commandStr The command to be processed.
     * @return The response to the command. Show to the user by the chatbot.
     */
    private String processCommand(String commandStr) {
        Command command = new Command(commandStr);
        Command.CommandType commandType = command.getCommandType();

        try {
            int taskNumber = 0; // for the switch statement
            Task task = null; // for the switch statement

            switch (commandType) {
            case List:
                return this.showTasks();
            case Add:
                Task.Type taskType = command.getTaskType();
                return this.addTask(Parser.parseTask(taskType, command));
            case Mark:
                taskNumber = command.getTaskNumber();
                task = this.tasks.markAsDone(taskNumber);
                return "Nice! I've marked this task as done:\n" + task;
            case Unmark:
                taskNumber = command.getTaskNumber();
                task = this.tasks.markAsNotDone(taskNumber);
                return "OK, I've marked this task as not done yet:\n" + task;
            case Delete:
                taskNumber = command.getTaskNumber();
                return this.deleteTask(taskNumber);
            case Find:
                String keyword = command.getKeyword();
                return this.findTasks(keyword);
            case Exit:
                return "Bye. Hope to see you again soon!";
            case Set:
                taskNumber = command.getTaskNumber();
                Task.Priority priority = command.getPriority();
                task = this.tasks.setPriority(taskNumber, priority);
                return "Got it. I've set the priority of this task to { " + priority + " } :\n"
                        + task;
            case Help:
                return command.getHelp();
            default:
                throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (InvalidCommandException | InvalidTaskException | DataIOException e) {
            return e.getMessage();
        }
    }

    /**
     * Shows the tasks in the task list.
     * If the task list is empty, a message will be shown to the user.
     *
     * @return The tasks in the task list or a message indicating that the Jay.task list is empty.
     */
    private String showTasks() {
        if (this.tasks.isEmpty()) {
            return "You have no tasks in the list.";
        } else {
            return "Here are the tasks in your list:\n" + tasks;
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @return The response to the user.
     */
    private String addTask(Task task) {
        try {
            assert task != null : "Task should not be null";
            this.tasks.addTask(task);
            return "Got it. I've added this task:\n" + task + "\n" + this.tasks.getTaskCount();
        } catch (DataIOException e) {
            return e.getMessage();
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber The number of the task to be deleted.
     * @return The response to the user.
     */
    private String deleteTask(int taskNumber) {
        try {
            Task task = this.tasks.removeTask(taskNumber);
            return "Noted. I've removed this task:\n" + task + "\n" + this.tasks.getTaskCount();
        } catch (DataIOException | InvalidCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Finds tasks in the task list that contain the keyword.
     *
     * @param keyword The keyword to search for.
     * @return The tasks that contain the keyword.
     */
    private String findTasks(String keyword) {
        TaskList foundTasks = this.tasks.findTasks(keyword);
        if (foundTasks.isEmpty()) {
            return "There are no tasks that match the keyword.";
        } else {
            return "Here are the matching tasks in your list:\n" + foundTasks;
        }
    }
}
