package alice;

import alice.command.Command;
import alice.storage.Storage;
import alice.storage.TaskList;
import alice.task.InvalidTaskException;

/** Handles input/event loop. */
public class Alice {
    private static final String NAME = "Alice";
    private static final String DATA_DIRECTORY_PATH = "./data";
    private static final String TASKS_FILE_NAME = "tasks.jsonl";

    private final Storage storage;
    private final TaskList taskList;

    /**
     * Creates an instance of the chatbot.
     */
    public Alice() {
        this.storage = new Storage(DATA_DIRECTORY_PATH, TASKS_FILE_NAME);
        this.taskList = new TaskList(this.storage);
    }

    public String getGreeting() {
        return String.format("Hello! I'm %s. What can I do for you?", NAME);
    }

    public String getReponse(String input) {
        try {
            Command command = Command.fromInput(input, taskList);
            return command.execute(input);
        } catch (InvalidTaskException exception) {
            return exception.getMessage();
        } catch (IllegalArgumentException exception) {
            // default behavior is to echo
            return input;
        }
    }
}
