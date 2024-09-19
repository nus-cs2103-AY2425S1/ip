package screwllum;

import java.io.IOException;
import java.util.List;

import screwllum.exception.ScrewllumException;
import screwllum.tasks.TaskManager;
import screwllum.utils.Parser;
import screwllum.utils.Storage;
import screwllum.utils.Ui;

/**
 * Represents the Chatbot object, which contains instances of other classes and utilizes their provided functionalities
 * to drive the chatbot experience.
 */
public class Screwllum {
    private Storage storage;
    private TaskManager taskManager;
    private Ui ui;
    
    public Screwllum() {
        this("Save.txt");
    }

    public Screwllum(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskManager = new TaskManager(storage.load());
        } catch (Exception e) {
            taskManager = new TaskManager();
        }
    }

    /**
     * Starts the application by displaying a welcome message and entering the main loop.
     * Using other classes, the loop parses user inputs, executes them, and updates the save file.
     * Exceptions caught results in the respective error message being displayed.
     */
    public String getResponse(String userInput) {
        try {
            List<String> tokens = Parser.parseUserInput(userInput);
            String result = taskManager.execute(tokens, ui);
            storage.writeToFile(taskManager.getTaskList());
            return result;
        } catch (ScrewllumException e) {
            return ui.showError(e.getMessage());
        }
    }
    
    public String showWelcome() {
        return ui.showWelcome();
    }
}
