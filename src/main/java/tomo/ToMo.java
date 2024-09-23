package tomo;

import command.Command;
import exception.StorageException;
import exception.ToMoException;
import storage.Storage;
import tasklist.TaskList;

/**
 * Handles everything about the conversation with user
 */
public class ToMo {
    private Parser parser;
    private TaskList tasks;
    private Storage storage;
    private String initializeMessage = "";
    /**
     * Constructor for the ToMo bot
     * @param fileName the file to load and store tasks
     */
    public ToMo(String directoryPath, String fileName) {
        parser = new Parser();
        storage = new Storage(directoryPath, fileName);

        try {
            tasks = storage.load();
            if (tasks.isEmpty()) {
                initializeMessage = "Your file is empty, we will start with an empty task list\n";
            } else {
                initializeMessage = "Yay, successfully loaded " + (tasks.size()) + " tasks\n";
            }
        } catch (StorageException e) {
            tasks = new TaskList();
            initializeMessage = "Your file is not found, we will start with an empty task list\n";
        }
    }

    public String getInitializeMessage() {
        return initializeMessage;
    }

    public String getGreeting() {
        return "What's up, it's ToMo here to assist you!";
    }

    public String getResponse(String cmdline) {
        try {
            Command command = parser.parse(cmdline);
            return command.getResponse(tasks);
        } catch (ToMoException e) {
            return e.toString();
        }
    }

    /**
     * Closes the conversation between user and the bot
     */
    public void close() {
        try {
            storage.store(tasks);
        } catch (StorageException e) {
            System.out.println(e);
        }
    }
}
