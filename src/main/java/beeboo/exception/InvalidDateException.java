package beeboo.exception;

public class InvalidDateException extends BeeBooExceptions{

    public InvalidDateException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "Your date commands are wrong. Here are the list of how to create tasks\n" +
                "event [eventName] /from [yyyy-mm-dd] [hh-mm]/to [time]\n" +
                "deadline [deadlineName] /by [yyyy-mm-dd] [hh-mm]";
    }
}
