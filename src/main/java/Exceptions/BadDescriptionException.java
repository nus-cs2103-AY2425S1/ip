package exceptions;

import tasks.TaskTypes;

/**
 * Raised exception on missing task descriptions.
 */
public class BadDescriptionException extends Exception {
    public BadDescriptionException(TaskTypes type) {
        super(String.format("Provide details for the %s!", type.getDescription()));
    }
}
