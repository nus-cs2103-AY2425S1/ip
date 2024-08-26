public class Task {

    private boolean marked;
    private String taskRep;

    protected Task(String taskRep) {
        this.marked = false;
        this.taskRep = taskRep;
    }

    public static Task of(String task) throws BigdogException {
        if (task.startsWith("todo")) {
            return Todo.of(task);
        } else if (task.startsWith("deadline")) {
            return Deadline.of(task);
        } else if (task.startsWith("event")) {
            return Event.of(task);
        } else {
            throw new BigdogException("Sorry I only recognise todo, deadline, or event tasks!");
        }
    }
    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    @Override
    public String toString() {
        if (marked) {
            return "[X] " + taskRep;
        } else {
            return "[ ] " + taskRep;
        }
    }


}
