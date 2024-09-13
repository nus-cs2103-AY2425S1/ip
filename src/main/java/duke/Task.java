package duke;

public class Task {
    final private String description;
    private boolean completed;

    public Task(String _description) {
        description = _description;
        completed = false;
    }

    public void mark() {
        completed = true;
    }

    public void unMark() {
        completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[X] " + description;
        }
        return "[] " + description;
    }

    public String saveFormat() {
        if (completed) {
            return "1 | " + description;
        }
        return "0 | " + description;
    }
}
