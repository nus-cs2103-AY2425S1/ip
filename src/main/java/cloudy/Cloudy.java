package cloudy;

import java.time.LocalDate;

import java.util.Scanner;

/**
 * Represents the main class for the Cloudy program.
 * Manages user input, task list operations, and file storage interactions.
 */
public class Cloudy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initializes a new instance of the Cloudy program.
     *
     * @param filePath The file path to load and save tasks.
     */
    public Cloudy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(Storage.loadTasksFromFile());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop, processing user input until the program ends.
     */
    public void run() {
        ui.showGreeting();
        Scanner echo = new Scanner(System.in);
        String userInput;

        // main program loop
        while (true) {
            userInput = echo.nextLine();
            Command command = parser.parseCommand(userInput);

            switch (command.getType()) {
                case "bye":
                    ui.showBye();
                    return;
                case "list":
                    ui.showList(tasks);
                    break;
                case "mark":
                    handleMarkCommand(command);
                    break;
                case "unmark":
                    handleUnmarkCommand(command);
                    break;
                case "todo":
                    handleTodoCommand(command);
                    break;
                case "deadline":
                    handleDeadlineCommand(command);
                    break;
                case "event":
                    handleEventCommand(command);
                    break;
                case "delete":
                    handleDeleteCommand(command);
                    break;
                case "invalid":
                    ui.showInvalidCommand();
                    break;
                case "invalidTaskNum":
                    ui.showInvalidTaskNum();
                    break;
                case "invalidTaskFormat":
                    ui.showInvalidTaskFormat();
                    break;
                case "invalidDeadline":
                    ui.showInvalidDeadline();
                    break;
                case "invalidEvent":
                    ui.showInvalidEvent();
                    break;
                default:
                    ui.showInvalidCommand();
            }
        }
    }

    private void handleMarkCommand(Command command) {
        try {
            int taskNumber = command.getTaskNumber();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task taskToMark = tasks.getTask(taskNumber - 1);
                taskToMark.markTask();
                storage.saveTasksToFile(tasks.getAllTasks());
                ui.showMark(taskToMark);
            } else {
                ui.showInvalidTaskNum();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTaskNum();
        }
    }

    private void handleUnmarkCommand(Command command) {
        try {
            int taskNumber = command.getTaskNumber();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task taskToUnmark = tasks.getTask(taskNumber - 1);
                taskToUnmark.unmarkTask();
                ui.showUnmark(taskToUnmark);
            } else {
                ui.showInvalidTaskNum();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTaskNum();
        }
    }

    private void handleTodoCommand(Command command) {
        String taskDescription = command.getTaskDescription();
        Task newTask = new Todo(taskDescription, false);
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showAddTask(newTask, tasks.size());
    }

    private void handleDeadlineCommand(Command command) {
        String taskDescription = command.getTaskDescription();
        LocalDate deadline = command.getDeadline();
        Task newTask = new Deadline(taskDescription, deadline, false);
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showAddTask(newTask, tasks.size());
    }

    private void handleEventCommand(Command command) {
        String taskDescription = command.getTaskDescription();
        LocalDate startTime = command.getStartTime();
        LocalDate endTime = command.getEndTime();
        Task newTask = new Event(taskDescription, startTime, endTime, false);
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showAddTask(newTask, tasks.size());
    }

    private void handleDeleteCommand(Command command) {
        try {
            int taskNumber = command.getTaskNumber();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task taskToDelete = tasks.getTask(taskNumber - 1);
                tasks.removeTask(taskNumber - 1);
                storage.saveTasksToFile(tasks.getAllTasks());
                ui.showDeleteTask(taskToDelete, tasks.size());
            } else {
                ui.showInvalidTaskNum();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTaskNum();
        }
    }


    /**
     * The main entry point of the Cloudy application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Cloudy("data/tasks.txt").run();
    }
}