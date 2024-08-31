public class Todo extends Task {

    protected String desc;

    public Todo(String description) {
        super(description);
        if (description.startsWith("todo ")) {
            this.desc = description.substring(5).trim();
        } else if (description.startsWith("[T][ ] ")) {
            this.desc = description.substring(7).trim();
            this.isDone = false;
        } else if (description.startsWith("[T][X] ")) {
            this.desc = description.substring(7).trim();
            this.isDone = true;
        }
    }

    public String toString() {
        return "[T]" + this.getStatusIcon() + this.desc;
    }
}
