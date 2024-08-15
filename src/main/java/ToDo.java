public class ToDo extends Input{

    public ToDo(String input) {
        super(input.split(" ", 2)[1]);

    }

    @Override
    public String toString() {
        String str = "[T] " + super.toString();
        return str;
    }
}
