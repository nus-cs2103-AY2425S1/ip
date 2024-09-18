package sirpotato;

import java.io.IOException;

public abstract class Command {
    /**
     * Executes the specific command, such as AddCommand or DeleteCommand
     * 
     * @param tasks The TaskList containing the current list of tasks
     * @param ui The current Ui class that handles the UI of the program
     * @param storage The current storage class that handles reading/writing to files
     * @return The chatbot's response to the user after command executed
     * @throws DukeException If the Task experiences incorrect input 
     * @throws IOException if there is an issue with reading/writing to storage file
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}