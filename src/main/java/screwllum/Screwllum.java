package screwllum;

import java.io.IOException;
import java.util.List;

import screwllum.exception.IllegalFileFormatException;
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
    private String welcomeMessage;
    public Screwllum() {
        this("Save.txt");
    }

    /**
     * Constructor for the class, which initialises other classes as well as the welcome message depending on
     * the presence or absence of exceptions.
     *
     * @param filePath
     */
    public Screwllum(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskManager = new TaskManager(storage.load());
            welcomeMessage = "Pleased to meet you!";
        } catch (IOException e) {
            welcomeMessage = "Hmm, something went wrong with file exception, I'll try to fix it...";
        } catch (IllegalFileFormatException e) {
            welcomeMessage = "How peculiar, your save file is corrupted. I will create a new one for you!";
        }
        if (taskManager == null) {
            taskManager = new TaskManager();
        }
    }

    /**
     * Starts the application by displaying a showWelcome message and entering the main loop.
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

    /**
     * Provides various welcome messages depending on the file initialisation phase.
     *
     * @return A string representing the welcome message.
     */
    public String showWelcome() {
        return ui.showWelcome(welcomeMessage);
    }
}
