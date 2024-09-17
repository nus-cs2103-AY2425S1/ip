package chacha.command;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.task.TaskList;

/**
 * Parses user inputs and matches them to the respective action.
 *
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ChaCha chacha;

    /**
     * Creates a Parser object with the specified components
     *
     * @param chacha ChaCha object
     * @param storage Storage of ChaCha
     * @param ui UI of ChaCha
     * @param tasks List of tasks
     */
    public Parser(ChaCha chacha, Storage storage, TaskList tasks, Ui ui) {
        this.chacha = chacha;
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Returns the string representation of response to the user input.
     *
     * @param userInput User input.
     *
     * @return String representation.
     */
    public String parseCommand(String userInput) {
        String alteredInput = userInput.toLowerCase();
        if (alteredInput.isEmpty()) {
            return new ErrorCommand(chacha).execute(alteredInput, storage, ui, tasks);
        }

        assert !alteredInput.isEmpty() : "user input should not be empty";

        String command = alteredInput.split(" ")[0];
        assert command != null : "command should not be null";

        return switch (command) {
        case "bye" -> new ByeCommand(chacha).execute(alteredInput, storage, ui, tasks);
        case "list" -> new ListCommand(chacha).execute(alteredInput, storage, ui, tasks);
        case "todo" -> new ToDoCommand(chacha).execute(alteredInput, storage, ui, tasks);
        case "deadline" -> new DeadlineCommand(chacha).execute(alteredInput, storage, ui, tasks);
        case "event" -> new EventCommand(chacha).execute(alteredInput, storage, ui, tasks);
        case "mark" -> new MarkCommand(chacha).execute(alteredInput, storage, ui, tasks);
        case "unmark" -> new UnmarkCommand(chacha).execute(alteredInput, storage, ui, tasks);
        case "delete" -> new DeleteCommand(chacha).execute(alteredInput, storage, ui, tasks);
        case "find" -> new FindCommand(chacha).execute(alteredInput, storage, ui, tasks);
        case "help" -> new HelpCommand(chacha).execute(alteredInput, storage, ui, tasks);
        default -> new ErrorCommand(chacha).execute(alteredInput, storage, ui, tasks);
        };
    }
}
