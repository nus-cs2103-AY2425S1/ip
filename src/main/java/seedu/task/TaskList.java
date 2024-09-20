package seedu.task;

import java.util.ArrayList;
import seedu.parser.BobException;
import seedu.storage.Storage;
import seedu.ui.Formatter;

/**
 * The {@code TaskList} class manages a list of tasks and provides methods to add, list, mark as done, unmark,
 * delete, and find tasks. It also interacts with the {@code Formatter} class to handle task-related UI output
 * and the {@code Storage} class to handle saving and loading tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Formatter formatter = new Formatter();

    /**
     * Adds a task to the task list and returns the formatted UI response.
     *
     * @param t The task to be added.
     * @return A formatted string indicating the task was added.
     */
    public String addTask(Task t) {
        assert t != null : "Task should not be null";
        this.tasks.add(t);
        return this.formatter.addTaskUi(t, this.tasks.size());
    }

    /**
     * Lists all tasks currently in the task list. If the task list is empty,
     * a message indicating no tasks will be returned.
     *
     * @return A formatted string of all tasks in the task list.
     */
    public String listTasks() {
        return this.formatter.listTaskUi(this.tasks);
    }

    /**
     * Marks a task as done based on its position in the task list.
     *
     * @param num The index of the task to be marked as done (0-based index).
     * @return A formatted string indicating the task was marked as done.
     */
    public String markTaskAsDone(int num) {
        assert num >= 0 && num < this.tasks.size() : "Index out of bounds";
        Task t = this.tasks.get(num);
        t.markAsDone();
        return this.formatter.markTaskAsDoneUi(t);
    }

    /**
     * Unmarks a task as not done based on its position in the task list.
     *
     * @param num The index of the task to be unmarked (0-based index).
     * @return A formatted string indicating the task was unmarked.
     */
    public String unmarkTaskAsDone(int num) {
        assert num >= 0 && num < this.tasks.size() : "Index out of bounds";
        Task t = this.tasks.get(num);
        t.markAsNotDone();
        return this.formatter.unmarkTaskAsDoneUi(t);
    }

    /**
     * Deletes a task from the task list based on its position and returns the updated list.
     *
     * @param num The index of the task to be deleted (0-based index).
     * @return A formatted string indicating the task was deleted.
     */
    public String deleteTask(int num) {
        assert num >= 0 && num < this.tasks.size() : "Index out of bounds";
        Task t = this.tasks.get(num);
        this.tasks.remove(num);
        return this.formatter.deleteTaskUi(t, this.tasks.size());
    }

    /**
     * Adds a {@code ToDo} task to the task list and returns the formatted UI response.
     *
     * @param description The description of the ToDo task.
     * @return A formatted string indicating the task was added.
     * @throws BobException If a duplicate task is found.
     */
    public String addToDo(String description) throws BobException {
        assert description != null && !description.isEmpty() : "ToDo description should not be null or empty";
        Task t = new ToDo(description);
        if (checkDuplicate(t)) {
            return "";
        }
        return this.addTask(t);
    }

    /**
     * Adds a {@code Deadline} task to the task list and returns the formatted UI response.
     *
     * @param description The description of the Deadline task.
     * @param end The end date of the Deadline task.
     * @return A formatted string indicating the task was added.
     * @throws BobException If a duplicate task is found.
     */
    public String addDeadline(String description, String end) throws BobException {
        assert description != null && !description.isEmpty() : "Deadline description should not be null or empty";
        assert end != null && !end.isEmpty() : "Deadline end time should not be null or empty";
        Task t = new Deadline(description, end);
        if (checkDuplicate(t)) {
            return "";
        }
        return this.addTask(t);
    }

    /**
     * Adds an {@code Event} task to the task list and returns the formatted UI response.
     *
     * @param description The description of the Event task.
     * @param start The start date of the Event task.
     * @param end The end date of the Event task.
     * @return A formatted string indicating the task was added.
     * @throws BobException If a duplicate task is found.
     */
    public String addEvent(String description, String start, String end) throws BobException {
        assert description != null && !description.isEmpty() : "Event description should not be null or empty";
        assert start != null && !start.isEmpty() : "Event start time should not be null or empty";
        assert end != null && !end.isEmpty() : "Event end time should not be null or empty";
        Task t = new Event(description, start, end);
        if (checkDuplicate(t)) {
            return "";
        }
        return this.addTask(t);
    }

    /**
     * Saves all tasks in the task list to the specified storage.
     *
     * @param s The storage where tasks will be saved.
     */
    public void saveTasks(Storage s) {
        assert s != null : "Storage should not be null";
        s.prepareSave();
        for (Task t : this.tasks) {
            assert t != null : "Task in the task list should not be null";
            s.saveTask(t.toSave());
        }
    }

    /**
     * Finds tasks by name in the task list and returns a formatted list of matching tasks.
     *
     * @param name The name or part of the name to search for.
     * @return A formatted string of tasks that match the search term.
     */
    public String findTasks(String name) {
        assert name != null && !name.isEmpty() : "Search keyword should not be null or empty";
        ArrayList<Task> temp = new ArrayList<>();
        this.tasks.stream().filter((t) -> containsName(t, name)).forEach(temp::add);
        return formatter.listTaskUi(temp);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Checks if a task's description contains the specified name.
     *
     * @param t The task to check.
     * @param name The name to check for in the task's description.
     * @return {@code true} if the task description contains the name; {@code false} otherwise.
     */
    public boolean containsName(Task t, String name) {
        String lowerCaseDescription = t.getDescription().toLowerCase();
        String lowerCaseName = name.toLowerCase();
        return lowerCaseDescription.contains(lowerCaseName);
    }

    /**
     * Checks if a task already exists in the task list.
     *
     * @param newTask The task to check for duplicates.
     * @return {@code true} if a duplicate task is found; {@code false} otherwise.
     * @throws BobException If a duplicate task is found.
     */
    public boolean checkDuplicate(Task newTask) throws BobException {
        for (Task t : this.tasks) {
            if (t.equals(newTask)) {
                throw new BobException("Duplicate task found!!");
            }
        }
        return false;
    }
}
