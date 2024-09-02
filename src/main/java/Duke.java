import exception.DukeException;
import task.TaskList;
import utilities.Storage;
import utilities.Ui;

public class Duke {
    private Storage storage = new Storage("./data/duke.txt");
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        ui = new Ui(tasks);
    }

    public static void main(String[] args) {

        Storage storage = new Storage("data/duke.txt");
        TaskList tasks = storage.load();
        Ui ui = new Ui(tasks);
        ui.startInteraction();

    }
}
