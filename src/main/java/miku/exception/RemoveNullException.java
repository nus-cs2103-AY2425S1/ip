package miku.exception;

/**
 * Thrown to indicate the index of the specified task is invalid,
 * Or that the task is invalid
 */
public class RemoveNullException extends Exception {
    private String desc;

    /**
     * Initialises a RemoveNullException.
     *
     * @param desc description of the error.
     */
    public RemoveNullException(String desc) {
        this.desc = desc;
    }

    /**
     * Prints the error message
     */
    public void print() {
        System.out.println("Can not remove a task using an invalid index: " + desc);
    }
}
