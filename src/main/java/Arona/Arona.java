package Arona;

import Arona.AronaExceptions.AronaException;
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
     * Overloaded constructor for JavaFX
     */
    public Arona() {
        this(".\\data.txt");
    }

    /**
     * Entry to main logic from GUI
     * @param  input  the unprocessed String input from the user
     */
    public String getResponse(String input) {
        assert storage != null : "storage should be instantiated";
        assert taskList != null : "taskList should be instantiated";
        assert parser != null : "parser should be instantiated";
        assert ui != null : "ui should be instantiated";

        String reply = "";

        // Process inputs
        try {
            reply = parser.parse(input);
            return reply;

        } catch (Exception e) {
            // User Error
            if (e instanceof AronaException) {
                return ui.showException(e);
            }
            // File not found or cant be read/write to
            else if (e instanceof java.io.IOException || e instanceof SecurityException) {
                return ui.showFileException();
            }
            // Path in arona::main not correct
            else if (e instanceof InvalidPathException) {
                return ui.showPathException();
            }
            // Other exception
            else {
                return ui.showException(e);
            }
        }
    }
}
