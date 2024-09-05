package gutti;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Ui} class handles interactions with the user.
 * It provides methods to display messages and prompts.
 */
public class Ui {

    /**
     * Displays an error message based on the provided {@code GuttiException}.
     *
     * @param e The exception containing the error message.
     */
    public String generateError(GuttiException e) {
        return e.getMessage();
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The {@code TaskList} containing the tasks to display.
     */
    public String showTaskList(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        return sb.toString();
    }


}
