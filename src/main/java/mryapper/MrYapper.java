package mryapper;

import mryapper.command.Command;
import mryapper.exception.IllegalTaskException;
import mryapper.exception.InvalidSyntaxException;
import mryapper.parser.Parser;
import mryapper.storagemanager.StorageManager;
import mryapper.task.TaskList;
import mryapper.ui.Ui;

import java.io.IOException;

/**
 * Contains the main program of the ChatBot. Running the main program initializes
 * the ChatBot and runs the ChatBot.
 */
public class MrYapper {

    private static final String TASK_DATA_PATH = "src/data/tasks.txt";
    private final StorageManager storageManager;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes the ChatBot with the given data file path.
     * The file path is relative and if no data file exists, a new
     * data .txt file will be created automatically.
     *
     * @param filePath The relative file path of data file.
     */
    public MrYapper(String filePath) {
        this.ui = new Ui();
        this.storageManager = new StorageManager(filePath);
        try {
            this.tasks = storageManager.retrieveData();
        } catch (IOException e) {
            System.out.println(" An error occurred when creating a new data file :(");
        }
    }

    /**
     * Runs the ChatBot until the "bye" command is executed.
     */
    public void run() {
        boolean isConversationOngoing = false;
        if (tasks != null && storageManager != null) {
            ui.showGreeting();
            isConversationOngoing = true;
        }

        while (isConversationOngoing) {
            try {
                String userInput = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(userInput);
                isConversationOngoing = !c.execute(tasks, ui, storageManager);
            } catch (IllegalTaskException | InvalidSyntaxException | IllegalArgumentException e) {
                ui.send(e.getMessage());
            }
            ui.showLine();
        }
        ui.close();
    }

    public static void main(String[] args) {
        new MrYapper(TASK_DATA_PATH).run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return input;
    }
}
