package fred;

import java.util.ArrayList;

/**
 * The Fred class serves as the main class for a task management application.
 * It initializes essential components such as Storage, TaskList, Ui, and Parser,
 * and provides methods to run the application, execute user actions, and handle
 * tasks like adding, marking, or deleting tasks.
 */
public class Fred {
    Storage storage;
    TaskList tasks;
    Ui ui;
    Parser parser;

    /**
     * Constructs a new Fred object and initializes the Storage, TaskList, Ui, and Parser components.
     */
    public Fred() {
        storage = new Storage();
        tasks = new TaskList();
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Runs the Fred application. This method loads tasks from the storage, greets the user,
     * and enters a loop to process user inputs and execute corresponding actions.
     */
    void run() {
        storage.getDataFile();
        ArrayList<Task> tasksFromDataFile = storage.getTasksFromDataFile();
        tasks.loadTasksFromDataFile(tasksFromDataFile);
    }

    /**
     * The main method to start the Fred application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Fred().run();
    }

    /**
     * Exits the Fred application by terminating the program.
     */
    void exit() {
        System.exit(0);
    }

    /**
     * Executes the user action based on the parsed input. The method supports
     * various actions such as adding a task, marking it as done or not done,
     * deleting a task, and printing the task list.
     *
     * @param action A String array containing the action and its parameters.
     * @throws FredException If the action is invalid or cannot be executed.
     */
    String executeAction(String[] action) throws FredException {
        String message = null;
        int taskNumber;
        Task task;
        switch (action[0]) {
        case "sayFarewell":
            message = "Bye. Hope to see you again soon!";
            break;
        case "printTaskList":
            String taskListString = tasks.getTaskListString();
            message = taskListString;
            break;
        case "markTaskAsDone":
            taskNumber = Integer.parseInt(action[1]);
            task = tasks.markTaskAsDone(taskNumber);
            message = String.format("Nice! I've marked this task as done:\n" +
                    "   %s", task);
            break;
        case "markTaskAsNotDone":
            taskNumber = Integer.parseInt(action[1]);
            task = tasks.markTaskAsNotDone(taskNumber);
            message = String.format("OK, I've marked this task as not done yet:\n" +
                    "   %s", task);
            break;
        case "deleteFromTaskList":
            taskNumber = Integer.parseInt(action[1]);
            task = tasks.deleteFromTaskList(taskNumber);
            storage.deleteFromDataFile(taskNumber);
            message = String.format("Noted. I've removed this task:\n" +
                    "   %s", task);
            break;
        case "addToTaskList":
            task = tasks.createTask(action[1], action[2]);
            tasks.addToTaskList(task);
            storage.appendToDataFile(task);
            message = String.format("Got it. I've added this task:\n" +
                    "   %s\n" +
                    "Now you have %d tasks in the list.", task, tasks.getTaskListSize());
            break;
        case "findTaskInTaskList":
            String tasksWithKeyword = tasks.findTasksInTaskList(action[1]);
            message = "Here are the matching tasks in your list:\n" + tasksWithKeyword;
            break;
        }
        ui.say(message);
        return message;
    }

    String getResponse(String input) {
        try {
            String[] action = parser.parseInput(input);
            return executeAction(action);
        } catch (FredException e) {
            return e.getMessage();
        }
    }
}
