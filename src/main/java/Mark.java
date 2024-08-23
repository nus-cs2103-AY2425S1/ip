import java.util.List;

public class Mark {
    // take in the description and arrayList
    private int index;
    private List<Task> allTasks;
    private Task targetTask;
    public Mark(String description, List<Task> allTasks) throws CommandFoundButInvalidException{
        try {
            this.allTasks = allTasks;
            if (description.isEmpty()) {
                throw new EmptyDescriptionException("mark");
            }
            this.index = Integer.parseInt(description) - 1;
            if (index < 0 || index >= allTasks.size()) {
                throw new InvalidSyntaxException("mark");
            }
            this.targetTask = allTasks.get(index);
            this.targetTask.markAsDone();
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException("mark");
        }
    }

    public String message() {
        return "Nice! I've marked this task as done:\n" + targetTask.toString();
    }
}
