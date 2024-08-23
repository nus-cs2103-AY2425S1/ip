import java.util.List;

public class Events extends Task {
    private String startDate;
    private String endDate;
    public Events(String description) throws CommandFoundButInvalidException {
        super(description);
        String[] inputs = this.getValidString(description);
        super.description = inputs[0];
        this.startDate = inputs[1];
        this.endDate = inputs[2];
    }
    public String toString() {
        String str = " (from: " + startDate + " to: " + endDate + ")";
        return "[E]" + super.toString() + str;
    }
    public String[] getValidString(String description) throws CommandFoundButInvalidException{
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        String[] firstSplit = description.split("/from", 2);
        if (firstSplit.length == 2 && !firstSplit[0].isEmpty() && !firstSplit[1].isEmpty()) {
            String[] secondSplit = firstSplit[1].split("/to");
            if (secondSplit.length == 2 && !secondSplit[0].isEmpty() && !secondSplit[1].isEmpty()) {
                String desc = firstSplit[0].trim();
                String start = secondSplit[0].trim();
                String end = secondSplit[1].trim();
                return new String[]{desc, start, end};
            }
            throw new InvalidSyntaxException("event");
        }
        throw new InvalidSyntaxException("event");
    }

    public String message(List<Task> allTasks) {
        String str1 = "Got it. I've added this task:\n";
        String str2 = String.format("Now you have %d tasks in the list", allTasks.size());
        return str1 + this.toString() + "\n" + str2;
    }
}
