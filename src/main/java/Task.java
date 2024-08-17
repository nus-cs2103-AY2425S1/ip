public class Task {
    private String name;
    private Boolean done;
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public void setUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return ("[" + (this.done ? "X" : " ") + "] " + this.name);
    }
}
