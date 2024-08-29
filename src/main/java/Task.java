public class Task {
    private String description;
    private boolean done;

    Task(String description) {
        this.description = description;
        this.done = false;
    }
    
    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String pref = done ? "[X] " : "[ ] ";
        return pref + description;
    }
}
