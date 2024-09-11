package duck.exceptions;

public class CheckUsageException extends UsageException {
    public CheckUsageException(String command) {
        super(command + " <task_number>", "task_number");
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "tip: Enter \"list\" to see the list of tasks and their numbers.";
    }
}
