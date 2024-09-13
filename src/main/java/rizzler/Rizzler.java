package rizzler;

import javafx.util.Pair;

/**
 * Main class that controls Rizzler's functions.
 */
public class Rizzler {
    private Ui ui;
    private FileStorage fileStorage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructs a new <code>Rizzler</code> with the
     * given file path of the save file to load from.
     *
     * @param filePath File path to load save from.
     */
    public Rizzler(String filePath) {
        this.ui = new Ui();
        this.fileStorage = new FileStorage(filePath);
        this.tasks = new TaskList(this.fileStorage.load());
        this.parser = new Parser();
    }

    /**
     * Shows greeting at startup
     *
     * @return Greeting string
     */
    public String startUp() {
        return this.ui.showGreeting();
    }

    /**
     * Initiates the running of Rizzler.
     */
    public Pair<String, Boolean> getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = this.parser.parse(fullCommand);
            String response = c.execute(this.tasks, this.ui, this.fileStorage);
            boolean isExit = c.isExit();
            return new Pair<>(response, isExit);
        } catch (RizzlerException e) {
            return new Pair<>(ui.showError(e.getMessage()), false);
        }
    }
}
