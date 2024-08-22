public abstract class Task {
    private boolean isComplete = false;
    private String title;

    public Task(String title) {
        this.title = title;
    }

    public void complete() throws AlreadyCompletedException {
        if (isComplete) {
            throw new AlreadyCompletedException();
        }
        isComplete = true;
    }

    public class AlreadyCompletedException extends Exception {
        private AlreadyCompletedException() {
            super("the task has already been completed");
        }
    }

    public String getStatusIcon() {
        return (isComplete ? "X" : " ");
    }

    @Override
    public String toString() {
        return title;
    }

    public abstract String getTypeIcon();
}
