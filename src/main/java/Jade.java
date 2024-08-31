import UI.UI;
import storage.Storage;
import task.TaskManager;

public class Jade {
    private static final String FILE_PATH = "./data/jade.txt";

    public static void main(String[] args) {
        Storage storage = new Storage(FILE_PATH);
        TaskManager taskManager = new TaskManager(storage);
        UI ui = new UI(taskManager);
        ui.run();
    }
}