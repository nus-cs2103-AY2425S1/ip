package gopher.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gopher.storage.TaskManager;

/**
 * Represents TaskList that tracks user input tasks.
 * Supports functionalities such as:
 *     1. Add/Delete task.
 *     2. Mark/Unmark task as done.
 *     3. Display tasks as list.
 *     4. Find tasks based on keywords.
 *     5. Get task by number.
 *     6. Get size of the list.
 */
public class TaskList {
    /**
     * Tasks tracked by the TaskList
     */
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the given task to the task list.
     * Triggers the TaskManager to update the local saved tasks.
     *
     * @param task task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
        TaskManager.saveTasks(tasks);
    }

    /**
     * Deletes the task with the given task number from the task list.
     * Triggers the TaskManager to update the local saved tasks.
     *
     * @param taskNumbers numbers of the tasks to be deleted
     */
    public void delete(int... taskNumbers) {
        // Map task number to actual task in the taskList
        Task[] deleteTaskList = Arrays
                .stream(taskNumbers)
                .mapToObj(this::getTask)
                .toArray(Task[]::new);

        // Delete tasks by reference
        // Do not use task number for deletion directly because
        // items in ArrayList will shift left after deletion
        // causing the indexes of the items to be messed up
        for (Task task: deleteTaskList) {
            tasks.remove(task);
        }
        TaskManager.saveTasks(tasks);
    }

    /**
     * Finds tasks whose description contains the given keyword.
     *
     * @param keyword keyword used to search for tasks
     * @return TaskList object containing all the matching tasks
     */
    public TaskList find(String keyword) {
        // Define regex pattern based on the given keyword
        ArrayList<Task> matchedTasks = new ArrayList<>();
        Pattern keywordPattern = Pattern.compile(keyword,
                Pattern.CASE_INSENSITIVE);

        // Search task whose String representation matches regex pattern
        for (Task task : tasks) {
            Matcher keywordMatcher = keywordPattern.matcher(task.toString());
            if (keywordMatcher.find()) {
                matchedTasks.add(task);
            }
        }

        return new TaskList(matchedTasks);
    }

    /**
     * Marks the task with the given task number as done.
     * Triggers the TaskManager to update the local saved tasks.
     *
     * @param taskNumbers numbers of the tasks to be marked as done
     */
    public void markAsDone(int... taskNumbers) {
        for (int taskNumber: taskNumbers) {
            Task task = getTask(taskNumber);
            task.markAsDone();
        }
        TaskManager.saveTasks(tasks);
    }

    /**
     * Marks the task with the given task number as not done.
     * Triggers the TaskManager to update the local saved tasks.
     *
     * @param taskNumbers numbers of the tasks to be marked as not done
     */
    public void markAsUndone(int... taskNumbers) {
        for (int taskNumber: taskNumbers) {
            Task task = getTask(taskNumber);
            task.markAsNotDone();
        }
        TaskManager.saveTasks(tasks);
    }

    /**
     * Gets the task with the given task number.
     *
     * @param taskNumber number of the task wanted
     * @return task with the specified number
     */
    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Gets the number of tasks on the task list.
     *
     * @return number of tasks on the list
     */
    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        // Show no task message to user if task list is empty
        if (tasks.isEmpty()) {
            return "Good job! There's no pending tasks to be done!";
        }

        // List out the tasks if list not empty
        StringBuilder list = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            String message = String.format("%d. %s",
                    i,
                    tasks.get(i - 1));
            list.append(message);
            if (i <= tasks.size() - 1) {
                list.append("\n");
            }
        }
        return list.toString();
    }
}
