package tasklist;

public class Task {
    String name;
    boolean done = false;
    
    public Task(String value) {
        this.name = value;
    }

    public void markAsDone() {
        done = true;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + name; 
    }
}
