package hannah;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import hannah.command.Command;
import hannah.task.Deadline;
import hannah.task.Event;
import hannah.task.Task;
import hannah.task.TaskList;
import hannah.task.ToDos;
import javafx.application.Platform;

/**
 * Represents the main class for the Hannah application.
 * The Hannah class manages tasks by interacting with the user through commands
 * such as adding, deleting, marking tasks, and finding tasks. It stores and retrieves
 * tasks using the Storage class, and processes user input using the Parser class.
 */
public class Hannah {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String FILE_PATH = "src/data/Hannah.txt";
    private Storage storage;
    private TaskList list;
    private Parser parser;
    private Ui ui;

    /**
     * The Hannah class represents the main application for managing tasks.
     * It interacts with the user through the command line and processes tasks
     * such as ToDos, Deadlines, and Events.
     */
    public Hannah() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.parser = new Parser();
        try {
            storage.loadFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    /**
     * Runs the Hannah application, handling user input and managing tasks.
     * The application continues running until the user types "bye".
     */
    public String run(String userInput) {
        TaskList list = (TaskList) this.storage.getTaskList();
        String result;
        Command command = parser.parse(userInput);
        Task task = null;
        String commandName = command.getCommandType();
        assert userInput != null && !userInput.trim().isEmpty() : "User input should not be null or empty";

        try {
            if (commandName.equals("bye")) {
                result = ui.showGoodbyeMessage();
                Platform.exit();
            } else if (commandName.equals("list")) {
                result = ui.showTasks(list);
            } else if (commandName.equals("delete")) {
                int taskNumber = ui.getTaskNumber(userInput);
                task = list.getTask(taskNumber);
                assert task != null : "Task to delete should not be null";
                list.deleteTask(taskNumber);
                int taskCount = list.size();
                result = ui.showTaskDeleted(taskCount, task);
            } else if (commandName.equals("unmark")) {
                int taskNumber = ui.getTaskNumber(userInput);
                task = list.getTask(taskNumber);
                assert task != null : "Task to unmark should not be null";
                list.unmarkTask(task);
                result = ui.showTaskUnmarked(task);
            } else if (commandName.equals("mark")) {
                int taskNumber = ui.getTaskNumber(userInput);
                task = list.getTask(taskNumber);
                assert task != null : "Task to mark should not be null";
                list.markTask(task);
                result = ui.showTaskMarked(task);
            } else if (commandName.equals("todo")) {
                if (userInput.length() <= 4) {
                    result = "Please add a task todo";
                } else {
                    task = new ToDos(userInput.substring(5));
                    assert task != null : "Todo task should not be null";
                    list.addTask(task);
                    int taskCount = list.size();
                    result = ui.showTaskAdded(task, taskCount);
                }
            } else if (commandName.equals("deadline")) {
                if (userInput.length() <= 8) {
                    result = "Please add a task after deadline";
                } else {
                    int slashIndex = userInput.indexOf("/");
                    assert slashIndex != -1 : "Invalid input format, missing slash";
                    if (!validateDate(userInput.substring(slashIndex + 4))) {
                        result = "Invalid date format. Please use " + DATE_FORMAT + ".";
                    } else {
                        String taskName = userInput.substring(9, slashIndex - 1);
                        task = new Deadline(taskName);
                        assert task != null : "Deadline task should not be null";
                        task.setDeadline(userInput.substring(slashIndex + 4));
                        list.addTask(task);
                        int taskCount = list.size();
                        result = ui.showTaskAdded(task, taskCount);
                    }
                }
            } else if (commandName.equals("event")) {
                if (userInput.length() <= 5) {
                    result = "Please add a task after event";
                } else {
                    int fromIndex = userInput.indexOf("/from");
                    int toIndex = userInput.indexOf("/to");
                    assert fromIndex != -1 : "Invalid input format, missing /from";
                    assert toIndex != -1 : "Invalid input format, missing /to";
                    if (!validateDate(userInput.substring(fromIndex + 6, toIndex - 1))) {
                        result = "Invalid date format. Please use " + DATE_FORMAT + ".";

                    } else if (!validateDate(userInput.substring(toIndex + 4))) {
                        result = "Invalid date format. Please use " + DATE_FORMAT + ".";
                    } else {
                        String taskName = userInput.substring(6, fromIndex - 1);
                        task = new Event(taskName);
                        task.setDuration(userInput.substring(fromIndex + 6, toIndex - 1),
                                userInput.substring(toIndex + 4));
                        list.addTask(task);
                        int taskCount = list.size();
                        result = ui.showTaskAdded(task, taskCount);
                    }
                }
            } else if (commandName.equals("find")) {
                String keyword = ui.getKeyword(userInput);
                assert keyword != null && !keyword.trim().isEmpty()
                        : "Keyword for finding tasks should not be null or empty";
                result = ui.showFindResults(list, keyword);
            } else if (commandName.equals("help")) {
                result = ui.getHelpMessage();
            } else {
                result = "Commands: todo, deadline, event, delete, mark, unmark\n";
            }

            storage.rewriteFile();

        } catch (IOException e) {
            result = "Error while rewriting the file: " + e.getMessage();
        }

        return result;
    }

    /**
     * Main method to start the Hannah application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Hannah hannah = new Hannah();
        Scanner scanner = new Scanner(System.in);
        System.out.println(hannah.ui.showWelcomeMessage());

        while (true) {
            String userInput = scanner.nextLine();
            String response = hannah.run(userInput);
            System.out.println(response);

            if (userInput.equals("bye")) {
                break;
            }
        }
    }

    /**
     * Validates that a date string is in the format yyyy-MM-dd.
     *
     * @param date The date string to validate.
     * @return true if the date is valid, false otherwise.
     */
    private boolean validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String getResponse(String input) {
        return "Hannah heard: " + input;
    }

    public Ui getUi() {
        return ui;
    }

}
