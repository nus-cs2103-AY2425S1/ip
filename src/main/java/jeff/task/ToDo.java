package jeff.task;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveAsCSV() {
        return "T," + super.saveAsCSV();
    }
}
