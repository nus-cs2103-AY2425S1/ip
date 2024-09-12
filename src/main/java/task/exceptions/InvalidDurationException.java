package task.exceptions;

import chatbot.exceptions.BeeException;

/**
 * A class that formats error message for
 * invalid inputs related to date and times.
 *
 * @author celeschai
 */
public class InvalidDurationException extends BeeException {
    /**
     * Formats error message.
     */
    public InvalidDurationException() {
        super("The duration does not make sense!");
    }
}
