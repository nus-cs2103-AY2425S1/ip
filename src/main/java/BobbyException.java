public class BobbyException extends Exception {
    public BobbyException(String message) {
        super(message);
    }
}

class InvalidTaskNumberException extends BobbyException {
    public InvalidTaskNumberException() {
        super("The task number provided is invalid.");
    }
}

class InvalidCommandFormatException extends BobbyException {
    public InvalidCommandFormatException(String command, String input) {
        super(String.format("You must provide a %s to %s", input, command));
    }
}
class EmptyTodoException extends BobbyException {
    public EmptyTodoException() {
        super("The description of todo cannot be empty!");
    }
}

class EmptyDeadlineException extends BobbyException {
    public EmptyDeadlineException() {
        super("The description or deadline of a deadline task cannot be empty.");
    }
}

class EmptyEventException extends BobbyException {
    public EmptyEventException() {
        super("The description, start time, or end time of an event cannot be empty.");
    }
}
class InvalidInputException extends BobbyException {
    public InvalidInputException() {
        super("I am sorry, but I do not know what that means!");
    }
}

class InvalidDateException extends BobbyException {
    public InvalidDateException() {
        super("Invalid date format. Please use yyyy-MM-dd.");
    }
}