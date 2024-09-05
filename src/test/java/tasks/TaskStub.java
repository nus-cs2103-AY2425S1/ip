package tasks;

import exceptions.GrokInvalidUserInputException;

/**
 * Provides a task stub to decouple unit tests for other packages.
 */
public class TaskStub extends Task {
    public TaskStub(String description) throws GrokInvalidUserInputException {
        super(description);
    }
}
