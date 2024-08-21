public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public Task createTask(String input) {
        String description = input.substring(5).trim();
        return new ToDo(description);
    }

    public static void showUsage() {
        System.out.println("To add a ToDo task, use the following format:");
        System.out.println("todo <task description>");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
