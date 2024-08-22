public class Todo extends Task {

    public Todo(String task) {
        super(task);
    }

    @Override
    public String printTaskOnList() {
        if (marked) {
            return "[T][X] " + this.task;
        } else {
            return "[T][ ] " + this.task;
        }

    }
}
