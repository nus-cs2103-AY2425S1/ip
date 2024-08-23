public class MissingDeadlineException extends Exception {
    public MissingDeadlineException() {
        super(" oops! deadline cannot be empty! please enter a deadline after task type and task name. for example:\n" +
                "      deadline cs2100 quiz /by 2pm");
    }
}
