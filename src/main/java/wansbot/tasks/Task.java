package wansbot.tasks;

public class Task {
    private String name;
    private boolean finished;

    public Task(String name) {
        this.name = name;
        this.finished = false;
    }

    // Returns status of task
    public boolean isDone() {
        return this.finished;
    }

    // finishes selected task
    public void finish() {
        this.finished = true;
    }

    // unchecks a finished task
    public void unfinish() {
        this.finished = false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
