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
        if (tasks == null) {
            try {
                tasks = new TaskList();
            } catch (IOException | InvalidDateException e) {
                throw new InvalidTaskInDatabaseException();
            }
        }
        return tasks;
    }

    /**
     * Adds new task to todolist.
     *
     * @param task The name of task to be added.
     */
    public String addTask(Task task) {
        listOfTasks.add(task);
        String successMessage = "Got it! I've added this task:" + "\n" + "  " + task.toString() + "\n"
                + "Now you have " + listOfTasks.size() + " tasks in the list.";
        return saveTasksToDataBase(successMessage);
    }

    /**
     * Marks specific task as done.
     *
     * @param index The task number to be marked.
     * @return Description of task marked returned.
     */
    public String markTask(int index) throws TaskNotFoundException {

        Task task = getTaskByIndex(index);
        task.markAsDone();
        String successMessage = "Nice! I've marked this task as done:\n" + "  " + task;
        return saveTasksToDataBase(successMessage);
    }

    /**
     * Marks specific task as not done.
     *
     * @param index The task number to be marked.
     * @return Description of task unmarked returned.
     */
    public String unmarkTask(int index) throws TaskNotFoundException {
        Task task = getTaskByIndex(index);
        task.markAsNotDone();
        String successMessage = "OK! I've marked this task as not done yet:\n" + "  " + task;
        return saveTasksToDataBase(successMessage);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted (1-based index).
     * @throws TaskNotFoundException If the index is out of bounds, meaning no task exists at the specified index.
     */
    public String deleteTask(int index) throws TaskNotFoundException {
        Task removedTask = getTaskByIndex(index);
        listOfTasks.remove(removedTask);
        String successMessage = "Noted! I've removed this task:" + "\n" + "  "
                + removedTask + "\n" + "Now you have "
                + listOfTasks.size() + " tasks in the list.";
        return saveTasksToDataBase(successMessage);
    }

    /**
     * Print all tasks added to the list.
     *
     * @return The description of all tasks added.
     */
    public String printList() {
        if (listOfTasks.isEmpty()) {
            return "There is currently no task in your list";
        }
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < listOfTasks.size(); i++) {
            tasks.append(i + 1).append(". ").append(listOfTasks.get(i)).append("\n");
        }
        return tasks.toString().trim();
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

    private Task getTaskByIndex(int index) throws TaskNotFoundException {
        if (index > 0 && index <= listOfTasks.size()) {
            return listOfTasks.get(index - 1);
        } else {
            throw new TaskNotFoundException();
        }
    }

    private String saveTasksToDataBase(String successMessage) {
        try {
            TaskDataBase.save(listOfTasks);
            return successMessage;
        } catch (IOException e) {
            return "There is an error saving task into database";
        }
    }
}
