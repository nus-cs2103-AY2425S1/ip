package patrick.tasklist;

import java.io.IOException;
import java.util.ArrayList;

import patrick.parser.Parser;
import patrick.storage.Storage;
import patrick.ui.Ui;

/**
 * The {@code TaskList} class manages a list of tasks, allowing operations such as adding, deleting,
 * marking as done or undone, and retrieving tasks from storage.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     *
     * @param taskList the list of tasks to initialize the {@code TaskList} with.
     * @throws Parser.PatrickException if the task list is empty.
     */
    public TaskList(ArrayList<Task> taskList) throws Parser.PatrickException {
        if (!taskList.isEmpty()) {
            this.taskList = taskList;
        } else {
            throw new Parser.PatrickException("Your Task List is empty!\n");
        }
    }

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Deletes a task from the task list based on the user input.
     *
     * @param input the user input containing the index of the task to be deleted.
     * @return a response message indicating the result of the operation.
     * @throws Parser.PatrickException if the input is invalid or the index is out of bounds.
     */
    public static String delete(String input) throws Parser.PatrickException {
        String response;
        String taskNo = input.replace("delete", "").trim();
        try {
            Integer.parseInt(taskNo);
        } catch (NumberFormatException e) {
            throw new Parser.PatrickException("Delete Task Details must be an integer");
        }
        int num = Integer.parseInt(taskNo);
        if (taskNo.isEmpty()) {
            throw new Parser.PatrickException("Delete Task Details cannot be empty!!");
        }
        if (num > Storage.getList().size()) {
            throw new Parser.PatrickException("Input task index is invalid. Please try again!!");
        }

        response = Ui.showDeleteItemMsg(num);
        Storage.deleteItem(num);
        try {
            Storage.writeToFile();
        } catch (IOException e) {
            response = Ui.THERE_IS_AN_ERROR + e.getMessage();
        }

        return response;
    }

    /**
     * Marks a task as done based on the user input.
     *
     * @param input the user input containing the index of the task to be marked as done.
     * @return a response message indicating the result of the operation.
     * @throws Parser.PatrickException if the input is invalid, the index is out of bounds,
     *     or the task is already marked as done.
     */
    public static String mark(String input) throws Parser.PatrickException {
        String response;
        String taskNo = input.replace("mark", "").trim();
        if (taskNo.isEmpty()) {
            throw new Parser.PatrickException("Task Number cannot be empty!!");
        }

        try {
            Integer.parseInt(taskNo);
        } catch (NumberFormatException e) {
            throw new Parser.PatrickException("Mark Task Details must be an integer");
        }

        int num = Integer.parseInt(taskNo);
        if (num > Storage.getList().size()) {
            throw new Parser.PatrickException("Invalid Task Number!!");
        }

        Task curr = Storage.getList().get(num - 1);
        if (curr.isDone) {
            throw new Parser.PatrickException("You cannot mark a completed task!!");
        }

        curr.markAsDone();
        response = "Nice! I've marked this task as done:\n  " + curr + "\n";
        try {
            Storage.writeToFile();
        } catch (IOException e) {
            return Ui.THERE_IS_AN_ERROR + e.getMessage();
        }

        return response;
    }

    /**
     * Marks a task as not done based on the user input.
     *
     * @param input the user input containing the index of the task to be marked as not done.
     * @return a response message indicating the result of the operation.
     * @throws Parser.PatrickException if the input is invalid, the index is out of bounds,
     *     or the task is already marked as not done.
     */
    public static String unmark(String input) throws Parser.PatrickException {
        String response;
        String taskNo = input.replace("unmark", "").trim();
        if (taskNo.isEmpty()) {
            throw new Parser.PatrickException("Task Number cannot be empty!!");
        }

        try {
            Integer.parseInt(taskNo);
        } catch (NumberFormatException e) {
            throw new Parser.PatrickException("Unmark Task Details must be an integer");
        }

        int num = Integer.parseInt(taskNo);
        if (num > Storage.getList().size()) {
            throw new Parser.PatrickException("Invalid Task Number!!");
        }

        Task curr = (Task) Storage.getList().get(num - 1);
        if (!curr.isDone) {
            throw new Parser.PatrickException("You cannot unmark an incomplete task!!");
        }

        curr.markAsUndone();
        response = "Nice! I've marked this task as not done yet:\n  " + curr + "\n";

        try {
            Storage.writeToFile();
        } catch (IOException e) {
            return Ui.THERE_IS_AN_ERROR + e.getMessage();
        }

        return response;
    }

    /**
     * Finds and displays tasks in the list that match the given keyword.
     *
     * @param input The user input containing the keyword to search for.
     * @return a response message indicating the result of the operation.
     * @throws Parser.PatrickException if the keyword is empty.
     */
    public static String findTask(String input) throws Parser.PatrickException {
        String response = null;
        int count = 0;
        String keyword = input.replace("find", "").trim();
        if (keyword.isEmpty()) {
            throw new Parser.PatrickException("Find keyword cannot be empty!");
        }

        for (int i = 0; i < Storage.getList().size(); i++) {
            if (Storage.getList().get(i).toString().contains(keyword)) {
                if (count == 0) {
                    response = "Here are the matching tasks in your list:\n";
                }
                count++;
                response += count + " " + Storage.getList().get(i).toString() + "\n";
            }
        }

        if (count == 0) {
            response = "There are no matching tasks in your list!";
        }

        return response;
    }

}
