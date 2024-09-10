package mahesh.util;

import java.util.ArrayList;

import mahesh.task.Task;

/**
 * Represents a list of tasks.
 * Provides methods to add, delete, mark, unmark, find, and print tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param list the list of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list and returns a response indicating the task was added.
     *
     * @param task the task to be added to the list
     * @return a String response indicating the task was added
     */
    public String addToList(Task task) {
        this.list.add(task);
        return Ui.printTaskAdded(task, this.list.size());
    }

    /**
     * Deletes a task from the list at the specified index and returns a response indicating the task was deleted.
     *
     * @param index the index of the task to be removed from the list
     * @return a String response indicating the task was deleted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public String deleteFromList(int index) {
        try {
            Task task = this.list.get(index);
            this.list.remove(index);
            return Ui.printTaskDeleted(task, this.list.size());
        } catch (IndexOutOfBoundsException err) {
            return Ui.printNoSuchTaskErr(this.list.size());
        }
    }

    /**
     * Marks a task as done at the specified index and returns a response indicating the task was marked as done.
     *
     * @param index the index of the task to be marked as done
     * @return a String response indicating the task was marked as done
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public String markTaskAsDone(int index) {
        try {
            Task task = this.list.get(index);
            task.markAsDone();
            return Ui.printMarkedAsDone(task);
        } catch (IndexOutOfBoundsException err) {
            return Ui.printNoSuchTaskErr(index);
        }
    }

    /**
     * Unmarks a task as done at the specified index and returns a response indicating the task was unmarked as done.
     *
     * @param index the index of the task to be unmarked as done
     * @return a String response indicating the task was unmarked as done
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public String unmarkTaskAsDone(int index) {
        try {
            Task task = this.list.get(index);
            task.unmarkAsDone();
            return Ui.printUnmarkedAsDone(task);
        } catch (IndexOutOfBoundsException err) {
            return Ui.printNoSuchTaskErr(index);
        }
    }

    /**
     * Finds tasks in the list that match the given search term and returns a response with the matching tasks.
     *
     * @param searchTerm the term to search for in the task descriptions
     * @return a String response with the matching tasks or an error message if no matches are found
     */
    public String findTaskInList(String searchTerm) {
        if (this.list.isEmpty()) {
            return Ui.printEmptyListErr();
        }
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Task task : this.list) {
            if (task.toString().substring(7).contains(searchTerm)) {
                count++;
                result.append(count).append(".").append(task).append("\n");
            }
        }
        if (count == 0) {
            return "No matching tasks found!";
        } else {
            StringBuilder response = new StringBuilder();
            response.append("Here are the matching tasks in your list:\n");
            response.append(result);
            return response.toString();
        }
    }

    /**
     * Prints all tasks in the list and returns a response with the tasks.
     * Returns an error message if the list is empty.
     *
     * @return a String response with all tasks in the list or an error message if the list is empty
     */
    public String printList() {
        if (this.list.isEmpty()) {
            return Ui.printEmptyListErr();
        }
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        int count = 1;
        for (Task task : this.list) {
            if (task == null) {
                break;
            }
            response.append(String.format("%d. %s\n", count++, task));
        }
        return response.toString();
    }

    /**
     * Converts the TaskList to a string format suitable for saving to a file.
     * The format includes the type indicator, completion status, description,
     * and any additional task-specific information.
     *
     * @return a string representation of the TaskList for file storage
     */
    public String toFileData() {
        StringBuilder fileData = new StringBuilder();
        for (Task task : list) {
            fileData.append(task.toFileEntry()).append(System.lineSeparator());
        }
        return fileData.toString();
    }
}