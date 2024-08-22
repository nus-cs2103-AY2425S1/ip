public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String taskDescription() {
        return "[T]" + showMark() + " " + this.name;
    }
}
