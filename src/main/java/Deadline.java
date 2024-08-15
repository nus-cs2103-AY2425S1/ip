public class Deadline extends Task {
    private final String by;
    private static final String formatString = "[D][%s] %s (by: %s)";

    public Deadline(String description, boolean isComplete, String by){
        super(description,isComplete);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format(formatString,this.isComplete?"X":" ",this.description,this.by);
    }

    @Override
    public Deadline mark() {
        return new Deadline(this.description, true,this.by);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(this.description, false,this.by);
    }

}
