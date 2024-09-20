package screwllum.tasks;

import java.util.ArrayList;
import java.util.List;

import screwllum.exception.InvalidIndexException;
import screwllum.utils.Ui;

/**
 * Represents a manager of a list of tasks, providing functionality to execute commands, and
 * modify and access the task list.
 */
public class TaskManager {
    private List<Task> taskList;

    public TaskManager(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskManager() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Executes the command represented by the tokens and updates the task list accordingly.
     * Handles commands such as "toggle", "delete", "todo", "deadline", and "event".
     * Each command modifies the task list and shows the corresponding message using the provided Ui.
     *
     * @param tokens The parsed command tokens that determine the action to be executed.
     * @param ui The UI object responsible for displaying messages.
     * @return A string representing the results of the action that was executed
     * @throws InvalidIndexException If the index provided in the command (e.g., for toggle or delete) is out of bounds.
     */
    public String execute(List<String> tokens, Ui ui) throws InvalidIndexException {
        assert tokens != null : "If parser works properly, there should always be a List of tokens";
        List<Task> list = null; // This is for handling delete
        String result = "";

        switch (tokens.get(0)) {
        case "toggle":
            executeToggle(tokens);
            break;
        case "delete":
            list = new ArrayList<>(taskList);
            executeDelete(tokens);
            break;
        case "todo":
            taskList.add(new ToDo(tokens.get(1)));
            break;
        case "deadline":
            taskList.add(new Deadline(tokens.get(1), tokens.get(2)));
            break;
        case "event":
            taskList.add(new Event(tokens.get(1), tokens.get(2), tokens.get(3)));
            break;
        default:
            // Do nothing
        }

        return list == null ? ui.showMessage(tokens, taskList) : ui.showMessage(tokens, list);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    private void executeToggle(List<String> tokens) throws InvalidIndexException {
        try {
            taskList.get(Integer.parseInt(tokens.get(1)) - 1).toggleStatus();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(tokens.get(1));
        }
    }

    private void executeDelete(List<String> tokens) throws InvalidIndexException {
        try {
            taskList.remove(Integer.parseInt(tokens.get(1)) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(tokens.get(1));
        }
    }
}
