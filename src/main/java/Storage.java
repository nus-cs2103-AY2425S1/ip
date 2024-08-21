public class Storage {
    private String[] storage;
    private int size;

    public Storage() {
        this.storage = new String[100];
        this.size = 0;
    }

    //Adds the input task to the storage array
    public void addTask(String task) {
        this.storage[this.size] = task;
        this.size++;
    }

    //Returns a string containing all the tasks in the storage array
    public String getTasks() {
        String tasks = "";
        for (int i = 0; i < this.size; i++) {
            tasks += (i + 1) + ". " + this.storage[i] + "\n";
        }
        return tasks;
    }
}
