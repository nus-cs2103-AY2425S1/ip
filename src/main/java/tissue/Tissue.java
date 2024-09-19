package tissue;

import java.io.IOException;
import java.util.ArrayList;

import tissue.parse.Parser;

/**
 * The class for the Tissue bot which sets up the required variables.
 */
public class Tissue {
    private GuiUi guiUi;

    /**
     * Initialise required variables.
     * @param filePath Path to store file.
     * @param fileName Name to store file as.
     */
    public Tissue(String filePath, String fileName) {
        assert !filePath.isEmpty() : "File path cannot be empty";
        assert !fileName.isEmpty() : "File name cannot be empty";
        Storage storage = new Storage(filePath, fileName);
        try {
            this.guiUi = new GuiUi(new Parser(), new TaskList(storage.load()), storage);
        } catch (IOException e) {
            this.guiUi = new GuiUi(new Parser(), new TaskList(new ArrayList<>()), storage);
        }

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
