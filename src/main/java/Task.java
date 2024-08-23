public class Task {
    private String task;
    private boolean completed;

    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    public String setCompleted(boolean completed) {
        this.completed = completed;
        String output = completed ? "\t Nice! I've marked this task as done: \n\t[X] " + task
                : "\t OK, I've marked this task as not done yet: \n\t [ ] " + task;
        return output;
    }

    @Override
    public String toString() {
        String output = completed ? "[X] " + task : "[ ] " + task;
        return output;
    }
}
