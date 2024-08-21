public class Storage {
    private Task[] storage;
    private int size;

    public Storage() {
        this.storage = new Task[100];
        this.size = 0;
    }

    //Adds the input task to the storage array
    public void addTask(Task task) {
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

    //Returns the size of the storage array
    public int getSize() {
        return this.size;
    }

    //Returns a string with the number of tasks remaining
    public String getNumOfTasks() {
        return "Now you have " + this.size + " tasks in the list.";
    }
}
