package exceptions;

import exceptions.SigmaBotExceptions;

public class TaskAlreadyDoneException extends SigmaBotExceptions {
    public TaskAlreadyDoneException(String message) {
        super(message);
    }
}
