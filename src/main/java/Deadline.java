import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    LocalDate deadlineDate;
    public Deadline(String name, String[] deadlineInfo) {

        super(validateString(name, deadlineInfo));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        this.deadlineDate = LocalDate.parse(deadlineInfo[1], formatter);
    }

    private static String validateString(String name, String[] deadlineInfo) {
        if (name.isEmpty()) {
            throw new TarsException("Try again. Next time tell me what your deadline is");
        }

        if (deadlineInfo == null) {
            throw new TarsException("Add a /by command and a deadline date");
        }

        switch(deadlineInfo.length) {
            case 1:
                if (deadlineInfo[0].equals("by")) {
                    throw new TarsException("Finish the command by adding a deadline date");
                } else {
                    throw new TarsException("Add the /by command");
                }

            case 2:
                if (deadlineInfo[0].equals("by")) {
                    if (deadlineInfo[1].isEmpty()) {
                        throw new TarsException("Finish the command by adding a deadline date");
                    }
                } else {
                    throw new TarsException("Add the /by command");
                }
        }

        return name;
    }
    @Override
    public String saveTask() {

        return String.format("D|%s|%s|%s", super.getStatus(), super.getTask(), deadlineDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
