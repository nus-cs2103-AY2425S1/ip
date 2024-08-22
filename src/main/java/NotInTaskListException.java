public class NotInTaskListException extends Exception {

    public NotInTaskListException() {
        super("Apologies, but that task does not exist");
    }
}
