package duke;

import duke.exceptions.EmptyTodoDescriptionException;
import duke.exceptions.UnknownMessageException;
import duke.tasks.Task;
import duke.tasks.TaskManager;

import java.util.Scanner;

public class DailyTasks {
    public static final String BOT_NAME = "DailyTasks";
    public static final String GREETING = "Hello! I'm " + BOT_NAME + ", your awesome task planner!";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";

    private final TaskManager taskManager;

    public DailyTasks() {
        this.taskManager = new TaskManager();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DailyTasks dailyTasks = new DailyTasks();

        dailyTasks.start(scanner);
    }

    private void start(Scanner scanner) {
        System.out.println(Formatter.formatOutputMessage(GREETING));

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equals("bye")) {
                System.out.println(Formatter.formatOutputMessage(GOODBYE));
                break;
            }

            handleCommand(userInput);
        }
    }

    private void handleCommand(String userInput) {
        try {
            if (userInput.equals("list")) {
                System.out.println(Formatter.formatTaskListings(taskManager.getTasks()));
            } else if (userInput.startsWith("unmark")) {
                handleMarkingTask(userInput, false);
            } else if (userInput.startsWith("mark")) {
                handleMarkingTask(userInput, true);
            } else if (userInput.startsWith("delete")) {
                handleDeleteTask(userInput);
            } else { // we try to add a task (todos/ deadline/ event) else throw an exception
                taskManager.addTask(userInput);
                System.out.println(Formatter.formatAddTask(taskManager.getTasks().size(),
                        taskManager.getLastTask()));
            }
        } catch (UnknownMessageException | EmptyTodoDescriptionException e) {
            System.out.println(Formatter.formatOutputMessage("Please enter a valid task!"));
        }
    }

    private void handleMarkingTask(String userInput, boolean markAsDone) {
        int index = parseTaskIndex(userInput);

        if (index != -1) {
            Task task = taskManager.getTask(index);
            if (markAsDone) {
                task.setDone();
                System.out.println(Formatter.formatMarkTask(task));
            } else {
                task.setNotDone();
                System.out.println(Formatter.formatUnmarkTask(task));
            }
        }
    }

    private void handleDeleteTask(String userInput) {
        int index = parseTaskIndex(userInput);

        if (index != -1) {
            Task task = taskManager.deleteTask(index);
            System.out.println(Formatter.formatDeleteTask(task, taskManager.getTasks().size()));
        }
    }

    private int parseTaskIndex(String userInput) {
        try {
            return Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid task number provided.");
            return -1;
        }
    }
}
