public class TaskDescriptionEmptyException extends TestamentException {

    public TaskDescriptionEmptyException(String s) {
        super(String.format("It appears that you have not provided any details for this %s.",
                s));
    }

}
