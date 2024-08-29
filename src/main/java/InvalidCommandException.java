// Solution below inspired by https://www.javatpoint.com/custom-exception
public class InvalidCommandException extends Exception {
    public InvalidCommandException (String message) {
        super(message);
    }
}
