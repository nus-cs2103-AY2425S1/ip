public abstract class Task {
    protected boolean done;
    protected String description;

    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }
    public Task(String description) {
        this.description = description;
        this.done = false;
    }
    public boolean toggleDone() {
        this.done = !this.done;
        return this.done;
    }
    @Override
    public String toString() {
        return (this.done ? "[X] " :"[ ] ") +  this.description;
    }
    public boolean isDone() {
        return this.done;
    }
}

