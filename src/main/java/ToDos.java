public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", super.getStatusIcons(), super.description);
    }

    @Override
    public String toFileString() {
        return String.format("T | %d | %s", this.done ? 1 : 0, this.description);
    }

}
