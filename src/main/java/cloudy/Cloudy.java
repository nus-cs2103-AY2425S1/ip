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

        // Ensure assertions are being used.
        // assert false : "Assertions are being verified";

        assert filePath != null : "File path should not be null";
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(Storage.loadTasksFromFile());
            assert tasks != null : "Loaded tasks should not be null";
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

        assert command != null : "Command should not be null";
        assert command.getType() != null : "Command type should not be null";

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
        assert tasks != null : "TaskList should not be null";

        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1)
                    .append(". ")
                    .append(tasks.getTask(i).printTaskOnList())
                    .append("\n");
        }
        return output.toString();
    }

    private String handleFindCommand(Command command) {
        assert command!= null : "Command should not be null";
        try {
            String searchDescription = command.getTaskDescription();
            if (searchDescription == null) {
                return showInvalidCommand();
            }
            ArrayList<Task> matchingTasks = tasks.findTasks(searchDescription);

            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");

            for (int i = 0; i < matchingTasks.size(); i++) {
                output.append(i + 1)
                        .append(". ")
                        .append(matchingTasks.get(i).printTaskOnList())
                        .append("\n");
            }
            return output.toString();
        } catch (Exception e) {
            return "An error occurred while processing the find command.";
        }


    }

    private String handleMarkCommand(Command command) {
        assert command!= null : "Command should not be null";
      
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
        assert command!= null : "Command should not be null";

        int taskNumber = command.getTaskNumber();
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            return "Please enter a valid task number.";
        }
      
        Task taskToUnmark = tasks.getTask(taskNumber - 1);
        taskToUnmark.unmarkTask();
        return "OK, I've marked this task as not done yet:\n" + taskToUnmark.printTaskOnList();

    }

    private String handleTodoCommand(Command command) {
        assert command!= null : "Command should not be null";
        String taskDescription = command.getTaskDescription();
        Task newTask = new Todo(taskDescription, false);
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        return "Got it. I've added this task:\n"
                + newTask.printTaskOnList() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    private String handleDeadlineCommand(Command command) {
        assert command!= null : "Command should not be null";
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
        assert command!= null : "Command should not be null";
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
        assert command!= null : "Command should not be null";

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