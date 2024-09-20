package astor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import astor.exception.AstorException;
import astor.exception.DeleteTaskOutOfRangeException;
import astor.exception.MarkTaskOutOfRangeException;
import astor.task.Task;


/**
 * Manages a list of tasks and provides functionalities to modify and access tasks.
 *
 * The TaskList class allows for operations such as adding, marking, unmarking, and deleting tasks.
 * It also provides methods to retrieve the list of tasks and their status.
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the string representation of all tasks.
     *
     * @return string information of all tasks
     */
    public String getTaskList() {
        int index = 1;
        StringBuilder s = new StringBuilder();
        for (Task task : taskList) {
            s.append("\n").append(index).append(". ").append(task.toString());
            index++;
        }
        return s.toString();
    }

    /**
     * Marks a task as completed.
     *
     * @param taskIndex the index of the task starting from 1 if the list is not empty
     * @param storage the data file
     * @return reply of the chatbot after marking tasks as done
     * @throws MarkTaskOutOfRangeException if an invalid taskIndex is given by user
     */
    public String markTaskDone(int taskIndex, Storage storage) throws MarkTaskOutOfRangeException {
        assert storage != null : "storage cannot be null";

        if (isWithinIndexRange(taskIndex)) {
            Task task = taskList.get(taskIndex - 1);
            if (task.isDone()) {
                return "This task is already done:\n" + taskIndex
                        + ". " + task;
            } else {
                task.markDone();
                storage.updateData(taskList);
                return "Nice! I've marked this task as done:\n" + taskIndex
                        + ". " + task;
            }
        } else {
            throw new MarkTaskOutOfRangeException(this.size());
        }
    }

    /**
     * Marks multiple tasks as done in the task list by their indices.
     *
     * <p>This method accepts a list of task indices, marks the corresponding tasks as done,
     * and returns a formatted string indicating which tasks have been marked done. Each task
     * is marked using the {@link #markTaskDone(int, Storage)} method, and the output is accumulated
     * and separated by double newlines. The trailing newline is removed before returning the result.
     *
     * @param storage   The storage object that contains the task list and persists changes.
     * @param indexList A variable number of integers representing the indices of the tasks to be marked as done.
     * @return A string summarizing which tasks were marked as done, each separated by double newlines.
     * @throws MarkTaskOutOfRangeException if any of the indices in {@code indexList} is out of range of the task list.
     */
    public String markListDone(Storage storage, int... indexList) throws MarkTaskOutOfRangeException {
        StringBuilder s = new StringBuilder();
        for (int i: indexList) {
            String currentOutput = markTaskDone(i, storage);
            s.append(currentOutput).append("\n\n");
        }
        return s.toString().trim();
    }


    /**
     * Marks a task as uncompleted.
     *
     * @param taskIndex the index of the task starting from 1 if the list is not empty
     * @param storage the data file
     * @return reply of the chatbot after marking tasks as done
     * @throws MarkTaskOutOfRangeException if an invalid taskIndex is given by user
     */
    public String unmarkTaskDone(int taskIndex, Storage storage) throws MarkTaskOutOfRangeException {
        assert storage != null : "storage cannot be null";

        if (isWithinIndexRange(taskIndex)) {
            String output = generateOutputUnmarkTask(taskIndex);
            storage.updateData(taskList);
            return output;
        } else {
            throw new MarkTaskOutOfRangeException(this.size());
        }
    }

    /**
     * Unmarks multiple tasks as not done in the task list by their indices.
     *
     * <p>This method accepts a list of task indices, unmarks the corresponding tasks as not done,
     * and returns a formatted string indicating which tasks have been unmarked. Each task is unmarked
     * using the {@link #unmarkTaskDone(int, Storage)} method, and the output is accumulated and
     * separated by double newlines. The trailing newline is removed before returning the result.
     *
     * @param storage   The storage object that contains the task list and persists changes.
     * @param indexList A variable number of integers representing the indices of the tasks to be unmarked as done.
     * @return A string summarizing which tasks were unmarked, each separated by double newlines.
     * @throws MarkTaskOutOfRangeException if any of the indices in {@code indexList} is out of range of the task list.
     */
    public String unmarkListDone(Storage storage, int... indexList) throws MarkTaskOutOfRangeException {
        StringBuilder s = new StringBuilder();
        for (int i: indexList) {
            String currentOutput = unmarkTaskDone(i, storage);
            s.append(currentOutput).append("\n\n");
        }
        return s.toString().trim();
    }

    public boolean isWithinIndexRange(int taskIndex) {
        return taskIndex >= 1 && taskIndex <= taskList.size();
    }

    /**
     * Generates the output message when unmarking a task as incomplete.
     * <p>
     * This method checks whether the task at the specified index is marked as completed.
     * If the task is completed, it marks the task as incomplete and returns a message
     * confirming this action. If the task is already marked as incomplete, it returns
     * a message indicating that no change is necessary.
     *
     * @param taskIndex the index of the task in the list (1-based index)
     * @return a message indicating whether the task was successfully marked as incomplete
     *         or was already unmarked
     */
    public String generateOutputUnmarkTask(int taskIndex) {
        Task task = taskList.get(taskIndex - 1);
        String output;
        if (task.isDone()) {
            task.markUndone();
            output = "OK! I've marked this task as not done yet:\n" + taskIndex
                    + ". " + task;
        } else {
            output = "This task is already marked as uncompleted:\n" + taskIndex
                    + ". " + task;
        }
        return output;
    }

    /**
     * Deletes tasks from taskList.
     *
     * @param taskIndex the index of the task starting from 1 if the list is not empty
     * @param storage the data file
     * @return reply of the chatbot after deleting a task
     * @throws AstorException if there are no tasks to delete or taskIndex provided is out of range
     */
    public String deleteTask(int taskIndex, Storage storage) throws AstorException {
        assert storage != null : "storage cannot be null";

        if (taskList.isEmpty()) {
            throw DeleteTaskOutOfRangeException.noTaskToDelete();
        }
        if (taskIndex >= 1 && taskIndex <= taskList.size()) {
            Task task = taskList.remove(taskIndex - 1);
            storage.updateData(taskList);
            return "Noted! I've removed this task:\n  "
                    + task + "\nNow you have " + taskList.size() + " tasks in the list.";
        } else {
            throw DeleteTaskOutOfRangeException.outOfRangeTaskToDelete(taskList.size());
        }
    }

    /**
     * Adds a new task to the list.
     *
     * This method adds the specified task to the task list and appends its data description to the storage file.
     *
     * @param task the task to add to the list
     * @param storage the storage object used to append data to the file
     * @return a string representation of the added task
     * @throws IOException if an I/O error occurs while appending to the file
     */
    public String addTask(Task task, Storage storage) throws IOException {
        assert task != null : "task cannot be null";
        assert storage != null : "storage cannot be null";

        String output = "";
        for (Task t: taskList) {
            if (t.equals(task)) {
                output = "The same task has already been created:\n "
                        + t.toString() + "\nBe more specific if you want to add the task!";
                return output;
            }
        }

        storage.appendToFile(task.dataDescription());
        taskList.add(task);
        output = "Got it. I've added this task:\n  "
                + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list.";
        return output;

    }

    public boolean checkDuplicates(Task task1, Task task2) {
        return task1.equals(task2);
    }

    /**
     * Finds the task that matches the description.
     *
     * @param descriptions a string of keywords to search for
     * @return a string that contains all the tasks that matches the description
     */
    public String matchesDescriptions(String... descriptions) {
        StringBuilder s = new StringBuilder();
        final int[] i = {1};
        taskList.stream()
                .filter(task -> Arrays.stream(descriptions).anyMatch(task::isIncluded))
                .forEach(task -> {
                    s.append("\n").append(i[0]++).append(". ").append(task.toString());
                });

        return s.toString();
    }

    public int size() {
        return taskList.size();
    }
}
