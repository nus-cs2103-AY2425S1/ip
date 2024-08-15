public class ToDo extends Task {
    private static final String formatString = "[T][%s] %s";
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isComplete) {
        super(description,isComplete);
    }

    @Override
    public String toString() {
        return String.format(formatString,this.isComplete?"X":" ",this.description);
    }

    @Override
    public ToDo mark() {
        return new ToDo(this.description, true);
    }

    public ToDo unmark() {
        return new ToDo(this.description, false);
    }
}
