public abstract class Task {
    private String task;
    private boolean isCompleted;

    public Task(String task) throws IllegalArgumentException {
        if (task.isEmpty() || task.equals(" ")){
            throw new IllegalArgumentException("\t Oh no!!(0o0) Description cannot be empty");
        }
        this.task = task;
        this.isCompleted = false;
    }

    public Task(String task, Boolean isCompleted) throws IllegalArgumentException {
        if (task.isEmpty() || task.equals(" ")){
            throw new IllegalArgumentException("\t Oh no!!(0o0) Description cannot be empty");
        }
        this.task = task;
        this.isCompleted = isCompleted;
    }

    public String setCompleted(boolean completed) {
        if (this.isCompleted == completed) {
            throw new IllegalArgumentException("\t It seems the task has already been marked as such");
        }
        this.isCompleted = completed;
        String output = completed ?
                "\t Nice! I've marked this task as done: \n\t  " + this.toString()
                : "\t OK, I've marked this task as not done yet: \n\t " + this.toString();
        return output;
    }

    @Override
    public String toString() {
        String output = isCompleted ? "[X] " + task : "[ ] " + task;
        return output;
    }

    public String toPrint(){
        return isCompleted ? "|1|" + task : "|0|" + task;
    }
}
