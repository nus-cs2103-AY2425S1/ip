// Buddy.java
import java.io.IOException;

public class Buddy {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public Buddy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            loadedTasks = new TaskList();
        }
        this.tasks = loadedTasks;
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Buddy("./data/tasks.txt").run();
    }
}