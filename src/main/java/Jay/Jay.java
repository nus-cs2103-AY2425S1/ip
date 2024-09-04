package Jay;

import java.util.Scanner;

import Jay.command.Command;
import Jay.command.InvalidCommandException;
import Jay.parser.Parser;
import Jay.storage.DataIOException;
import Jay.task.InvalidTaskException;
import Jay.task.Task;

/**
 * Jay.Jay is a personal assistant chatbot that helps users to keep track of various tasks.
 */
public class Jay {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Jay.Jay object.
     *
     * @param name The name of the user.
     */
    public Jay(String name) {
        this.tasks = new TaskList("tasks.txt");
        this.ui = new Ui(name);
    }

    public static void main(String[] args) {
        Jay jay = new Jay("Jay");
        jay.start();
    }


    /**
     * Starts the chatbot.
     */
    public void start() {
        ui.greet();
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
     * Processes the Jay.command and returns the response.
     *
     * @param commandStr The Jay.command to be processed.
     * @return The response to the Jay.command. Show to the user by the chatbot.
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
                return "Nice! I've marked this Jay.task as done:\n" + task;
            case Unmark:
                taskNumber = command.getTaskNumber();
                task = this.tasks.markAsNotDone(taskNumber);
                return "OK, I've marked this Jay.task as not done yet:\n" + task;
            case Delete:
                taskNumber = command.getTaskNumber();
                return this.deleteTask(taskNumber);
            case Find:
                String keyword = command.getKeyword();
                return this.findTasks(keyword);
            case Exit:
                return "Bye. Hope to see you again soon!";
            default:
                throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (InvalidCommandException | InvalidTaskException | DataIOException e) {
            return e.getMessage();
        }
    }

    /**
     * Shows the tasks in the Jay.task list.
     * If the Jay.task list is empty, a message will be shown to the user.
     *
     * @return The tasks in the Jay.task list or a message indicating that the Jay.task list is empty.
     */
    private String showTasks() {
        if (this.tasks.isEmpty()) {
            return "You have no tasks in the list.";
        } else {
            return "Here are the tasks in your list:\n" + tasks;
        }
    }

    /**
     * Adds a Jay.task to the Jay.task list.
     *
     * @param task The Jay.task to be added.
     * @return The response to the user.
     */
    private String addTask(Task task) {
        try {
            this.tasks.addTask(task);
            return "Got it. I've added this Jay.task:\n" + task + "\n" + this.tasks.getTaskCount();
        } catch (DataIOException e) {
            return e.getMessage();
        }
    }

    /**
     * Deletes a Jay.task from the Jay.task list.
     *
     * @param taskNumber The number of the Jay.task to be deleted.
     * @return The response to the user.
     */
    private String deleteTask(int taskNumber) {
        try {
            Task task = this.tasks.removeTask(taskNumber);
            return "Noted. I've removed this Jay.task:\n" + task + "\n" + this.tasks.getTaskCount();
        } catch (DataIOException | InvalidCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Finds tasks in the Jay.task list that contain the keyword.
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
