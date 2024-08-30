package snowy;

public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String temp = super.toString();
        return String.format("[T]%s", temp);
    }

    @Override
    public String toFileStorage() {
        String temp = super.toFileStorage();
        return String.format("T|%s", temp);
    }
}
