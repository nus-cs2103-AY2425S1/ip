import command.Command;
import command.InvalidCommandException;
import parser.Parser;
import storage.DataIOException;
import task.InvalidTaskException;
import task.Task;

import java.util.Scanner;

/**
 * Jay is a personal assistant chatbot that helps users to keep track of various tasks.
 */
public class Jay {
    public static void main(String[] args) {
        Jay jay = new Jay("Jay");
        jay.start();
    }

    private final TaskList tasks;
    private final Ui ui;

    public Jay(String name) {
        this.tasks = new TaskList("tasks.txt");
        this.ui = new Ui(name);
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
     * Processes the command and returns the response.
     *
     * @param commandStr The command to be processed.
     * @return The response to the command. Show to the user by the chatbot.
     */
    private String processCommand(String commandStr) {
        Command command = new Command(commandStr);
        Command.CommandType commandType = command.getCommandType();

        try {
            switch (commandType) {
                case List -> {
                    return this.showTasks();
                }
                case Add -> {
                    Task.TYPE taskType = command.getTaskType();
                    return this.addTask(Parser.parseTask(taskType, command));
                }

                case Mark -> {
                    int taskNumber = command.getTaskNumber();
                    Task task = this.tasks.markAsDone(taskNumber);
                    return "Nice! I've marked this task as done:\n" + task;
                }

                case Unmark -> {
                    int taskNumber = command.getTaskNumber();
                    Task task = this.tasks.markAsNotDone(taskNumber);
                    return "OK, I've marked this task as not done yet:\n" + task;
                }

                case Delete -> {
                    int taskNumber = command.getTaskNumber();
                    return this.deleteTask(taskNumber);
                }

                case Exit -> {
                    return "Bye. Hope to see you again soon!";
                }

                default ->
                    throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (InvalidCommandException | InvalidTaskException | DataIOException e) {
            return e.getMessage();
        }
    }

    /**
     * Shows the tasks in the task list.
     * If the task list is empty, a message will be shown to the user.
     * @return The tasks in the task list or a message indicating that the task list is empty.
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
     * @param task The task to be added.
     * @return The response to the user.
     */
    private String addTask(Task task) {
        try {
            this.tasks.addTask(task);
            return "Got it. I've added this task:\n" + task + "\n" + this.tasks.getTaskCount();
        } catch (DataIOException e) {
            return e.getMessage();
        }
    }

    /**
     * Deletes a task from the task list.
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
}
