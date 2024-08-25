public class Todo extends Task{
    public Todo(String description) {
        super(description);
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

    public static String checkTodoFormat(String input) throws InputFormatException{
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
