package ahmad.exceptions.todo;

import ahmad.exceptions.AhmadException;

public class TodoEmptyNameException extends AhmadException {
    public TodoEmptyNameException() {
        super("Name should not be empty");
    }
}
