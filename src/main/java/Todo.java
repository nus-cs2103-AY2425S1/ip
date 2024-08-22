public class Todo extends Task {

    public Todo(String s) {
        super(s);
    }

    public static Todo createTodo(String input) throws EmptyDescriptionException {
        String todoDescription = input.substring(4).trim();
        if (todoDescription.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        return new Todo(todoDescription);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
