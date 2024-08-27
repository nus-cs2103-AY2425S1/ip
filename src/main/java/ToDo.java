public class ToDo extends Task {

    public ToDo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "T | 1 | " + this.getTitle();
        } else {
            return "T | 0 | " + this.getTitle();
        }
    }
}
