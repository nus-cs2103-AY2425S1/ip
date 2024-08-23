import java.util.List;

public class Unmark {
    // take in the description and arrayList
    private int index;
    private List<Task> allTasks;
    private Task targetTask;
    public Unmark(String description, List<Task> allTasks) throws CommandFoundButInvalidException{
        try {
            this.allTasks = allTasks;
            if (description.isEmpty()) {
                throw new EmptyDescriptionException("unmark");
            }
            this.index = Integer.parseInt(description) - 1;
            if (index < 0 || index >= allTasks.size()) {
                throw new InvalidSyntaxException("unmark");
            }
            this.targetTask = allTasks.get(index);
            this.targetTask.markAsNotDone();
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException("unmark");
        }
    }

    public String message() {
        return "OK, I've marked this task as not done yet:\n" + targetTask.toString();
    }
}
