public class Task {

    private boolean marked;
    private String taskRep;

    public Task(String taskRep) {
        this.marked = false;
        this.taskRep = taskRep;
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
