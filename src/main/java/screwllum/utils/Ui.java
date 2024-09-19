package screwllum.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import screwllum.tasks.Task;

/**
 * Represents a front-end CLI UI for interactions with users. Essentially, a wrapper for a scanner with methods that
 * utilise multiple System.out.println calls.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    
    public String getInput() {
        return sc.nextLine();
    }
    
    public String showWelcome() {
        return "Pleased to meet you";
    }

    /**
     * Displays an error message to the user, which is normally the description of a Screwllum Exception.
     *
     * @param message The error message to be shown.
     * @return The error message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns a string based on the command tokens and the current list of tasks.
     * Handles commands such as "bye", "list", "toggle", "delete", "todo", "deadline", and "event".
     *
     * @param tokens The parsed command tokens.
     * @param taskList The current list of tasks.
     * @return The response related to the requested command token
     */
    public String showMessage(List<String> tokens, List<Task> taskList) {
        String response = "";
        switch (tokens.get(0)) {
        case "bye":
            response = "It was my pleasure, good bye";
            System.exit(0);
            break; // No requirement for a break, only to remove checkstyle warnings
        case "list":
            response = printTaskList(taskList);
            break;
        case "toggle":
            response = "I have toggled the status of this task:\n"
                    + taskList.get(Integer.parseInt(tokens.get(1)) - 1).toString();
            break;
        case "delete":
            response = "I have deleted this task:\n" + taskList.get(Integer.parseInt(tokens.get(1)) - 1).toString();
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            response = "I have added the following task:\n"
                    + taskList.get(taskList.size() - 1).toString()
                    + "Now you have " + taskList.size() + " tasks";
            break;
        case "find":
            List<Task> tempList = new ArrayList<>();
            String keywords = tokens.get(1);
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                if (currentTask.getDesc().contains(keywords)) {
                    tempList.add(currentTask);
                }
            }
            response = "find " + keywords + "\n" + printTaskList(tempList);
            break;
        default:
            response = "I don't know what to do with this task";
        }
        return response;
    }
    
    private String printTaskList(List<Task> taskList) {
        String response = "";
        if (taskList.isEmpty()) {
            response = "There are no tasks for you!";
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                response += (String.format("%s. %s\n", i + 1, taskList.get(i).toString()));
            }
        }
        return response;
    }
}
