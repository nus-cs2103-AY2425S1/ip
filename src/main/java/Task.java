public class Task {
    private String task;
    private boolean completed;

    public Task(String task) throws IllegalArgumentException {
        if (task.isEmpty() || task.equals(" ")){
            throw new IllegalArgumentException("\t Oh no!!(0o0) Description cannot be empty");
        }
        this.task = task;
        this.completed = false;
    }

    public String setCompleted(boolean completed) {
        if (this.completed == completed) {
            throw new IllegalArgumentException("\t It seems the task has already been marked as such");
        }
        this.completed = completed;
        String output = completed ?
                "\t Nice! I've marked this task as done: \n\t  " + this.toString()
                : "\t OK, I've marked this task as not done yet: \n\t " + this.toString();
        return output;
    }

    @Override
    public String toString() {
        String output = completed ? "[X] " + task : "[ ] " + task;
        return output;
    }
}
