package sirpotato;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A custom class to hold the list of tasks
 * Contains methods to operate the task list
 */
public class TaskList {

    protected ArrayList<Task> toDoList;
    private Ui ui;

    /**
     * Initalises a tasklist with the given ArrayList of tasks
     * 
     * @param toDoList an ArrayList of tasks in the to-do list
     */
    public TaskList(ArrayList<Task> toDoList) {
        this.toDoList = toDoList;
        this.ui = new Ui();
    }

    /**
     * An alternate constructor for the tasklist if there
     * are no current tasks in the to-do list.
     */
    public TaskList() {
        this.toDoList = new ArrayList<Task>();
        this.ui = new Ui();
    }

    public ArrayList<Task> getList() {
        return toDoList;
    }

    /**
     * Marks a certain item in the task list as finished
     * 
     * @param itemNumber The item you wish to mark finished(starts with index 1 for item 1)
     */
    public void mark(int itemNumber) {
        toDoList.get(itemNumber).setCompletion(true);
        ui.displayMarkedItem(itemNumber, this);
    }

    /**
     * Unmarks a certain item in the task list as finished
     * 
     * @param itemNumber The item you wish to unmark finished(starts with index 1 for item 1)
     */
    public void unmark(int itemNumber) {
        toDoList.get(itemNumber).setCompletion(false);
        ui.displayUnmarkedItem(itemNumber, this);
    }

    /**
     * Adds a task to the tasklist
     * 
     * @param task The task you wish to add
     */
    public void addTask(Task task) {
        toDoList.add(task);
        ui.displayAddedTask(task, this);
    }

    /**
     * Deletes a task from the task list
     * 
     * @param taskNumber The number of the task you wish to delete
     * @param toDoList The ArrayList of the tasklist should you wish to specify
     */
    public void delete(int taskNumber, ArrayList<Task> toDoList) {
        // ui.displayDeletionMessage(toDoList.get(taskNumber), toDoList);
        toDoList.remove(taskNumber);
    }

    /**
     * Deletes a task from the task list
     * 
     * @param taskNumber The number of the task you wish to delete
     */
    public void delete(int taskNumber) {
        toDoList.remove(taskNumber);
    }

    /**
     * Returns the task (starts at index 0)
     * 
     * @return Task at that index(starting at 0)
     */
    public Task getTask(int itemNumber) {
        return this.toDoList.get(itemNumber);
    }

    public int getSizeOfList() {
        return this.toDoList.size();
    }

    /**
     * Returns a TaskList that contains the tasks sorted in the category specified
     * 
     * @param categoryToSortBy the category by which to sort, either by deadline, or by description
     * @return TaskList containing the tasks in sorted order 
     */
    public TaskList sortBy(String categoryToSortBy) {
        if (categoryToSortBy.equals("deadline")) {
            ArrayList<Task> deadlineTasks = toDoList.stream()
                                   .filter(Task::isDeadline)
                                   .collect(Collectors.toCollection(ArrayList::new));
            
            Collections.sort(deadlineTasks, Comparator.comparing(task -> ((Deadline) task).getByDate()));
            return new TaskList(new ArrayList<Task>(deadlineTasks));
        } else if (categoryToSortBy.equals("description")) {
            Collections.sort(toDoList, Comparator.comparing(Task::displayDescription));
            return this;
        }
        return new TaskList();
    }

}