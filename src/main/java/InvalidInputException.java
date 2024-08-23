public class InvalidInputException extends DukeException {
    InvalidInputException() {
        super("test");
    }

    @Override
    public String toString() {
        return "eh paiseh ah bro, you need to tell me your start time and end time \n " +
                "in this format 'event *your task* /from *time* /to *time*' OR" +
                "'deadline *your task* /by *day*'";
    }
}
