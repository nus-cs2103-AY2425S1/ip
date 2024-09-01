package bob;

import bob.task.Task;
import bob.task.ToDo;
import bob.task.Deadline;
import bob.task.Event;

import java.io.File;
import java.time.LocalDateTime;

public class Bob {
    private static final String FILE_PATH = "data" + File.separator + "Bob.txt";
    private static TaskList taskList;
    private static Storage storage;
    private static Ui ui;

    public Bob(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            taskList = new TaskList(storage.load());
            ui.showLoadingSuccess();
        } catch (BobException e) {
            ui.showError(e);
            taskList = new TaskList();
        }
    }

    public enum Command {
        BYE, LIST, RELEVANT, FIND, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    public static void main(String[] args) {
        try {
            new Bob(FILE_PATH).run();
        } catch (BobException e) {
            ui.showError(e);
        }
    }

    public void run() throws BobException {
        ui.showWelcome();

        while (true) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parseCommand(userInput);
                String taskDetails = Parser.getTaskDetails(userInput);

                switch (command) {
                case BYE:
                    commandBye();
                    return;

                case LIST:
                    commandList();
                    break;

                case RELEVANT:
                    commandRelevant(taskDetails);
                    break;

                case MARK:
                    commandMark(taskDetails);
                    break;

                case UNMARK:
                    commandUnmark(taskDetails);
                    break;

                case TODO:
                    commandTodo(taskDetails);
                    break;

                case DEADLINE:
                    commandDeadline(taskDetails);
                    break;

                case EVENT:
                    commandEvent(taskDetails);
                    break;

                case DELETE:
                    commandDelete(taskDetails);
                    break;

                case FIND:
                    commandFind(taskDetails);
                    break;

                case UNKNOWN:

                default:
                    throw new BobException("Sorry, I do not understand. Please try something else.");
                }
            } catch (BobException e) {
                ui.showError(e);
            } catch (Exception e) {
                throw new BobException("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    static void commandBye() {
        ui.showGoodbye();
    }

    static void commandList() {
        if (taskList.isEmpty()) {
            ui.showNoTasks();
        }
            ui.showMessage("Your list of tasks:\n" + taskList.printTasks());
    }

    static void commandRelevant(String dateStr) throws BobException {
        ui.showMessage(taskList.printRelevantTasksByDate(dateStr));
    }

    static void commandFind(String taskDetails) throws BobException {
        ui.showMessage(taskList.printTasksByKeyword(taskDetails));
    }

    static void commandMark(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            int taskNum = Integer.parseInt(taskDetails);
            bob.task.Task currTask = taskList.getTask(taskNum);
            currTask.markAsDone();
            storage.saveTasks(taskList);
            ui.showMessage("Good Job! Marking this task as done:\n " + currTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    static void commandUnmark(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            int taskNum = Integer.parseInt(taskDetails);
            Task currTask = taskList.getTask(taskNum);
            currTask.markAsUndone();
            storage.saveTasks(taskList);
            ui.showMessage("Okay, marking this task as not done yet:\n " + currTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    static void commandTodo(String taskDetails) throws BobException {
        String format = "Add ToDo task in the following format:\ntodo <description>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }
        ToDo task = new ToDo(taskDetails);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        ui.showMessage("Adding ToDo task:\n " + task
                + "\nTotal number of tasks in your list: " + taskList.getNumTasks());
    }

    static void commandDeadline(String taskDetails) throws BobException {
        String format = "Add Deadline task in the following format:\n"
                + "deadline <description> /by <due date>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }

        try {
            String[] params = taskDetails.split(" /");
            String description = params[0];
            String byStr = params[1].split(" ", 2)[1];

            LocalDateTime by = Parser.parseDateTime(byStr);
            if (by.isBefore(LocalDateTime.now())) {
                throw new BobException("Oops! The date you provided is in the past. "
                        + "Kindly provide a future date.");
            }
            Deadline task = new Deadline(description, by);
            taskList.addTask(task);
            storage.saveTasks(taskList);
            ui.showMessage("Adding Deadline task:\n " + task
                    + "\nTotal number of tasks in your list: " + taskList.getNumTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("You may have missing details or wrong format!\n" + format);
        }
    }

    static void commandEvent(String taskDetails) throws BobException {
        String format = "Add Event task in the following format:\n"
                + "deadline <description> /from <start date> /to <due date>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }

        try {
            String[] params = taskDetails.split(" /");
            String description = params[0];
            String fromStr = params[1].split(" ", 2)[1];
            String toStr = params[2].split(" ", 2)[1];

            LocalDateTime from = Parser.parseDateTime(fromStr);
            LocalDateTime to = Parser.parseDateTime(toStr);
            if (from.isBefore(LocalDateTime.now()) || to.isBefore(LocalDateTime.now())) {
                throw new BobException("Oops! The date you provided is in the past. "
                        + "Kindly provide a future date.");
            }
            if (to.isBefore(from)) {
                throw new BobException("The end date cannot be before the start date. " +
                        "Please try again.");
            }
            Event task = new Event(description, from, to);
            taskList.addTask(task);
            storage.saveTasks(taskList);
            ui.showMessage("Adding Event task:\n " + task
                    + "\nTotal number of tasks in your list: " + taskList.getNumTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("You may have missing details or wrong format!\n" + format);
        }
    }

    static void commandDelete(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            int taskNum = Integer.parseInt(taskDetails);
            Task currTask = taskList.getTask(taskNum);
            taskList.delTask(taskNum);
            storage.saveTasks(taskList);
            ui.showMessage("Noted, removing this task:\n " + currTask
                    + "\nTotal number of tasks in your list: " + taskList.getNumTasks());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }
}