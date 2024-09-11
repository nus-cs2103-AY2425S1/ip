package hypebot.tasklist;

import static hypebot.common.Messages.ERROR_DELETE_TASK_INDEX_OUT_OF_BOUNDS;
import static hypebot.common.Messages.ERROR_MARK_TASK_INDEX_OUT_OF_BOUNDS;
import static hypebot.common.Messages.ERROR_UNMARK_TASK_INDEX_OUT_OF_BOUNDS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import hypebot.task.Task;

/**
 * Represents the Tasklist containing all Task objects entered by user.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class Tasklist {
    private final ArrayList<Task> tasks;

    /**
     * Creates a dummy empty Tasklist.
     */
    public Tasklist() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a Tasklist with the saved Tasks from tasks.txt.
     *
     * @param tasks List containing Tasks processed from tasks.txt, encoded by TasklistEncoder.
     */
    public Tasklist(List<Task> tasks) {
        this();
        this.tasks.addAll(tasks);
    }

    /**
     * Returns the number of Tasks in the Tasklist.
     *
     * @return Number of Tasks in Tasklist.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Takes in an index (0-indexed) and returns the Task at corresponding index in the Tasklist.
     *
     * @param index Index of Task to be returned.
     * @return Task at corresponding index.
     */
    public Task getTaskByIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Takes in a Task and adds it to the Tasklist.
     *
     * @param task Task to be added to TASKS.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Takes in an index (0-indexed), removes it from the Tasklist and returns the Task
     * at the previously specified index.
     *
     * @param index Index of Task to be deleted.
     * @return Task removed from Tasklist, previously at given index.
     * @throws IndexOutOfBoundsException If index provided is < 0 or > TASKS.size() - 1.
     */
    public Task delete(int index) throws IndexOutOfBoundsException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(ERROR_DELETE_TASK_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Takes in an index (0-indexed), marks the corresponding Task as complete.
     *
     * @param index Index of Task to be marked complete.
     * @throws IndexOutOfBoundsException If index provided < 0 or > TASKS.size() - 1.
     */
    public void mark(int index) throws IndexOutOfBoundsException {
        try {
            tasks.get(index).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(ERROR_MARK_TASK_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Takes in an index (0-indexed), marks the corresponding Task as incomplete.
     *
     * @param index Index of Task to be marked incomplete.
     * @throws IndexOutOfBoundsException If index provided < 0 or > TASKS.size() - 1.
     */
    public void unmark(int index) throws IndexOutOfBoundsException {
        try {
            tasks.get(index).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(ERROR_UNMARK_TASK_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Takes in a LocalDate, returns all Tasks in TASKS that occur on given date.
     * Uses stream().filter().toList().
     *
     * @param date LocalDate object created by Parser from user input.
     * @return Tasklist containing Tasks occurring on given date.
     */
    public Tasklist getHappeningOn(LocalDate date) {
        List<Task> tasksOnDate = tasks.stream().filter(task -> task.isHappeningOn(date)).toList();
        return new Tasklist(tasksOnDate);
    }

    /**
     * Takes in a search query from user input parsed by Parser sent to a FindCommand,
     * then returns a Tasklist of Tasks with the keyword in the Task name.
     *
     * @param searchQuery Search query from user to search Tasks with the search query in the name.
     * @return Tasklist of Tasks containing search query in name.
     */
    public Tasklist getNameContains(Pattern searchQuery) {
        List<Task> tasksWithSearchQuery = tasks.stream().filter(
                task -> searchQuery.matcher(task.getName().toLowerCase()).find()
        ).toList();
        return new Tasklist(tasksWithSearchQuery);
    }

    /**
     * Creates a String object with all Tasks listed out numerically, in order of user insertion.
     *
     * @return String representation of Tasks in Tasklist in numerical insertion order.
     */
    @Override
    public String toString() {
        StringBuilder listMessage = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listMessage.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return listMessage.toString();
    }
}
