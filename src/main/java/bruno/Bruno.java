package bruno;

import bruno.command.Command;
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
    private TaskList tasks;

    /**
     * Constructs a Bruno object. This constructor initializes Bruno's
     * storage system and task list. It ensures that the necessary directory
     * and file for storing tasks exist.
     *
     * @param directoryPath The directory where the task data file is stored.
     * @param filePath The path to the file that stores task data.
     */
    public Bruno(String directoryPath, String filePath) {
        this.storage = new Storage(directoryPath, filePath);
        this.storage.ensureDirectoryExists();
        this.storage.ensureFileExists();
        this.tasks = new TaskList(this.storage);
    }

    public static void main(String[] args) {
        Bruno bruno = new Bruno("src/main/data/", "src/main/data/bruno.txt");
        
        bruno.run();
    }

    /**
     * Runs the Bruno chatbot. This method contains the main program loop
     * for interacting with the user. It reads commands from the user,
     * processes them through the Parser, and executes the appropriate commands.
     * The loop continues until the exit command is given.
     */
    public void run() {
        Ui.printGreetingMessage();
        boolean running = true;
        while (running) {
            String userResponse = Ui.readCommand();
            Command command = Parser.parse(userResponse, tasks);
            if (command != null) {
                command.execute();
                running = !command.isExit();
            }
        }
    }
}