package Dook.Tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String fileFormatted() {
        return "T | " + super.fileFormatted();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Todo)) {
            return false;
        } else if (o == this) {
            return true;
        }

        Todo t = (Todo) o;

        return t.description.equals(this.description);
    }
}
