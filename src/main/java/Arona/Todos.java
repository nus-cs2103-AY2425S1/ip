package Arona;

public class Todos extends Task {
    protected String by;

    public Todos(String description) {
        super(description);
    }

    public String getCategory() {
        return "[T]";
    }

    public String toFriendlyString() {
        return super.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
