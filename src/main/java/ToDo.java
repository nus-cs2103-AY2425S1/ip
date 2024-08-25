public class ToDo extends Task{

    public ToDo(String description, TaskType type) {
        super(description, type);
    }

    public ToDo(String description, boolean isDone, TaskType type) {
        super(description, isDone, type);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String formatToSave() {
        return super.formatToSave();
    }
}
