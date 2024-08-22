public class TaskDescriptionEmptyException extends Exception {

    public TaskDescriptionEmptyException(String s) {
        super(String.format("It appears that you have not provided any details for this %s.",
                s));
    }

}
