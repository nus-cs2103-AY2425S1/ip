public class Task {
    private String description;
    private boolean completion;

    public Task(String description) {
        this.description = description;
        this.completion = false;
    }

    public void setCompletion(boolean completion) {
        this.completion = completion;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (this.completion ? "X" : " ");
    }
}
