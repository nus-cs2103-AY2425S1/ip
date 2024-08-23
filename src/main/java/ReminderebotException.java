public class ReminderebotException extends Exception{
    ReminderebotException(String message) {
        super("Oops! " + message);
    }
}
