package UI;

import exception.JadeException;
import task.*;

import java.util.Scanner;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

public class UI {
    public static final String INDENT = "     "; // 5 spaces for indentation
    public static final String TOP_LINE = "    " + "_".repeat(60) + "\n";
    public static final String BOT_LINE = "\n" + "    " + "_".repeat(60);
    public static final String GREET = INDENT + "Hello! I'm Jade!\n"
            + INDENT + "What can I do for you?";
    public static final String EXIT = INDENT + "Bye. Hope to see you again soon!";
    public static final String LIST_MESSAGE = INDENT + "Here are the tasks in your list:";
    private final TaskManager taskManager;
    private final Scanner sc;
    private String message;

    public UI(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.sc = new Scanner(System.in);
    }

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
        System.out.println(TOP_LINE + LIST_MESSAGE);
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            if (i < taskManager.getTaskCount() - 1) {
                System.out.println(INDENT + (i + 1) + "." + taskManager.getTask(i));
            } else {
                // last task
                System.out.println(INDENT + (i + 1) + "." + taskManager.getTask(i)
                        + BOT_LINE);
            }
        }
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
            displayErrorMessage("Please specify a valid task number.");
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
        Task newTask = parseTask(command);
        if (newTask != null) {
            taskManager.addTask(newTask);
            displayTaskAddedMessage(newTask);
        }
    }

    private Task parseTask(String command) {
        try {
            if (command.startsWith("todo")) {
                if (command.substring(4).trim().isEmpty()) {
                    throw new JadeException("The todo task cannot be empty!");
                }
                return new Todo(command.substring(5));
            } else if (command.startsWith("deadline")) {
                if (command.substring(8).trim().isEmpty()) {
                    throw new JadeException("The deadline task cannot be empty!");
                }
                return parseDeadline(command);
            } else if (command.startsWith("event")) {
                if (command.substring(5).trim().isEmpty()) {
                    throw new JadeException("The event task cannot be empty!");
                }
                return parseEvent(command);
            }
        } catch (JadeException e) {
            displayErrorMessage(e.getMessage());
        }
        return null;
    }

    private Task parseDeadline(String command) throws JadeException {
        String[] parts = command.substring(9).split(" /by ", 2);
        if (parts.length < 2) {
            throw new JadeException("Please provide a deadline in the format:\n"
                    + INDENT + "  " + "deadline <task> /by <time>");
        } else {
            try {
                return new Deadline(parts[0], parts[1]);
            } catch (DateTimeParseException e) {
                throw new JadeException("Please use yyyy-MM-dd HHmm format for time.\n"
                        + INDENT + "  " + "eg. 2024-12-25 2130");
            }
        }
    }

    private Task parseEvent(String command) throws JadeException {
        String[] parts = command.substring(6).split(" /from ", 2);
        if (parts.length < 2) {
            throw new JadeException("Please provide an event in the format:\n"
                    + INDENT + "  " + "event <task> /from <time>");
        } else {
            String[] timeParts = parts[1].split(" /to ", 2);
            if (timeParts.length < 2) {
                throw new JadeException("Please provide an end time in the format:\n"
                        + INDENT + "  " + "event <task> /from <start time> /to <end time>");
            } else {
                try {
                    return new Event(parts[0], timeParts[0], timeParts[1]);
                } catch (DateTimeParseException e) {
                    throw new JadeException("Please use yyyy-MM-dd HHmm format for time.\n"
                            + INDENT + "  " + "eg. 2024-12-25 2130");
                }
            }
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
            displayErrorMessage("Please specify a valid task number.");
        } catch (JadeException e) {
            displayErrorMessage(e.getMessage());
        }
    }

    private void handleFindCommand(String command) {
        String keyword = command.substring(5).trim();
        taskManager.findTasks(keyword);
    }

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
