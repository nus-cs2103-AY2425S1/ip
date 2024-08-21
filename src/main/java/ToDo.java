public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String mark = isDone() ? "X" : " ";
        return String.format("[T][%s] %s", mark, getDescription());
    }
}
