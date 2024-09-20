import exception.DPlusPlusEException;
import task.TaskList;
import utilities.Storage;
import utilities.Ui;
import java.io.File;
import exception.FormatException;
import exception.NoInputException;

public class DPlusPlusE {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "./data/d_plus_plus_e.txt";

    public DPlusPlusE(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DPlusPlusEException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        ui = new Ui(tasks);
    }

    public DPlusPlusE() {
        this(FILE_PATH);
        assert new File(FILE_PATH).exists() : "File should exist";
        assert storage != null : "Storage should not be null";
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "UI should not be null";
    }

    public String getResponse(String input) {
        try {
            return ui.interactWithUser(input).toString();
        } catch (FormatException e) {
            return e.getMessage();
        } catch (NoInputException e) {
            return e.getMessage();
        }
    }
}
