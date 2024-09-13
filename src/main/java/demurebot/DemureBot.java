package demurebot;

import java.io.IOException;
import java.util.ArrayList;

import demurebot.command.Command;
import demurebot.storage.Storage;
import demurebot.task.TaskList;
import demurebot.ui.Parser;
import demurebot.ui.Ui;


/**
 * Main class for the DemureBot application.
 * This class handles the initialization and execution of the chatbot.
 */
public class DemureBot {
    private TaskList list;
    private final Storage storage;
    private final Ui ui;
    private final String filePath = "./data/tasks.txt";

    /**
     * Constructs a new DemureBot instance.
     * Initializes the storage and UI components.
     */
    public DemureBot() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.list = null;

        // check if data folder exists if not create it
        String folderPath = "./data";
        this.storage.checkFolder(folderPath);

        // check if saved data exists if not create it
        boolean hasSavedData = this.storage.checkFile(filePath);

        // load saved data if exists
        if (hasSavedData) {
            try {
                // fetch list of items to do
                this.list = new TaskList(this.storage.load(filePath));
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } catch (DemureBotException e) {
                System.out.println("Error loading task: " + e.getMessage());
            }
        }

        if (this.list == null) {
            this.list = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the main loop of the DemureBot application.
     * Handles user input, command execution, and data persistence.
     */
    public String run(String input) {
        assert !input.isEmpty() : "Input is empty!";

        try {
            Command command = Parser.parse(input);
            String response = command.execute(this.list, this.ui);
            this.storage.save(filePath, this.list);
            return response;
        } catch (DemureBotException e) {
            return e.getMessage();
        }
    }
}
