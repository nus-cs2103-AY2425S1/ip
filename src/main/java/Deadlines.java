import java.util.List;

public class Deadlines extends Task {
    private String deadline;
    public Deadlines(String description) throws CommandFoundButInvalidException {
        super(description);
        super.description = this.getValidString(description)[0].trim();
        this.deadline = this.getValidString(description)[1].trim();
    }

    public String toString() {
        String str = " (by: " + deadline + ")";
        return "[D]" + super.toString() + str;
    }
    public String[] getValidString(String description) throws CommandFoundButInvalidException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        String[] inputArray = description.split("/by");
        if (inputArray.length == 2 && !inputArray[0].isEmpty() && !inputArray[1].isEmpty()) {
            return inputArray;
        }
        throw new InvalidSyntaxException("deadline");
    }

    public String message(List<Task> allTasks) {
        String str1 = "Got it. I've added this task:\n";
        String str2 = String.format("Now you have %d tasks in the list", allTasks.size());
        return str1 + this.toString() + "\n" + str2;
    }
}
