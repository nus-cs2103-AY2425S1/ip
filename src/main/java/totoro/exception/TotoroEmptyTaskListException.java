package totoro.exception;

public class TotoroEmptyTaskListException extends TotoroException {
    @Override
    public String toString() {
        return String.format("%s There are no tasks at the moment", super.toString());
    }
}
