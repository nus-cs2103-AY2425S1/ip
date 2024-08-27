package tasklist;

import ouiouibaguette.OuiOuiBaguetteException;

public class TaskListOutOfBoundsException extends OuiOuiBaguetteException {
    public TaskListOutOfBoundsException(int index, int size) {
        super("Index " + index + " out of bounds for size " + size);
    }
}
