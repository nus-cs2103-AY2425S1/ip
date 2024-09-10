package vuewee.ui;

import java.util.Scanner;

import vuewee.EndProgramException;
import vuewee.command.Command;
import vuewee.command.CommandType;
import vuewee.parser.CommandParser;
import vuewee.parser.IllegalCommandException;
import vuewee.task.Task;
import vuewee.task.TaskList;

/**
 * The TaskListCli class represents the user interface for Vuewee. It contains
 * methods to add, delete, display, and mark tasks as done or not done.
 */
public class TaskListCli extends TaskListUi {
    private static TaskListCli instance;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Creates a new TaskListUI object with the specified task list. Used for
     * reading and writing tasks to a file and for testing purposes.
     *
     * @param taskList Existing task list to be used
     */
    private TaskListCli(TaskList taskList) {
        super(taskList);
    }

    private TaskListCli() {
        super();
    }

    /**
     * Returns the singleton instance of TaskListCli.
     *
     * @return The singleton instance
     */
    public static TaskListCli getTaskListInstance(TaskList taskList) {
        if (instance == null) {
            instance = new TaskListCli(taskList);
        }
        return instance;
    }

    /**
     * Creates a new TaskListUI object with the specified scanner.
     */
    public static TaskListCli getScannerInstance() {
        if (instance == null) {
            instance = new TaskListCli();
        }
        return instance;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added
     */
    @Override
    public void addTask(Task task) {
        super.addTask(task);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + super.taskList.size() + " " + this.taskWord() + " in the list.");
    }

    /**
     * Delete a task from the list
     *
     * @param taskNumber The index of the task to be deleted (1-based)
     * @throws IndexOutOfBoundsException
     */
    @Override
    public Task deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        Task deletedTask = super.deleteTask(taskNumber);

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deletedTask.toString());
        System.out.println("Now you have " + this.taskList.size() + " " + super.taskWord() + " in the list.");

        return deletedTask;
    }

    /**
     * Display all tasks in the list that match the keyword. Search is done by
     * another method that returns a new TaskList with matching tasks.
     *
     * @param tasks TaskList to search for matching tasks
     * @throws IllegalCommandException
     */
    @Override
    public void displayTasks(TaskList tasks) throws IllegalCommandException {
        if (tasks.size() == 0) {
            throw new IllegalCommandException("No tasks found.");
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("  " + (i + 1) + ". " + task.toString());
        }
    }

    /**
     * Mark a task as done or not done
     *
     * @param taskNumber The index of the task to be marked (1-based)
     * @param isDone     The new status of the task
     * @throws IllegalCommandException
     */
    @Override
    public void markTask(int taskNumber, boolean isDone) throws IllegalCommandException {
        try {
            super.markTask(taskNumber, isDone);
            if (isDone) {
                System.out.println("Nice! I've marked this task as done:");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + this.taskList.get(taskNumber - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalCommandException("Invalid task number. There are " + this.taskList.size() + " " + this
                    .taskWord() + " in your list.");
        }
    }

    /**
     * Starts the Vuewee program and reads user input until the user types "bye".
     */
    @Override
    public void run() {
        this.taskList = this.storage.readTasks();

        if (this.taskList.size() > 0) {
            System.out.println("Loaded " + this.taskList.size() + " " + this.taskWord() + " into your task list.");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Vuewee\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        // Echo input from user until user types "bye"
        try {
            while (true) {
                String input = scanner.nextLine();
                try {
                    System.out.println("____________________________________________________________");

                    CommandParser parser = new CommandParser(input);
                    CommandType commandType = parser.getCommandType();
                    Command command = commandType.createCommand();
                    command.executeCommand(this, taskList, parser);

                } catch (IndexOutOfBoundsException | IllegalCommandException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("____________________________________________________________");
                storage.storeTasks(this.taskList);
            }
        } catch (EndProgramException e) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
            this.close();
        }
    }

    @Override
    public void close() {
        TaskListCli.instance = null;
        this.storage.storeTasks(this.taskList);
        this.scanner.close();
    }
}
