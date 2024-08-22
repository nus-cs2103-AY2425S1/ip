public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String temp = super.toString();
        return String.format("[T]%s", temp);
    }
}
