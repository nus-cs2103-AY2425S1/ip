public class WrongTaskNumberException extends Exception {
    public WrongTaskNumberException() {
        super("There is no such task number. Please check again!");
    }
}
