package bibi;

import java.io.FileNotFoundException;

import bibi.task.TaskList;
/**
 * Represents the chat bot.
 */
public class Bibi {
    /** The Ui instance that handles printing and reading console commands */
    private Processor processor;
    /** The list that contains all Tasks inside the save file */
    private TaskList tasks;
    /** The class that handles writing to the save file */
    private Storage storage;

    /**
     * Constructs a new Bibi instance.
     *
     * @param filePath An absolute path to the save file
     */
    public Bibi(String filePath) {
        this.processor = new Processor();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);

        // Init
        storage.initializeDataDirectory();
        try {
            storage.restoreTasks(tasks);
        } catch (FileNotFoundException e) {
            // Shouldn't reach this point, since a folder will be created automatically
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(String input) {
        Command c = Parser.parseCommand(input.trim());
        String response = c.execute(tasks, processor, storage);

        if (c.isError()) {
            return "ERROR" + response;
        } else {
            return response;
        }
    }
}
