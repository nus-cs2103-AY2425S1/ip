package duke;

import duke.exceptions.EmptyTodoDescriptionException;
import duke.exceptions.UnknownMessageException;
import duke.tasks.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;

public class DailyTasks {
    public static final String BOT_NAME = "DailyTasks";
    public static final String GREETING = "Hello! I'm " + BOT_NAME + ", your awesome task planner!";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";

    private final TaskList taskList;

    public DailyTasks() {
        this.taskList = new TaskList();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DailyTasks dailyTasks = new DailyTasks();

        try {
            List<Task> tasks = Storage.loadStateFileToTasksList();
            dailyTasks.taskList.setTasks(tasks);

            dailyTasks.start(scanner);

            Storage.saveTasksListToStateFile(dailyTasks.taskList.getTasks());
            System.out.println("saved");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    private void start(Scanner scanner) {
        System.out.println(Ui.formatOutputMessage(GREETING));

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equals("bye")) {
                System.out.println(Ui.formatOutputMessage(GOODBYE));
                break;
            }

            handleCommand(userInput);
        }
    }

    private void handleCommand(String userInput) {
        try {
            if (userInput.equals("list")) {
                System.out.println(Ui.formatTaskListings(taskList.getTasks(), false));
            } else if (userInput.startsWith("unmark")) {
                handleMarkingTask(userInput, false);
            } else if (userInput.startsWith("mark")) {
                handleMarkingTask(userInput, true);
            } else if (userInput.startsWith("delete")) {
                handleDeleteTask(userInput);
            } else if (userInput.startsWith("filter")) {
                handleFilterTask(userInput);
            } else { // we try to add a task (todos/ deadline/ event) else throw an exception
                taskList.addTask(userInput);
            }
        } catch (UnknownMessageException | EmptyTodoDescriptionException e) {
            System.out.println(Ui.formatOutputMessage("Please enter a valid task!"));
        }
    }

    private void handleMarkingTask(String userInput, boolean markAsDone) {
        int index = parseTaskIndex(userInput);

        if (index != -1) {
            Task task = taskList.getTask(index);
            if (markAsDone) {
                task.setDone();
                System.out.println(Ui.formatMarkTask(task));
            } else {
                task.setNotDone();
                System.out.println(Ui.formatUnmarkTask(task));
            }
        }
    }

    private void handleDeleteTask(String userInput) {
        int index = parseTaskIndex(userInput);

        if (index != -1) {
            Task task = taskList.deleteTask(index);
            System.out.println(Ui.formatDeleteTask(task, taskList.getTasks().size()));
        }
    }

    private void handleFilterTask(String userInput) {
        String dateString = userInput.split(" ", 2)[1];
        LocalDateTime dateTime = Parser.parseDateTime(dateString);
        List<Task> tasks = this.taskList.getTasksOccurring(dateTime);
        System.out.println(Ui.formatTaskListings(tasks, true));
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
