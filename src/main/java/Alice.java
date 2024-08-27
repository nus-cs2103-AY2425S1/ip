import storage.Storage;
import storage.TaskList;
import ui.Ui;

public class Alice {
    private final TaskList TASKS;
    private final Storage STORAGE;
    private final Ui UI;

    public Alice(String filePath) {
        STORAGE = new Storage(filePath);
        // load tasks
        STORAGE.loadTasks();
        TASKS = STORAGE.getTasks();

        // initialise UI with the loaded tasks
        UI = new Ui(TASKS);
    }

    public static void main(String[] args) {
        // create instance of Alice with loaded tasks
        new Alice("data.txt").run();
    }

    // starts the program
    public void run() {
        UI.showWelcome();
        UI.getInput();
        UI.exitMessage();
        STORAGE.saveTasks(TASKS);
    }
}
