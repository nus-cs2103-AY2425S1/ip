public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String stringify() {
        String str = "[T]";
        str += super.stringify();
        return str;
    }
}
