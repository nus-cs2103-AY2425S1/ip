import exception.DukeException;
import task.TaskList;
import utilities.Storage;
import utilities.Ui;

public class DPlusPlusE {
    private Storage storage = new Storage("./data/duke.txt");
    private TaskList tasks;
    private Ui ui;

    public DPlusPlusE(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        ui = new Ui(tasks);
    }

    public DPlusPlusE() {
        this("./data/duke.txt");
    }

    public String getResponse(String input) {
        return ui.interactWithUser(input).toString();
    }
}
