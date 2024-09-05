package chatgpt.task;

public class ToDos extends Task {

    public ToDos(String task) {
        super(task);
    }

    public ToDos(String task, boolean isCompleted) {
        super(task, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toPrint() {
        return "T " + super.toPrint();
    }
}
