package models;
import java.util.ArrayList;
import lib.ActiveRecord;
import lib.DbDriverInterface;
import lib.FileDbDriver;

public class TaskList extends ActiveRecord {

    private ArrayList<Task> tasks;

    public TaskList(DbDriverInterface dbDriver) {
        super(dbDriver);
        init();
    }

    private void init() {
        // deserialise text and save into tasks;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveToDb();
    }

    public int getSize() {return tasks.size();}

    public void markTask(int index) {
        tasks.get(index).mark();
        saveToDb();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmark();
        saveToDb();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        Task returnTask = this.tasks.remove(index-1);
        saveToDb();
        return returnTask;
    }

    public void saveToDb() {
        String serializedString = this.serialiseTaskList();
        this.dbDriver.save(serializedString);
    }

    public String serialiseTaskList() {
        if (tasks.isEmpty()) {
            return "";
        }
        StringBuilder list = new StringBuilder();
        for (Task task : tasks) {
            list.append(task.serialize()).append("\n");
        }
        System.out.println("This is serialised raw string");
        System.out.println(list);
        return list.toString();
    }

    public String listTasks() {
        if (tasks.isEmpty()) {
            return "List is empty!";
        }
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return list.toString();
    }
}
