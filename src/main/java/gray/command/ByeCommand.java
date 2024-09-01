package gray.command;

import gray.GrayException;
import gray.Tasks;

/**
 * A command that ends the chatbot loop interaction.
 */
public class ByeCommand implements Command {
    /**
     * Executes the bye command.
     *
     * @param tasks
     * @throws GrayException
     */
    @Override
    public String execute(Tasks tasks) {
        return "Bye. Hope to see you again soon!";
    }
}
