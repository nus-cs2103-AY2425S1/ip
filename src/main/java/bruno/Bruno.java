package bruno;

import bruno.command.Command;
import bruno.exceptions.BrunoException;
import bruno.task.TaskList;

/**
 * Represents a chatbot called Bruno. A Bruno object is a chatbot that manges different
 * types of tasks.
 */
public class Bruno {

    /**
     * Enum to represent different types of tasks Bruno can handle.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    private Storage storage;
    private TaskList taskList;
    private String commandType;

    /**
     * Constructs a Bruno object. This constructor initializes Bruno's
     * storage system and task list. It ensures that the necessary directory
     * and file for storing tasks exist.
     *
     * @param directoryPath The directory where the task data file is stored.
     * @param filePath The path to the file that stores task data.
     */
    public Bruno(String directoryPath, String filePath) {
        assert directoryPath != null : "Directory path is null";
        assert filePath != null : "File path is null";
        this.storage = new Storage(directoryPath, filePath);
        this.storage.ensureDirectoryExists();
        this.storage.ensureFileExists();
        this.taskList = new TaskList(this.storage);
    }

    /**
     * Default constructor to be used by JavaFX.
     */
    public Bruno() {
        this("src/main/data/", "src/main/data/bruno.txt");
    }

    public String getResponse(String input) {
        try {
            assert input != null : "Input string is null";
            Command c = Parser.parse(input, taskList);
            c.execute();
            commandType = c.getClass().getSimpleName();
            return c.toString();
        } catch (BrunoException e) {
            commandType = "Error";
            return e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }
}
