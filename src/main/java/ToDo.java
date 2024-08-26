public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toStorageFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + desc;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
