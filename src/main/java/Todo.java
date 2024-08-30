public class Todo extends Task{
    public Todo(String description) throws InputFormatException{
        super(description);
    }

    public String toFileFormatString() {
        return String.format("T | %s", super.toFileFormatString());
    }
    @Override
    public String toString() {
        return String.format("[T] %s\n", super.toString());
    }


    public static boolean matchTodo(String s) {
        return s.startsWith("todo");
    }
}
