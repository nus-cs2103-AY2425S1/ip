public class InvalidDateException extends BeeBooExceptions{

    public InvalidDateException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "Your date commands are wrong. Here are the list of how to create tasks\n" +
                "event [eventName] /from [time] /to [time]\n" +
                "deadline [deadlineName] /by [time]";
    }
}
