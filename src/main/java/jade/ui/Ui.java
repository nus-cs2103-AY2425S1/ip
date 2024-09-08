package jade.ui;

import java.util.ArrayList;
import java.util.Scanner;

import jade.exception.JadeException;
import jade.parser.Parser;
import jade.task.Task;
import jade.task.TaskManager;
import jade.task.TaskType;

/**
 * Handles user interaction and input for the Jade application.
 */
public class Ui {
    public static final String INDENT = "     "; // 5 spaces for indentation
    public static final String TOP_LINE = "    " + "_".repeat(60) + "\n";
    public static final String BOT_LINE = "\n" + "    " + "_".repeat(60);
    public static final String GREET = INDENT + "Hello! I'm Jade!\n"
            + INDENT + "What can I do for you?";
    public static final String EXIT = INDENT + "Bye. Hope to see you again soon!";

    private final Scanner sc;
    private final TaskManager taskManager;
    private final Parser parser;
    private String message;

    /**
     * Constructs a UI object with the specified TaskManager.
     *
     * @param taskManager The TaskManager to interact with.
     */
    public Ui(TaskManager taskManager) {
        this.sc = new Scanner(System.in);
        this.taskManager = taskManager;
        this.parser = new Parser();
    }

    /**
     * Starts the user interface and handles user commands.
     */
    public void run() {
        System.out.println(TOP_LINE + GREET + BOT_LINE);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    displayTaskList();
                } else if (command.startsWith("mark")) {
                    handleMarkCommand(command, true);
                } else if (command.startsWith("unmark")) {
                    handleMarkCommand(command, false);
                } else if (isTaskCommand(command)) {
                    handleTaskCommand(command);
                } else if (command.startsWith("delete")) {
                    handleDeleteCommand(command);
                } else if (command.startsWith("find")) {
                    handleFindCommand(command);
                } else {
                    throw new JadeException("Please specify the type of task: todo, deadline, or event.");
                }
            } catch (JadeException e) {
                displayErrorMessage(e.getMessage());
            }
            command = sc.nextLine();
        }

        System.out.println(TOP_LINE + EXIT + BOT_LINE);
    }

    private void displayTaskList() {
        StringBuilder message = new StringBuilder(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            message.append("\n" + INDENT).append(i + 1).append(".").append(taskManager.getTask(i));
        }
        System.out.println(TOP_LINE + message + BOT_LINE);
    }

    private void handleMarkCommand(String command, boolean isDone) {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            if (taskManager.isValidTaskIndex(taskIndex)) {
                taskManager.markTask(taskIndex, isDone);
                String status = isDone
                        ? "Nice! I've marked this task as done:"
                        : "OK, I've marked this task as not done yet:";
                message = INDENT + status + "\n"
                        + INDENT + "  " + taskManager.getTask(taskIndex);
                System.out.println(TOP_LINE + message + BOT_LINE);
            } else {
                throw new JadeException("Hmm, no such task. Try again.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            displayErrorMessage("Please specify a valid task number in the format:\n"
                    + INDENT + "  mark <index>");
        } catch (JadeException e) {
            displayErrorMessage(e.getMessage());
        }
    }

    private boolean isTaskCommand(String command) {
        try {
            TaskType.valueOf(command.split(" ")[0].toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private void handleTaskCommand(String command) {
        try {
            Task newTask = parser.parseTaskCommand(command);
            if (newTask != null) {
                taskManager.addTask(newTask);
                displayTaskAddedMessage(newTask);
            }
        } catch (JadeException e) {
            displayErrorMessage(e.getMessage());
        }
    }

    private void displayTaskAddedMessage(Task task) {
        int taskCount = taskManager.getTaskCount();
        message = INDENT + "Got it. I've added this task:\n"
                + INDENT + "  " + task;

        if (taskCount == 1) {
            message += "\n" + INDENT + "Now you have 1 task in the list.";
        } else {
            message += "\n" + INDENT + String.format("Now you have %d tasks in the list.", taskCount);
        }

        System.out.println(TOP_LINE + message + BOT_LINE);
    }

    private void handleDeleteCommand(String command) {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            if (taskManager.isValidTaskIndex(taskIndex)) {
                Task removedTask = taskManager.getTask(taskIndex);
                taskManager.deleteTask(taskIndex);
                int taskCount = taskManager.getTaskCount();

                message = INDENT + "Noted. I've removed this task:\n"
                        + INDENT + "  " + removedTask;
                if (taskCount <= 1) {
                    message += "\n" + INDENT + String.format("Now you have %d task in the list.", taskCount);
                } else {
                    message += "\n" + INDENT + String.format("Now you have %d tasks in the list.", taskCount);
                }

                System.out.println(TOP_LINE + message + BOT_LINE);
            } else {
                throw new JadeException("Hmm, no such task. Try again.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            displayErrorMessage("Please specify a valid task number in the format:\n"
                    + INDENT + "  mark <index>");
        } catch (JadeException e) {
            displayErrorMessage(e.getMessage());
        }
    }

    private void handleFindCommand(String command) {
        String keyword = command.substring(5).trim();
        taskManager.findTasks(keyword);
    }

    /**
     * Displays the list of tasks that match the search keyword provided by the user.
     * If no tasks match the keyword, a message indicating no matches is shown.
     *
     * @param matchingTasks The list of tasks that match the search keyword.
     */
    public static void showMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder message;
        if (matchingTasks.isEmpty()) {
            message = new StringBuilder(INDENT + "No matching tasks found.");
        } else {
            message = new StringBuilder(INDENT + "Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                message.append("\n" + INDENT).append(i + 1).append(".").append(matchingTasks.get(i));
            }
        }
        System.out.println(TOP_LINE + message + BOT_LINE);

    }

    private void displayErrorMessage(String errorMessage) {
        System.out.println(TOP_LINE + INDENT + errorMessage + BOT_LINE);
    }
}
