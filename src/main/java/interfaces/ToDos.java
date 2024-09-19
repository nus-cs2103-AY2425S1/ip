package interfaces;

public class ToDos extends Task {
    /**
     * @inheritDoc
     */
    public ToDos(String description) {
        super(description.replace("todo ", ""));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String loadString() {
        return "todo " + this.description + " | " + this.isDone + " | " + this.tag;
    }
}
