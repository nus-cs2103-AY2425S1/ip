import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> storage;

    public Storage() {
        this.storage = new ArrayList<>(100);
    }

    //Adds the input task to the storage array
    public void addTask(Task task) {
        this.storage.add(task);
    }

    //Returns a string containing all the tasks in the storage array
    public String getTasks() {
        String taskList = "";
        int i = 1;
        for (Task task : this.storage) {
            taskList += i + "." + task.toString() + "\n";
            i++;
        }
        return taskList;
    }

    //Return a specific task from the storage array based on the index
    public Task getTask(int index) {
        return this.storage.get(index);
    }

    //Returns the size of the storage array
    public int getSize() {
        return this.storage.size();
    }

    //Returns a string with the number of tasks remaining
    public String getNumOfTasks() {
        return "Now you have " + this.getSize() + " tasks in the list.";
    }

    //Deletes a task from the storage array based on the index
    public void deleteTask(int index) {
        this.storage.remove(index);
    }
}
