public class Todo extends Task {

    public Todo(String task, boolean isMarked) {
        super(task, isMarked);
    }

    @Override
    public String printTaskOnList() {
        if (isMarked) {
            return "[T][X] " + this.task;
        } else {
            return "[T][ ] " + this.task;
        }

    }

    @Override
    public String toFileFormat() {
        return "T | " + (this.isMarked ? "1" : "0") + " | " + this.task;
    }
}
