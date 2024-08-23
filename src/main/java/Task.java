public class Task {
    private final String taskString;
    private boolean completed;
    public Task(String s) {
        this.taskString = s;
        completed = false;
    }
    public void mark() {
        this.completed = true;
    }
    public void unmark() {
        this.completed = false;
    }
    @Override
    public String toString() {
        String output = "";
        if (completed) {
            output = "[x]";
        } else {
            output = "[ ]";
        }
        return String.format("%s %s", output, taskString);
    }
}
