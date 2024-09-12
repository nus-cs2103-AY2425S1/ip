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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        return "Here are the tasks in your list:\n"
                + IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.getTask(i).printTaskOnList())
                .collect(Collectors.joining("\n"));

    }

    private String handleFindCommand(Command command) {
        ArrayList<Task> matchingTasks = tasks.findTasks(command.getTaskDescription());

        return "Here are the matching tasks in your list:\n" +
                IntStream.range(0, matchingTasks.size())
                        .mapToObj(i -> (i + 1) + ". " + matchingTasks.get(i).printTaskOnList())
                        .collect(Collectors.joining("\n"));

    }

    private String handleMarkCommand(Command command) {
        try {
            int taskNumber = command.getTaskNumber();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task taskToMark = tasks.getTask(taskNumber - 1);
                taskToMark.markTask();
                storage.saveTasksToFile(tasks.getAllTasks());
                return "Nice! I've marked this task as done:\n" + taskToMark.printTaskOnList();
            } else {
                return "Invalid task format. Please enter a valid task.";
            }
        } catch (NumberFormatException e) {
            return "Invalid task format. Please enter a valid task.";
        }
    }

    private String handleUnmarkCommand(Command command) {
        try {
            int taskNumber = command.getTaskNumber();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task taskToUnmark = tasks.getTask(taskNumber - 1);
                taskToUnmark.unmarkTask();
                return "OK, I've marked this task as not done yet:\n" + taskToUnmark.printTaskOnList();
            } else {
                return "Please enter a valid task number.";
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid task number.";
        }
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
        try {
            int taskNumber = command.getTaskNumber();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task taskToDelete = tasks.getTask(taskNumber - 1);
                tasks.removeTask(taskNumber - 1);
                storage.saveTasksToFile(tasks.getAllTasks());
                return "Noted. I've removed this task:\n"
                        + "Now you have " + tasks.size() + " tasks in the list.";
            } else {
                return "Please enter a valid task number.";
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid task number.";
        }
    }


    public String showInvalidCommand() {
        return "Invalid command. Try again.";
    }

}