package arona;

import arona.AronaExceptions.AronaException;
import java.nio.file.InvalidPathException;

public class Arona {

    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    /**
     * Constructor for the main class of Arona, instantiates ui, storage, and tasklist as objects to be passed within
     * the programme
     * @param  filePath  a relative filepath giving the location that data.txt should be stored in
     */
    public Arona(String filePath) {

        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showException(e);
            taskList = new TaskList();
        }

        parser = new Parser(storage, taskList, ui);
    }

    /**
     * Constructor for JavaFX, overloaded
     */
    public Arona() {
        this(".\\data.txt");
    }

    /**
     * Entry to main logic from GUI
     * @param  input  the unprocessed String input from the user
     * @return A String message which is displayed by GUI
     */
    public String getResponse(String input) {
        assert storage != null : "storage should be instantiated";
        assert taskList != null : "taskList should be instantiated";
        assert parser != null : "parser should be instantiated";
        assert ui != null : "ui should be instantiated";

        // Process inputs
        try {
            return parser.parse(input);
        } catch (Exception e) {
            if (e instanceof AronaException) {
                // User Error
                return ui.showException(e);
            } else if (e instanceof java.io.IOException || e instanceof SecurityException) {
                // File not found or cant be read/write to
                return ui.showFileException();
            } else if (e instanceof InvalidPathException) {
                // Path in arona::main not correct
                return ui.showPathException();
            } else {
                // Other exception
                return ui.showException(e);
            }
        }
    }
}
