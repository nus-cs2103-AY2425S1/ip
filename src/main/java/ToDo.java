public class ToDo extends Task{
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "T | 1 | " + this.getDescription();
        } else {
            return "T | 0 | " + this.getDescription();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
