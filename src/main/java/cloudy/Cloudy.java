package cloudy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the main class for the Cloudy program.
 * Manages user input, task list operations, and file storage interactions.
 */
public class Cloudy {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initializes a new instance of the Cloudy program.
     *
     * @param filePath The file path to load and save tasks.
     */
    public Cloudy(String filePath) {

        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(Storage.loadTasksFromFile());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    public String showGreeting() {
        return "Hello! I'm Cloudy.\n What can I do for you today?";
    }

    /**
     * Processes input from the GUI and returns the corresponding response.
     * @param userInput The input entered by the user.
     * @return The response to be displayed in the GUI.
     */
    public String getResponse(String userInput) {
        Command command = parser.parseCommand(userInput);

        switch (command.getType()) {
            case "bye":
                return "Bye. Hope to see you again soon!";

            case "list":
                return showList(tasks);
            case "find":
                return handleFindCommand(command);

            case "mark":
                return handleMarkCommand(command);
            case "unmark":
                return handleUnmarkCommand(command);

            case "todo":
                return handleTodoCommand(command);
            case "deadline":
                return handleDeadlineCommand(command);
            case "event":
                return handleEventCommand(command);

            case "delete":
                return handleDeleteCommand(command);

            default:
                return showInvalidCommand();
        }
    }




    /**
     * Returns a string of the list of tasks in the user's task list.
     * @param tasks The TaskList containing all the tasks to be displayed.
     */
    public String showList(TaskList tasks) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1)
                    .append(". ")
                    .append(tasks.getTask(i).printTaskOnList())
                    .append("\n");
        }
        return output.toString();
    }

    private String handleFindCommand(Command command) {
        ArrayList<Task> matchingTasks = tasks.findTasks(command.getTaskDescription());

        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1)
                    .append(". ")
                    .append(tasks.getTask(i).printTaskOnList())
                    .append("\n");
        }
        return output.toString();
    }

    private String handleMarkCommand(Command command) {

        int taskNumber = command.getTaskNumber();
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            return "Please enter a valid task number.";
        }
        Task taskToMark = tasks.getTask(taskNumber - 1);
        taskToMark.markTask();
        storage.saveTasksToFile(tasks.getAllTasks());
        return "Nice! I've marked this task as done:\n" + taskToMark.printTaskOnList();

    }

    private String handleUnmarkCommand(Command command) {

        int taskNumber = command.getTaskNumber();
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            return "Please enter a valid task number.";
        }
        Task taskToUnmark = tasks.getTask(taskNumber - 1);
        taskToUnmark.unmarkTask();
        return "OK, I've marked this task as not done yet:\n" + taskToUnmark.printTaskOnList();

    }

    private String handleTodoCommand(Command command) {
        String taskDescription = command.getTaskDescription();
        Task newTask = new Todo(taskDescription, false);
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        return "Got it. I've added this task:\n"
                + newTask.printTaskOnList() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    private String handleDeadlineCommand(Command command) {
        String taskDescription = command.getTaskDescription();
        LocalDate deadline = command.getDeadline();
        Task newTask = new Deadline(taskDescription, deadline, false);
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        return "Got it. I've added this task:\n"
                + newTask.printTaskOnList() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    private String handleEventCommand(Command command) {
        String taskDescription = command.getTaskDescription();
        LocalDate startTime = command.getStartTime();
        LocalDate endTime = command.getEndTime();
        Task newTask = new Event(taskDescription, startTime, endTime, false);
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        return "Got it. I've added this task:\n"
                + newTask.printTaskOnList() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    private String handleDeleteCommand(Command command) {

        int taskNumber = command.getTaskNumber();
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            return "Please enter a valid task number.";
        }
        tasks.removeTask(taskNumber - 1);
        storage.saveTasksToFile(tasks.getAllTasks());
        return "Noted. I've removed this task:\n"
                + "Now you have " + tasks.size() + " tasks in the list.";

    }


    public String showInvalidCommand() {
        return "Invalid command. Try again.";
    }

}