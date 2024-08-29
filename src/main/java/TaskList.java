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

    public void mark(int itemNumber) {
        toDoList.get(itemNumber).setCompletion(true);
        ui.displayMarkedItem(itemNumber, this.toDoList);
    }

    public void unmark(int itemNumber) {
        toDoList.get(itemNumber).setCompletion(false);
        ui.displayUnmarkedItem(itemNumber, this.toDoList);
    }

    public void addTask(Task task) {
        toDoList.add(task);
        ui.displayAddedTask(task, this.toDoList);
    }

    public void delete(int taskNumber, ArrayList<Task> toDoList) {
        ui.displayDeletionMessage(toDoList.get(taskNumber), this.toDoList);
        toDoList.remove(taskNumber);
    }
}