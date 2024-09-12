package gray.command;

import gray.GrayException;
import gray.Tasks;

/**
 * Represents the interface for command.
 * A command interacts with given tasks and returns a string.
 *
 */
public interface Command {
    String execute(Tasks tasks) throws GrayException;
}
