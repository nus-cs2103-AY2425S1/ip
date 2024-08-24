/**
 * Thrown when invalid index is given in input. Contains size of TaskList.
 */
public class OutOfListException extends JacksonException {
    public OutOfListException(String msg) {
        super(msg);
    }
}
