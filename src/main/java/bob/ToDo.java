package bob;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getSaveFormat() {
        return "T | " + super.getSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo temp) {
            return super.equals(temp);
        }
        return false;
    }
}
