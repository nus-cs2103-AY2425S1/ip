package exceptions;

import tasks.TaskType;

/**
 * Raises exception on missing task descriptions.
 */
public class BadDescriptionException extends Exception {
    public BadDescriptionException(TaskType type) {
        super(String.format("Provide details for the %s!", type.getDescription()));
    }
}
