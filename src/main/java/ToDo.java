public class ToDo extends IndividualTask {
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String saveToFileFormat() {
        return "T | " + this.getSaveIcon() + " | " + this.getTaskDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
