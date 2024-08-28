public class Todo extends Task{
    public Todo(String description) throws InputFormatException{
        super(getDescription(description));
    }

    public String toFileFormatString() {
        return String.format("T | %s", super.toFileFormatString());
    }
    @Override
    public String toString() {
        return String.format("[T] %s\n", super.toString());
    }

    public static String getDescription(String input) throws InputFormatException{
        String[] todoSplit = input.split(" ", 2);
        if (todoSplit.length != 2) {
            throw new InputFormatException("Oops!  I need a description to save your task");
        }
        return todoSplit[1];
    }

    public static boolean matchTodo(String s) {
        return s.startsWith("todo");
    }
}
