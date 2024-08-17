public class Task {

    private final String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public boolean isComplete() {
        return this.isCompleted;
    }
    public void completeTask() {
        this.isCompleted = true;
    }

    public void uncompleteTask() {
        this.isCompleted = false;
    }
    @Override
    public String toString() {
        if (isCompleted) {
            return ("[X] " + this.name);
        }
        return ("[ ] " + this.name);
    }
}
