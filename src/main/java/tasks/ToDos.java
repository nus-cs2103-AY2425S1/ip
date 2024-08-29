package tasks;

public class ToDos extends Task {

    public ToDos(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String getAdditionalInfo() {
        return "";
    }

    @Override
    public String toFileString() {
        return getType() + "|" + getName() + "|" + isComplete();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
