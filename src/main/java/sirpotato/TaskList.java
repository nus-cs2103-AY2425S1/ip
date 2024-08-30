package sirpotato;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> toDoList;
    private Ui ui;

    public TaskList(ArrayList<Task> toDoList) {
        this.toDoList = toDoList;
        this.ui = new Ui();
    }

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
        ui.displayMarkedItem(itemNumber, this.toDoList);
    }

    /**
     * Unmarks a certain item in the task list as finished
     * 
     * @param itemNumber The item you wish to unmark finished(starts with index 1 for item 1)
     */
    public void unmark(int itemNumber) {
        toDoList.get(itemNumber).setCompletion(false);
        ui.displayUnmarkedItem(itemNumber, this.toDoList);
    }

    /**
     * Adds a task to the tasklist
     * 
     * @param task The task you wish to add
     */
    public void addTask(Task task) {
        toDoList.add(task);
        ui.displayAddedTask(task, this.toDoList);
    }

    /**
     * Deletes a task from the task list
     * 
     * @param taskNumber The number of the task you wish to delete
     * @param toDoList The ArrayList of the tasklist should you wish to specify
     */
    public void delete(int taskNumber, ArrayList<Task> toDoList) {
        ui.displayDeletionMessage(toDoList.get(taskNumber), toDoList);
        toDoList.remove(taskNumber);
    }

    /**
     * Deletes a task from the task list
     * 
     * @param taskNumber The number of the task you wish to delete
     */
    public void delete(int taskNumber) {
        ui.displayDeletionMessage(toDoList.get(taskNumber), this.toDoList);
        toDoList.remove(taskNumber);
    }

}