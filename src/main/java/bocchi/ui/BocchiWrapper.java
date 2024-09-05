package bocchi.ui;

import bocchi.Bocchi;
import bocchi.command.Parser;
import bocchi.exception.BocchiException;

/**
 * A lightweight wrapper that acts as a bridge connecting the backend and the GUI.
 */
public class BocchiWrapper {
    private final Bocchi BOCCHI = new Bocchi();
    private MainWindow mainWindow;

    /**
     * Reads a command; passes it to the chatbot and returns the response.
     * @param input The user's prompt.
     * @return The chatbot's response.
     */
    public String getResponse(String input) {
        try {
            return BOCCHI.processCommand(Parser.parse(input));
        } catch (BocchiException e) {
            return e.getMessage();
        }
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.respond(BOCCHI.greet());
    }
}
