package cloud.task;

public class Todo extends Task {

    /**
     * Constructs a Todo object
     * @param desc description of the task
     */
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
