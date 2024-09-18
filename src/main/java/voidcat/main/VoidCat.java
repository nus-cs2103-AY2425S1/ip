package voidcat.main;

import voidcat.exception.VoidCatException;
import voidcat.parser.Parser;
import voidcat.storage.Storage;
import voidcat.task.TaskList;
import voidcat.ui.Ui;

import java.io.IOException;
import java.lang.SecurityException;
/**
 * Represents the main class of the Void Cat program.
 * It manages the overall flow of the program,
 * including initializing components, interacting with the user, and handling tasks.
 */
public class VoidCat {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String[] greetings = {
                "Hello! I'm your friendly void cat, \n",
                "Purr... Hello, wanderer. I am \n",
                "Mew! Welcome human! I'm \n",
                "Greetings from the abyss, friend, for I am\n",
                "Meow! Happy to help, they call me \n"
    };

    private String[] assistGreetings = {
            "How can this void assist you today?",
            "At your service, human.",
            "What help does human need today?",
            "Need any help?",
            "What can I do for you?"
    };

    // Example of exits
    private String[] exits = {
                "Purr... Until next time, friend.",
            "Meow! I shall vanish into the shadows now."
                ,
                "Farewell! May your path be clear.",
                "Mew! See you in the void again soon.",
                "The void calls, but I'll return. Goodbye!"
    };

    /**
     * Constructs a Void object with a specified file path for storage of tasks.
     * If file exists, tasks are loaded by Storage.
     * If not, the file is created.
     *
     * @param filePath The path to the file where user's tasks are stored.
     */
    public VoidCat(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
    }

    /**
     * Handles user input and gets the response.
     * Asserts tasks are initialized before processing.
     *
     * @param input The user's input command.
     * @return Response to user input.
     */
    public String getResponse(String input) {
        try {
            this.tasks = new TaskList(storage.load());
            if (input.equalsIgnoreCase("bye")) {
                return Ui.goodbye(exits);
            }
            assert tasks != null : "TaskList is not initialized!";
            return new Parser().parseAndExecute(input, tasks, ui, storage);

        } catch (VoidCatException e) {
            return e.getMessage();
        } catch (IOException i) {
            return "Error saving tasks to file: " + i.getMessage();
        } catch (SecurityException s) {
            return "Security error in creating file or directory: " + s.getMessage();
        }
    }
}
