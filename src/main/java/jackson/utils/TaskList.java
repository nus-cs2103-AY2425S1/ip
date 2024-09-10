package jackson.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import jackson.exceptions.OutOfListException;
import jackson.tasks.Task;

/**
 * Class used to store tasks.
 */
public class TaskList {
    /**
     * Comparators to sort tasks by.
     * Sorting by time alone does not make sense because dates can be out of order.
     * Reverse the status comparator as unmarked status ([ ]) is placed before marked status [X] usually.
     */
    private static Comparator<Task> defaultComparator = (task1, task2) -> 0;
    private static Comparator<Task> comparatorByName =
            Comparator.comparing(Task::getName, Comparator.nullsLast(Comparator.naturalOrder()));
    private static Comparator<Task> comparatorByStartDateTime =
            Comparator.comparing(Task::getStartDateTime, Comparator.nullsLast(Comparator.naturalOrder()));;
    private static Comparator<Task> comparatorByEndDateTime =
            Comparator.comparing(Task::getEndDateTime, Comparator.nullsLast(Comparator.naturalOrder()));;
    private static Comparator<Task> comparatorByTaskType =
            Comparator.comparing(Task::getTaskType, Comparator.nullsLast(Comparator.naturalOrder()));;
    private static Comparator<Task> comparatorByMarkedUnmarked =
            Comparator.comparing(Task::getStatus, Comparator.nullsLast(Comparator.reverseOrder()));;

    // Array list to store classes
    private ArrayList<Task> tasks;

    /**
     * Constructs empty TaskList instance.
     * This is the main constructor.
     * @param expectedSize expected number of tasks to store.
     */
    public TaskList(int expectedSize) {
        this.tasks = new ArrayList<>(expectedSize);
    }

    /**
     * Constructs TaskList instance from given Tasks.
     * This is an overloaded constructor in case this functionality is needed.
     * @param tasks varargs of tasks to add to new TaskList instance.
     */
    public TaskList(Task... tasks) {
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
    }

    /**
     * Returns number of tasks in the list.
     * @return integer containing how many tasks are in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds task to the list and prints list adding message.
     * @param task {@code Task} object to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets a specified task according to index.
     * @param index index of task (from 0) to get.
     * @return {@code Task} object at index {@code index}.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Deletes Task from the list at specified index.
     * @param index Integer index to delete at.
     * @return {@code Task} object that was deleted.
     * @throws OutOfListException Thrown if invalid index is given, contains current task size.
     */
    public Task deleteTask(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfListException(String.valueOf(this.tasks.size()));
        }
        Task curr = this.tasks.remove(index);
        return curr;
    }

    /**
     * Marks task as completed at specified index.
     * @param index Integer index to mark task at.
     * @return {@code Task} object that was marked.
     * @throws OutOfListException Thrown if invalid index is given, contains current task size.
     */
    public Task mark(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfListException(String.valueOf(this.tasks.size()));
        }
        Task curr = this.tasks.get(index);
        curr.mark();
        return curr;
    }

    /**
     * Returns tasks that have names that contain keywords.
     * @param keywords String of keyword(s) to search for.
     * @return {@code ArrayList} of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keywords) {
        ArrayList<Task> filtered = new ArrayList<>(this.tasks);
        filtered.removeIf(x -> !x.getName().contains(keywords));
        return filtered;
    }

    /**
     * Unmarks task as completed at specified index.
     * @param index Integer index to unmark task at.
     * @return {@code Task} object that was unmarked.
     * @throws OutOfListException Thrown if invalid index is given, contains current task size.
     */
    public Task unmark(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfListException(String.valueOf(this.tasks.size()));
        }
        Task curr = this.tasks.get(index);
        curr.unmark();
        return curr;
    }

    /**
     * Sorts list by specified category.
     * Enums cannot be used here as we pass the argument directly as a String.
     * @param by Task attribute to sort for.
     * @param ascending true if sorting by ascending order, otherwise false.
     */
    public void sort(String by, boolean ascending) {
        System.out.printf("%s %b\n", by, ascending);
        if (this.tasks.size() >= 2) {
            Comparator<Task> selectedComparator;
            switch (by) {
            case "name":
                selectedComparator = comparatorByName;
                break;
            case "startdatetime":
                selectedComparator = comparatorByStartDateTime;
                break;
            case "enddatetime":
                selectedComparator = comparatorByEndDateTime;
                break;
            case "status":
                selectedComparator = comparatorByMarkedUnmarked;
                break;
            case "tasktype":
                selectedComparator = comparatorByTaskType;
                break;
            default:
                selectedComparator = defaultComparator;
            }

            // since comparators naturally sort by descending order, reverse the comparator
            // if ascending order is specified.
            if (!ascending) {
                selectedComparator = selectedComparator.reversed();
            }
            this.tasks.sort(selectedComparator);
        }
    }

    /**
     * Returns string representation of the list.
     * Includes task index, task type and whether it is marked or unmarked.
     */
    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "Nothing in list lah!";
        } else {
            StringBuilder output = new StringBuilder();
            Task curr;
            for (int i = 0; i < tasks.size(); i++) {
                curr = tasks.get(i);
                output.append(String.format("%d. %s\n", i + 1, curr));
            }
            return output.toString().strip();
        }
    }
}
