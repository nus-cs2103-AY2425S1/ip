package rizz.source;
import java.io.IOException;
import rizz.command.Command;

public class Rizz {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Rizz(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasks());
    }


    public void run() throws IOException {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String userInput = this.ui.readCommand();
            Command command = Parser.parseCommand(userInput);
            if (command == null) {
                ui.showError("Invalid command. Please try again.");
                continue;
            }
            command.execute(tasks, ui, storage);
            storage.saveTasks(tasks);
        }
    }


    public static void main(String[] args) throws IOException {
        new Rizz("./data/rizz.txt").run();
    }

}