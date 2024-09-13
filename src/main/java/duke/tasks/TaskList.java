package duke.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.data.TaskDataBase;
import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskInDatabaseException;
import duke.exceptions.TaskNotFoundException;

/**
 * This class represents a list containing tasks.
 * It provides methods to add, mark, unmark and print tasks in the list.
 */
public class TaskList {
    private static TaskList tasks;
    private List<Task> listOfTasks;

    protected TaskList() throws IOException, InvalidDateException {
        listOfTasks = TaskDataBase.load();
        assert listOfTasks != null : "Task list should not be null after loading.";
    }

    /**
     * Initializes the `TaskList` if it hasn't been initialized yet.
     *
     * @return The initialized `TaskList` instance.
     */
    public static TaskList init() throws InvalidTaskInDatabaseException {
        try {
            if (tasks == null) {
                tasks = new TaskList();
            }
            return tasks;
        } catch (IOException e) {
            throw new InvalidTaskInDatabaseException();
        } catch (InvalidDateException e) {
            throw new InvalidTaskInDatabaseException();
        }
    }

    /**
     * Adds new task to todolist.
     *
     * @param task The name of task to be added.
     */
    public String addTask(Task task) {
        listOfTasks.add(task);
        try {
            TaskDataBase.save(listOfTasks);
            assert listOfTasks.contains(task) : "Task should have been added to the list.";
            return "Got it! I've added this task:" + "\n" + "  " + task.toString() + "\n"
                    + "Now you have " + listOfTasks.size() + " tasks in the list.";
        } catch (IOException e) {
            return "There is an error saving task into database ( ;´ - `;);";
        }
    }

    /**
     * Marks specific task as done.
     *
     * @param index The task number to be marked.
     * @return Description of task marked returned.
     */
    public String markTask(int index) throws TaskNotFoundException {
        try {
            if (index > 0 && index <= listOfTasks.size()) {
                Task task = listOfTasks.get(index - 1);
                assert task != null : "Task to be marked as done should not be null.";
                task.markAsDone();
                TaskDataBase.save(listOfTasks);
                return "Nice! I've marked this task as done:\n" + "  " + task;
            } else {
                throw new TaskNotFoundException();
            }
        } catch (IOException e) {
            return "There is an error saving task into database ( ;´ - `;);";
        }
    }

    /**
     * Marks specific task as not done.
     *
     * @param index The task number to be marked.
     * @return Description of task unmarked returned.
     */
    public String unmarkTask(int index) throws TaskNotFoundException {
        try {
            if (index > 0 && index <= listOfTasks.size()) {
                Task task = listOfTasks.get(index - 1);
                assert task != null : "Task to be unmarked as done should not be null.";
                TaskDataBase.save(listOfTasks);
                task.markAsNotDone();
                return "OK! I've marked this task as not done yet:\n" + "  " + task;
            } else {
                throw new TaskNotFoundException();
            }
        } catch (IOException e) {
            return "There is an error saving task into database ( ;´ - `;);";
        }
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted (1-based index).
     * @throws TaskNotFoundException If the index is out of bounds, meaning no task exists at the specified index.
     */
    public String deleteTask(int index) throws TaskNotFoundException {
        try {
            if (index > 0 && index <= listOfTasks.size()) {
                Task removedTask = listOfTasks.remove(index - 1);
                assert removedTask != null : "Task should have been removed.";
                TaskDataBase.save(listOfTasks);
                return "Noted! I've removed this task:" + "\n" + "  "
                        + removedTask + "\n" + "Now you have "
                        + listOfTasks.size() + " tasks in the list.";
            } else {
                throw new TaskNotFoundException();
            }
        } catch (IOException e) {
            return "There is an error saving task into database ( ;´ - `;);";
        }
    }

    /**
     * Print all tasks added to the list.
     *
     * @return The description of all tasks added.
     */
    public String printList() {
        if (listOfTasks.isEmpty()) {
            return " There is currently no task in your list";
        }
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list:" + "\n");
        for (int i = 0; i < listOfTasks.size(); i++) {
            tasks.append(i + 1).append(".").append(listOfTasks.get(i).toString());
            if (i < listOfTasks.size() - 1) {
                tasks.append("\n");
            }
        }
        return tasks.toString();
    }

    /**
     * Prints all tasks matching user input.
     */
    public String findTask(String keyword) throws TaskNotFoundException {
        List<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            throw new TaskNotFoundException();
        }

        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append((i + 1) + ". " + matchingTasks.get(i) + "\n");
        }
        return result.toString();
    }
}
