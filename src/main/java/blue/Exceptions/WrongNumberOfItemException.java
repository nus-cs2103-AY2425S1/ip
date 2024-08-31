package blue.Exceptions;

public class WrongNumberOfItemException extends Exception {
    public WrongNumberOfItemException(int n) {
        String temp = "erm did you know we only have " + n + " items in the list";
    }
}
