public abstract class MarkUnmarkTaskInputException extends Exception {

    public MarkUnmarkTaskInputException(String action) {
        super(action + " task input must be in the form of "
                + "`" + action + " [integer], where integer is the task number in" +
                "task list");
    }
}
