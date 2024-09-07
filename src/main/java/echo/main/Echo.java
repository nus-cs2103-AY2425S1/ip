package echo.main;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import echo.exception.EchoException;
import echo.parser.Parser;
import echo.storage.Storage;
import echo.task.Deadline;
import echo.task.Event;
import echo.task.Task;
import echo.task.ToDo;
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
     * Initialise the chatbot by loading the list of task in the txt file into the chatbot and
     * returning a string of greeting message
     *
     * @return a string of greeting message
     */
    public String initialise() {
        try {
            storage.loadStorage(parser, taskList);
            return ui.greet();
        } catch (IOException e) {
            return ui.printErrorMessage(e);
        }
    }

    /**
     * Handles the command when user types in "list"
     *
     * @return string of the list of tasks in taskList
     */
    public String handleListCommand() {
        return ui.listAllTask(this.taskList);
    }

    /**
     * Handles the command when user types in "mark"
     *
     * @param index the index of the task that is required to be marked in String
     * @return string of display message after marking a task
     */
    public String handleMarkCommand(String index) {
        Task task = taskList.markAndGetTask(index);
        return ui.printMarkMessage(task);
    }

    /**
     * Handles the command when user types in "unmark"
     *
     * @param index the index of the task that is required to be unmarked in String
     * @return string of display message after unmarking a task
     */
    public String handleUnmarkCommand(String index) {
        Task task = taskList.unmarkAndGetTask(index);
        return ui.printUnmarkMessage(task);
    }

    /**
     * Handles the command when users types in "todo"
     * Creates a ToDos object and is added to the taskList
     *
     * @param taskDescription description of the ToDos task
     * @return string of message after adding the ToDos object
     */
    public String handleToDoCommand(String taskDescription) {
        String[] description = parser.parseToDo(taskDescription);
        ToDo toDoTask = new ToDo(description);
        taskList.addTask(toDoTask);
        return ui.printAddTaskMessage(toDoTask, taskList);
    }

    /**
     * Handles the command when users types in "deadline".
     * Creates a Deadline object and is added to the taskList
     *
     * @param taskDescription description and deadline of the Deadline task
     * @return string of message after adding the Deadlines object
     */
    public String handleDeadlineCommand(String taskDescription) {
        String[] deadlineArray = parser.parseDeadline(taskDescription);
        Deadline deadlineTask = new Deadline(deadlineArray);
        taskList.addTask(deadlineTask);
        return ui.printAddTaskMessage(deadlineTask, taskList);
    }

    /**
     * Handles the command when users types in "event".
     * Creates an Event object and is added to the taskList
     *
     * @param taskDescription description, start time and end time of the Event task
     * @return string of message after adding the Events object
     */
    public String handleEventCommand(String taskDescription) {
        String[] eventArray = parser.parseEvent(taskDescription);
        Event eventTask = new Event(eventArray);
        taskList.addTask(eventTask);
        return ui.printAddTaskMessage(eventTask, taskList);
    }

    /**
     * Handles the command when user types in "delete"
     * Remove the task at that index provided from taskList
     *
     * @param index the index of the task that is required to be deleted in String
     * @return a string of message after deleting task
     */
    public String handleDeleteCommand(String index) {
        Task deletedTask = taskList.getTaskAndDelete(index);
        return ui.printDeleteMessage(deletedTask, taskList);
    }

    /**
     * Handles the command when user types in "find"
     *
     * @param keyword keyword contained in the task description
     * @return a string of list of all the task with the keyword
     */
    public String handleFindCommand(String keyword) {
        ArrayList<Task> arrayList = taskList.findTask(keyword);
        return ui.printFoundTask(arrayList);
    }

    /**
     * Handles the command when user types in "bye"
     *
     * @return a string of goodbye message
     */
    public String handleByeCommand() {
        try {
            this.storage.saveTaskList(this.taskList);
            return ui.bye();
        } catch (IOException e) {
            return ui.printErrorMessage(e);
        }
    }

    /**
     * Determines which command the user is giving and calls the respective functions to handle the command
     *
     * @param input command and description entered by users e.g. deadline work /by 11-12-2024 2345
     * @return string of output from chatbot
     * @throws EchoException if the command in the input is not valid
     */
    public String executeInput(String input) throws EchoException {
        try {
            String[] replyArray = parser.parseInput(input);
            String command = replyArray[0];
            String description = (replyArray.length > 1) ? replyArray[1] : "";

            switch (command) {
            case "list":
                return handleListCommand();
            case "mark":
                return handleMarkCommand(description);
            case "unmark":
                return handleUnmarkCommand(description);
            case "todo":
                return handleToDoCommand(description);
            case "deadline":
                return handleDeadlineCommand(description);
            case "event":
                return handleEventCommand(description);
            case "delete":
                return handleDeleteCommand(description);
            case "find":
                return handleFindCommand(description);
            case "bye":
                return handleByeCommand();
            default:
                throw new EchoException("Sorry! I don't get what you mean. Try again with the list of commands above.");
            }
        } catch (EchoException | DateTimeParseException | NumberFormatException e) {
            return ui.printErrorMessage(e);
        }
    }

    /**
     * Starts the Chat Bot and load data in text file into taskList
     * Executes input provided by users
     * Saves data in taskList into text file when user types in "bye"
     */
    public void run() {
        try {
            System.out.println(initialise());
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            while (!input.equals("bye")) {
                String output = executeInput(input);
                System.out.println(output);
                input = scanner.nextLine();
            }
            scanner.close();

            System.out.println(ui.bye());
            storage.saveTaskList(taskList);

        } catch (IOException e) {
            System.out.println(ui.printErrorMessage(e));
        }
    }

    public static void main(String[] args) {
        String filePath = "./src/main/data/echo.txt";
        Echo echo = new Echo(filePath);
        echo.run();
    }
}
