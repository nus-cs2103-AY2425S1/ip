package echoa.exception;

/**
 * This class encapsulates errors relating to a todo update instructions.
 * It extends from the class UpdateFormatException.
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
