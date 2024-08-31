import exceptions.InvalidDateException;
import storage.Storage;
import task.TaskList;


public class Blitz {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Blitz(String storagePath) {
        try {
            this.storage = Storage.createStorage(storagePath);
            this.taskList = new TaskList(storage);
            ui = new Ui(taskList);
        } catch (InvalidDateException e) {
            System.out.println("PLEASE USE THE PROPER DATE FORMAT");
        }
    }

    public static void main(String[] args) {
        String path = "src/main/data/blitz.txt";
        Blitz blitzJr = new Blitz(path);
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
