import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void completeTaskAt(int index) throws IndexOutOfBoundsException {
        this.taskList.get(index - 1).completeTask();
    }

    public void uncompleteTaskAt(int index) throws IndexOutOfBoundsException {
        this.taskList.get(index - 1).uncompleteTask();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task removeTaskAt(int index) throws IndexOutOfBoundsException{
        Task removedTask = taskList.get(index - 1);
        taskList.remove(index - 1);
        return removedTask;
    }

    public Task getTaskAt(int index) {
        return taskList.get(index - 1);
    }

    public String getTaskString(int index) {
        return taskList.get(index - 1).toString();
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < this.getSize(); i++) {
            int index = i + 1;
            if (index < this.getSize()) {
                output += " " + index + ". " + taskList.get(i).toString() + "\n";
            } else {
                output += " " + index + ". " + taskList.get(i).toString();
            }
        }
        return output;
    }

    public String[] toSave() {
        String[] output = new String[this.getSize()];
        for (int i = 0; i < this.getSize(); i++) {
            output[i] = taskList.get(i).storeData();
        }
        return output;
    }
}   
