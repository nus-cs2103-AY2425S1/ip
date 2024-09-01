package FlyChat.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class Deadline extends Task {
    
    private LocalDate deadline;

    private Deadline(String description, LocalDate deadline) {
        super(description); 
        this.deadline = deadline; 
    }

    public static Deadline createNewDeadline(String description, String deadlineText, boolean isMarked) throws InputMismatchException {
        if (description.equals("")) {
            throw new InputMismatchException("Please ensure that input contains description");
        }

        try {
            Deadline newDeadline = new Deadline(description, LocalDate.parse(deadlineText));
            if (isMarked) {
                newDeadline.completeTask();
            }
            return newDeadline;
        } catch (DateTimeParseException e) {
            throw new InputMismatchException("Please ensure that input contains end date formatted in the yyyy-mm-dd format");
        }
    }

    @Override
    public String formatStringForSaving() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MM dd yyyy")) + ")";
    }
}
