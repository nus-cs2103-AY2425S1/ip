public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
