import exception.DukeException;
import task.TaskList;
import utilities.Storage;
import utilities.Ui;
import java.io.File;

public class DPlusPlusE {
    private Storage storage;
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
        assert new File("./data/duke.txt").exists() : "File should exist";
        assert storage != null : "Storage should not be null";
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "UI should not be null";
    }

    public String getResponse(String input) {
        return ui.interactWithUser(input).toString();
    }
}
