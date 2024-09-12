package echoa;

/**
 * ListOutOfBoundsException is a class that encapsulates exceptions relating to
 * exceeding the bounds of a TaskList.
 * It extends from class EchoaException.
 */

public class ListOutOfBoundsException extends EchoaException {
    private int index;

    public ListOutOfBoundsException(int index) {
        super();
        this.index = index;
    }

    @Override
    public String getMessage() {
        return this.index + "is out of bounds. Please check your list again.";
    }
}
