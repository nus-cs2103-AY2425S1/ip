package shoai;
import shoai.TaskList; // Imports the TaskList class to manage tasks
import shoai.Ui; // Imports the Ui class for user interface interactions
import shoai.Storage; // Imports the Storage class to handle task storage
import shoai.Parser; // Imports the Parser class to parse commands
import shoai.ShoAIException; // Imports the ShoAIException class for handling exceptions

public class ShoAI {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ShoAI(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (ShoAIException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                isExit = new Parser().parse(fullCommand, tasks, ui, storage);
            } catch (ShoAIException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new ShoAI("src/main/data/ShoAI.txt").run();
    }
}
