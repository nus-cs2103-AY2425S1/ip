import java.util.Arrays;

public class ToDos extends Task{
    public ToDos(String description) {
        super(formatDescription(description));
    }

    @Override
    public String getTaskString() {
        return String.format("[T] [%s] %s\n",this.getStatusIcon(), this.getDescription());
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
        String response = String.format("Nice! I've marked this task as done:\n [T] [x] %s", this.getDescription());
        ScoobyDoo.printFormattedResponse(response);
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
        String response = String.format("OK, I've marked this task as not done yet:\n [T] [ ] %s",
                this.getDescription());
        ScoobyDoo.printFormattedResponse(response);
    }

    private static String formatDescription(String input) {
        return input.split(" ", 2)[1];
    }

    public static boolean matchTodo(String s) {
        return s.startsWith("todo");
    }
}
