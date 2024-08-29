package jarvis;

public abstract class Task {
    private String name;
    private boolean done;

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task(String name) {
            this.name = name;
        }
}
