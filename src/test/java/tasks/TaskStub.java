package tasks;

import exceptions.GrokInvalidUserInputException;

public class TaskStub extends Task {
    public TaskStub(String description) throws GrokInvalidUserInputException{
        super(description);
    }
}
