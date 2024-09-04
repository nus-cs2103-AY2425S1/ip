public class ToDos extends Task {

    public ToDos(String task) {
        super(task);
    }

    public ToDos(String task, Boolean isCompleted) {
        super(task, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toPrint() {
        return "todo " + super.toPrint();
    }
}
