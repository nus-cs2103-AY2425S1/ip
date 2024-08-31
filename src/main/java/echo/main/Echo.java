package echo.main;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import echo.exception.EchoException;
import echo.parser.Parser;
import echo.storage.Storage;
import echo.task.Deadlines;
import echo.task.Events;
import echo.task.Task;
import echo.task.ToDos;
import echo.tasklist.TaskList;
import echo.ui.Ui;

/**
 * Represents the Chat Bot called Echo
 *
 * @author Ernest Lim
 */
public class Echo {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    private Parser parser;

    /**
     * Constructor to create Echo Object
     *
     * @param filePath String that contains the file path to store or load the list of task
     */
    public Echo(String filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
    }

    /**
     * Handles the command when user types in "list"
     * Prints out the list of tasks in taskList
     */
    public void handleListCommand() {
        this.ui.listAllTask(this.taskList);
    }

    /**
     * Handles the command when user types in "mark"
     * Prints out a display message after marking a task
     *
     * @param index the index of the task that is required to be marked in String
     */
    public void handleMarkCommand(String index) {
        Task task = taskList.markAndGetTask(index);
        ui.printMarkMessage(task);
    }

    /**
     * Handles the command when user types in "unmark"
     * Prints out a display message after unmarking a task
     *
     * @param index the index of the task that is required to be unmarked in String
     */
    public void handleUnmarkCommand(String index) {
        Task task = taskList.unmarkAndGetTask(index);
        ui.printUnmarkMessage(task);
    }

    /**
     * Handles the command when users types in "todo"
     * Creates a ToDos object and is added to the taskList
     * Prints out a display message after adding the ToDos object
     *
     * @param taskDescription description of the ToDos task
     */
    public void handleToDoCommand(String taskDescription) {
        String description = parser.parseToDos(taskDescription);
        ToDos toDoTask = new ToDos(description);
        taskList.addTask(toDoTask);
        ui.printAddTaskMessage(toDoTask, taskList);
    }

    /**
     * Handles the command when users types in "deadline".
     * Creates a Deadline object and is added to the taskList
     * Prints out a display message after adding the Deadline object
     *
     * @param taskDescription description and deadline of the Deadline task
     */
    public void handleDeadlineCommand(String taskDescription) {
        String[] deadlineArray = parser.parseDeadlines(taskDescription);
        String deadlineDescription = deadlineArray[0];
        String deadlineDate = deadlineArray[1];
        Deadlines deadlineTask = new Deadlines(deadlineDescription, deadlineDate);
        taskList.addTask(deadlineTask);
        ui.printAddTaskMessage(deadlineTask, taskList);
    }

    /**
     * Handles the command when users types in "event".
     * Creates an Event object and is added to the taskList
     * Prints out a display message after adding the Event object
     *
     * @param taskDescription description, start time and end time of the Event task
     */
    public void handleEventCommand(String taskDescription) {
        String[] eventArray = parser.parseEvents(taskDescription);
        String eventDescription = eventArray[0];
        String eventStartTime = eventArray[1];
        String eventEndTime = eventArray[2];
        Events eventTask = new Events(eventDescription, eventStartTime, eventEndTime);
        taskList.addTask(eventTask);
        ui.printAddTaskMessage(eventTask, taskList);
    }

    /**
     * Handles the command when user types in "delete"
     * Remove the task at that index provided from taskList
     * Prints out a display message after deleting the task
     *
     * @param index the index of the task that is required to be deleted in String
     */
    public void handleDeleteCommand(String index) {
        Task deletedTask = taskList.getTaskAndDelete(index);
        ui.printDeleteMessage(deletedTask, taskList);
    }

    /**
     * Handles the command when user types in "find"
     * Prints a list of all the task with the keyword
     *
     * @param keyword keyword contained in the task description
     */
    public void handleFindCommand(String keyword) {
        ArrayList<Task> arrayList = taskList.findTask(keyword);
        ui.printFoundTask(arrayList);
    }

    /**
     * Determines which command the user is giving and calls the respective functions to handle the command
     *
     * @param input command and description entered by users e.g. deadline work /by 11-12-2024 2345
     * @throws EchoException if the command in the input is not valid
     */
    public void executeInput(String input) throws EchoException {
        try {
            String[] replyArray = parser.parseInput(input);
            String command = replyArray[0];
            String description = (replyArray.length > 1) ? replyArray[1] : "";

            switch (command) {
            case "list":
                handleListCommand();
                break;
            case "mark":
                handleMarkCommand(description);
                break;
            case "unmark":
                handleUnmarkCommand(description);
                break;
            case "todo":
                handleToDoCommand(description);
                break;
            case "deadline":
                handleDeadlineCommand(description);
                break;
            case "event":
                handleEventCommand(description);
                break;
            case "delete":
                handleDeleteCommand(description);
                break;
            case "find":
                handleFindCommand(description);
                break;
            default:
                ui.showValidCommands();
                throw new EchoException("Sorry! I don't get what you mean. Try again with the list of commands above.");
            }
        } catch (EchoException | DateTimeParseException | NumberFormatException e) {
            ui.printErrorMessage(e);
        }
    }

    /**
     * Starts the Chat Bot and load data in text file into taskList
     * Executes input provided by users
     * Saves data in taskList into text file when user types in "bye"
     */
    public void run() {
        try {
            ui.greet();
            this.storage.loadStorage(this.parser, this.taskList);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            while (!input.equals("bye")) {
                this.executeInput(input);
                input = scanner.nextLine();
            }
            scanner.close();

            ui.bye();
            this.storage.saveTaskList(this.taskList);

        } catch (IOException e) {
            ui.printErrorMessage(e);
        }
    }

    public static void main(String[] args) {
        String filePath = "./src/main/data/echo.txt";
        Echo echo = new Echo(filePath);
        echo.run();
    }
}
