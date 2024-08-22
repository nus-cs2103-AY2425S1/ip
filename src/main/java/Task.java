public class Task {

    private final String taskName;
    private boolean completeStatus;

    public Task(String name) {
        taskName = name;

    }

    public void markDone() {
        completeStatus = true;
    }

    public void markUndone() {
        completeStatus = false;
    }

    @Override
    public String toString() {
        String status;

        if (completeStatus) {
            status = "[X]";

        } else {
            status = "[ ]";

        }

        return String.format("%s %s", status, taskName);
    }
}
