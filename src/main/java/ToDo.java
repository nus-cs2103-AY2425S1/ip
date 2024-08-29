public class ToDo extends Task {
    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
    @Override
    public String toStorageString() {
        return String.format("T | %s",this.taskString);
    }
}
