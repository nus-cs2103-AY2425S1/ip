public class Todo extends Task{

    private Todo(String str, boolean marked) {
        super(str, marked);
    }

    public static Todo of(String s) throws BigdogException {
        if (s.isEmpty()) {
            throw new BigdogException("todo can't be empty! How can you do nothing!");
        }
        return new Todo(s, false);
    }

    public static Todo of(String s, boolean marked) throws BigdogException {
        if (s.length() <= 4) {
            throw new BigdogException("todo can't be empty! How can you do nothing!");
        }
        return new Todo(s.substring(4), marked);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

