public class ToDoTask extends Task {
    public ToDoTask(String task) {
        super(task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[T]");
        sb.append(super.toString());
        return sb.toString();
    }
}