package src.exceptions;

public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("test");
    }

    @Override
    public String getMessage() {
        return "eh paiseh ah bro, you need to tell me your start time and end time \n " +
                "in this format 'event *your task* /from *time* /to *time*' OR" +
                "'deadline *your task* /by *day*'";
    }
}
