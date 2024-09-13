package echoa;

/**
 * This class encapsulates errors relating to a todo update instructions.
 */

public class ToDoUpdateFormatException extends UpdateFormatException {
    public ToDoUpdateFormatException() {
        super();
    }

    @Override
    public String getMessage() {
        return "ToDo Update Format is as shown: \n"
                + "update [label] "
                + "/d [new description]) ";
    }
}
