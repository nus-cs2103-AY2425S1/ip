public class Storage {
    private Task[] storage;
    private int size;

    public Storage() {
        this.storage = new Task[100];
        this.size = 0;
    }

    //Adds the input task to the storage array
    public void addTask(String description) {
        Task task = new Task(description);
        this.storage[this.size] = task;
        this.size++;
    }

    //Returns a string containing all the tasks in the storage array
    public String getTasks() {
        String taskList = "";
        for (int i = 0; i < this.size; i++) {
            taskList += (i + 1) + "." + this.storage[i].toString() + "\n";
        }
        return taskList;
    }

    //Return a specific task from the storage array based on the index
    public Task getTask(int index) {
        return this.storage[index];
    }
}
