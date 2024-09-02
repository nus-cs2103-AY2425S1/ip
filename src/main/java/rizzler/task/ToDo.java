package rizzler.task;

public class ToDo extends Task {

    public ToDo(String todoDesc) {
        super(todoDesc);
    }

    public ToDo(String todoDesc, boolean isDone) {
        super(todoDesc, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getType() {
        return "ToDo";
    }

    @Override
    public String toTsv() {
        return getType() + "\t" + super.toTsv();
    }
}
