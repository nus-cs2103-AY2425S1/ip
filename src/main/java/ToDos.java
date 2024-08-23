import java.util.List;

public class ToDos extends Task{
    // essentially a Task without time
    // add into ArrayList, and then print the statements
    public ToDos(String description) throws InvalidSyntaxException, EmptyDescriptionException{
        super(description);
        // check if description is valid here
        // if empty description is empty we throw
        super.description = this.getValidString(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
    // passes in a String description
    private String getValidString(String description) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        } else {
            return description;
        }
    }

    public String message(List<Task> allTasks) {
        String str1 = "Got it. I've added this task:\n";
        String str2 = String.format("Now you have %d tasks in the list", allTasks.size());
        return str1 + this.toString() + "\n" + str2;
    }
}
