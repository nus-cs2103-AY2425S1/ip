public class NoDescriptionException extends BeeBooExceptions{
    public NoDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "You didn't put in a description user. Here is a list of commands and descriptions\n" +
                "event [eventName] /from [time] /to [time]\n"+
                "deadline [deadlineName]/ by [time]\n " +
                "todo [todoName]\n" +
                "Please try again user";
    }
}
