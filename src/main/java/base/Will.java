package base;

import task.TaskList;
import utility.Parser;
import utility.Storage;

/**
 * The {@code Will} class represents the core chatbot logic for managing tasks.
 * It initializes task-related functionalities and processes user input to generate responses.
 */
public class Will {

    private final TaskList TASKS;
    private final Storage STORAGE;
    private final Parser PARSER;

    /**
     * Constructs a new {@code Will} instance, initializing the {@code TaskList},
     * {@code Storage}, and {@code Parser} objects.
     * Loads tasks from storage into the task list.
     */
    public Will(){
        this.TASKS = new TaskList();
        this.STORAGE = new Storage();
        this.PARSER = new Parser();

        STORAGE.load(TASKS);
    }

    /**
     * Processes user input and returns a response from the chatbot.
     * The input is parsed and the appropriate actions are taken, such as adding,
     * removing, or updating tasks.
     *
     * @param input The user's input to be processed.
     * @return The chatbot's response based on the input command.
     */
    public String getResponse(String input) {
        return this.PARSER.parseCommand(TASKS, input, STORAGE);
    }
}
