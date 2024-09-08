package slothingwaffler;

import java.util.Scanner;

/**
 * Main class for running the Slothing Waffler application.
 * <p>
 * This class handles the application flow, including user input and command processing.
 * </p>
 */
public class SlothingWaffler {

    private final TaskList tasks;
    private final Storage storage;

    /**
     * Constructs a SlothingWaffler instance with the specified file name for storage.
     *
     * @param fileName the name of the file to store and load tasks
     */
    public SlothingWaffler(String fileName) {
        this.storage = new Storage(fileName);
        this.tasks = new TaskList(storage);
    }

    public String getResponse(String userInput) {
        try {
            return Parser.parse(userInput, tasks, storage);
        } catch (SlothingWafflerException e) {
            return e.getMessage();
        }
    }

}
