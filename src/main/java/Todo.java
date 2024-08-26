public class Todo extends Task{

    private Todo(String str) {
        super(str);
    }

    public static Todo of(String s) throws BigdogException {
        if (s.length() <= 5) {
            throw new BigdogException("todo can't be empty! How can you do nothing!");
        }
        return new Todo(s.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
