public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String formatSave() {
        return "T | "
                + super.formatSave();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
