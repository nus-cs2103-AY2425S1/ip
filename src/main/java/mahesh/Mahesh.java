package mahesh;

import java.io.IOException;

import mahesh.command.Command;
import mahesh.util.MaheshException;
import mahesh.util.Parser;
import mahesh.util.Storage;
import mahesh.util.TaskList;

/**
 * Represents the main application class for managing tasks.
 * It initializes the application, processes user input, and executes commands.
 */
public class Mahesh {

    /**
     * The relative path to the file used for storing tasks.
     */
    private String path = "../../../../data/mahesh.txt";
    private Storage store;
    private TaskList list;

    /**
     * Constructs a Mahesh object with the specified file path for storing tasks.
     *
     * @param paths the optional relative path to the file used for storing tasks
     */
    public Mahesh(String path) {
        this.path = path;
        this.store = new Storage(this.path);
        try {
            this.list = store.retrieveData();
        } catch (IOException e) {
            this.list = new TaskList();
        }
    }

    /**
     * Constructs a Mahesh object with the default file path for storing tasks.
     */
    public Mahesh() {
        this.store = new Storage(this.path);
        try {
            this.list = store.retrieveData();
        } catch (IOException e) {
            this.list = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input the user's chat message
     * @return the response generated by executing the parsed command
     */
    public String getResponse(String input) {
        Parser parserObj = new Parser(list, store);
        try {
            Command command = parserObj.parse(input);
            if (command != null) {
                String response = command.execute();
                return response;
            } else {
                return "Unknown command";
            }
        } catch (MaheshException err) {
            return err.getMessage();
        }
    }
}
