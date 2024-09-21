package screwllum.utils;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import screwllum.tasks.Task;

/**
 * Represents a front-end CLI UI for interactions with users. Essentially, a wrapper for a scanner with methods that
 * utilise multiple System.out.println calls.
 */
public class Ui {
    public String showWelcome(String message) {
        return message;
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
        assert tokens != null : "Similar to task manager, there should be tokens at this stage";
        String response = "";
        switch (tokens.get(0)) {
        case "bye":
            response = getByeResponse();
            break;
        case "list":
            response = getListResponse(taskList);
            break;
        case "archive":
            response = getArchiveResponse();
            break;
        case "toggle":
            response = getToggleResponse(taskList, tokens);
            break;
        case "delete":
            response = getDeleteResponse(taskList, tokens);
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            response = getNewTaskResponse(taskList);
            break;
        case "find":
            response = getFindResponse(taskList, tokens);
            break;
        default:
            // No response
        }
        return response;
    }

    private String getArchiveResponse() {
        return "Alright, I have archived your tasks in a file called archive.txt.";
    }

    private List<Task> filter(String keywords, List<Task> taskList) {
        List<Task> filteredList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (currentTask.getDesc().contains(keywords)) {
                filteredList.add(currentTask);
            }
        }
        return filteredList;
    }

    private String getListResponse(List<Task> taskList) {
        String response = "";
        if (taskList.isEmpty()) {
            response = "There are no tasks!";
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                response += (String.format("%s. %s\n", i + 1, taskList.get(i).toString()));
            }
        }
        return response;
    }

    private String getByeResponse() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
        return "It was my pleasure, good bye!";
    }

    private String getToggleResponse(List<Task> taskList, List<String> tokens) {
        return "I have toggled the status of this task:\n"
                + taskList.get(Integer.parseInt(tokens.get(1)) - 1).toString();
    }

    private String getDeleteResponse(List<Task> taskList, List<String> tokens) {
        return "I have deleted this task:\n" + taskList.get(Integer.parseInt(tokens.get(1)) - 1).toString();
    }

    private String getNewTaskResponse(List<Task> taskList) {
        return "I have added the following task:\n"
                + taskList.get(taskList.size() - 1).toString()
                + "\nNow you have " + taskList.size() + " tasks";
    }

    private String getFindResponse(List<Task> taskList, List<String> tokens) {
        List<Task> filteredList = filter(tokens.get(1), taskList);
        return "find " + tokens.get(1) + "\n" + getListResponse(filteredList);
    }
}
