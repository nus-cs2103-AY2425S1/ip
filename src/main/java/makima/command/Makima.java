package makima.command;

import javafx.application.Platform;
import makima.io.FileManager;
import makima.task.Task;

import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Chatbot interface
 */
public class Makima {

    public static final String FAREWELL = "Baibai. Hope to see you soon! ^_^\n";
    public static final Scanner SC = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();

    private State state;

    /**
     * Initializes a new instance of a chatbot.
     */
    public Makima() {
        if (!FileManager.loadFile(this)) {
            exit();
        }
        state = new WaitingState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void exit() {
        Platform.exit();
    }

    public String getResponse(String input) {
        System.out.print(input);
        return state.getResponse(input, this);
    }

    /**
     * Returns a formatted string representing all tasks
     *
     * @return String representing all currently tracked tasks
     */
    public String getTaskList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i + 1).append(":").append(tasks.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Mark the selected task
     * @param idx index of task to mark
     */
    public void mark(int idx) {
        tasks.get(idx).mark();
        FileManager.saveFile(this);
    }

    /**
     * Unmark the selected task
     * @param idx index of task to unmark
     */
    public void unmark(int idx) {
        tasks.get(idx).unmark();
        FileManager.saveFile(this);
    }

    /**
     * Deletes the selected task
     * @param idx index of task to delete
     */
    public void delete(int idx) {
        tasks.remove(idx);
    }

    /**
     * Checks if the provided index is valid (1-indexed)
     * @param idx index
     * @return true if the index is valid
     */
    public boolean isValidTaskListIndex(int idx) {
        return (idx >= 0) && (idx < tasks.size());
    }

    public void addTask(Task task) {
        tasks.add(task);
        FileManager.saveFile(this);
    }

    /**
     * Returns a string representing the lists of current tasks
     * for writing to save file
     *
     * @return current tasks
     */
    public String convertTaskstoFileString() {
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            output.append(task.toFileString());
        }
        return output.toString();
    }

    /**
     * Returns a string containing the tasks whose name contains the search term
     *
     * @param search Case sensitive search term
     * @return formatted string containing all tasks.
     */
    public String getTasksWithTerm(String search) {
        StringBuilder result = tasks.stream().filter(task -> task.match(search))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        return !result.isEmpty()
                ? result.toString()
                : "No items matched the search!";
    }

    public static void main(String[] args) {
        new Makima();
    }



}
