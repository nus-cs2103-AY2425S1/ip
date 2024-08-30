public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toParseableString() {
        String s = "t,";
        if (this.isCompleted()) {
            s += "m,";
        }
        else {
            s += "u,";
        }
        s += this.getName();
        return s;
    }
}
