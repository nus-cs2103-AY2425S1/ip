import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Deadlines extends Task {
//    private String deadline;
    private LocalDateTime deadline;
    private String initDesc;
    public Deadlines(String description) throws CommandFoundButInvalidException {
        super(description);
        this.initDesc = description;
        super.description = this.getValidString(description)[0].trim();
        // restrict the usage to only "yyyy-mm-dd"
        // replace all the occurrence of "/" into "-"
        // check if is a valid LocalDate
        String date = this.getValidString(description)[1].trim();
        date = date.replace("/", "-");
        try {
            deadline = LocalDateTime.parse(date);
        } catch (DateTimeException e) {
            throw new InvalidSyntaxException("deadline, please use yyyy-mm-ddThh:mm. E.g. 2024-09-11T23:59");
        }
    }

    public String toString() {
        String str = " (by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyy HH:mm")) + ")";
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

    public String getInitDesc() {
        String str = super.isDone ? "1" : "0";
        return String.format("D | %s | %s", str, this.initDesc);
    }
}
