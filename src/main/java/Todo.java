public class Todo extends Task{
    public Todo(String description) {
        super(formatDescription(description));
    }

    @Override
    public String getTaskString() {
        return String.format("[T] [%s] %s\n",this.getStatusIcon(), this.getDescription());
    }

    @Override
    public String markAsDone() {
        return super.markAsDone() + String.format(" [T] [x] %s", this.getDescription());
    }

    @Override
    public String markAsUndone() {
        return super.markAsUndone() + String.format(" [T] [ ] %s",
                this.getDescription());
    }

    private static String formatDescription(String input) {
        return input.split(" ", 2)[1];
    }

    public static boolean matchTodo(String s) {
        return s.startsWith("todo");
    }
}
