package tissue;

import tissue.parse.Parser;

/**
 * The class for the Tissue bot which sets up the required variables.
 */
public class Tissue {
    private final Ui ui;
    private final GuiUi guiUi;

    /**
     * Constructor to initialize required variables.
     */
    public Tissue(String filePath, String fileName) {
        assert !filePath.isEmpty() : "File path cannot be empty";
        assert !fileName.isEmpty() : "File name cannot be empty";
        Storage storage = new Storage(filePath, fileName);
        this.guiUi = new GuiUi(new Parser(), new TaskList(storage.load()), storage);
        this.ui = new Ui(new Parser(), new TaskList(storage.load()), storage);
    }

    public static void main(String[] args) {
        new Tissue("./data/", "tissue.csv").run();
    }

    private void run() {
        ui.run();
    }

    public String getResponse(String input) {
        if (input.isEmpty()) {
            return "Please input a valid input.";
        }
        return guiUi.getResponse(input);
    }

    public String getGreeting() {
        return guiUi.getGreetingMessage();
    }
}
