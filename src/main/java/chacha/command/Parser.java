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
     * @param chacha
     * @param storage
     * @param tasks
     * @param ui
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
     * @return String representation.
     */
    public String parseCommand(String userInput) {
        String command = userInput.split(" ")[0];


        if (command.equals("bye")) {
            return new ByeCommand(chacha).execute(userInput, storage, ui, tasks);

        } else if (command.equals("list")) {
            return new ListCommand(chacha).execute(userInput, storage, ui, tasks);

        } else if (command.equals("todo")) {
            return new ToDoCommand(chacha).execute(userInput, storage, ui, tasks);

        } else if (command.equals("deadline")) {
            return new DeadlineCommand(chacha).execute(userInput, storage, ui, tasks);

        } else if (command.equals("event")) {
            return new EventCommand(chacha).execute(userInput, storage, ui, tasks);

        } else if (command.equals("mark")) {
            return new MarkCommand(chacha).execute(userInput, storage, ui, tasks);

        } else if (command.equals("unmark")) {
            return new UnmarkCommand(chacha).execute(userInput, storage, ui, tasks);

        } else if (command.equals("delete")) {
            return new DeleteCommand(chacha).execute(userInput, storage, ui, tasks);

        } else if (command.equals("find")) {
            return new FindCommand(chacha).execute(userInput, storage, ui, tasks);

        } else {
            return new ErrorCommand(chacha).execute(userInput, storage, ui, tasks);
        }
    }
}
