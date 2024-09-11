package screwllum.tasks;

import screwllum.utils.Ui;
import screwllum.exception.InvalidIndexException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a manager of a list of tasks, providing functionality to modify and access the task list.
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
     * @throws InvalidIndexException If the index provided in the command (e.g., for toggle or delete) is out of bounds.
     */
    public void execute(List<String> tokens, Ui ui) throws InvalidIndexException {
        List<Task> list = null;
        switch (tokens.get(0)) {
        case "toggle":
            try {
                taskList.get(Integer.parseInt(tokens.get(1)) - 1).toggleStatus();
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException(tokens.get(1)); 
            }
            break;
        case "delete":
            try {
                list = new ArrayList<>(taskList);
                taskList.remove(Integer.parseInt(tokens.get(1)) - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException(tokens.get(1));
            }
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
        }
        if (list == null) {
            ui.showMessage(tokens, taskList);
        } else {
            ui.showMessage(tokens, list);
        }
    }
    
    public List<Task> getTaskList() {
        return taskList;
    }
}
