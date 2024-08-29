public class ToDoTask extends Task {
    public ToDoTask(String task) {
        super(task);
    }

    @Override
    public String getStorageRepresentation() {
        if (this.isdone) {
            return "T|1|" + this.description;
        } else {
            return "T|0|" + this.description;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[T]");
        sb.append(super.toString());
        return sb.toString();
    }
}