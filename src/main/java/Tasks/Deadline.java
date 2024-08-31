package Tasks;

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

    public static Deadline createNewDeadline(String userInput) throws InputMismatchException {
        if (!userInput.matches("^\\s+\\S.*\\s*/by\\s*\\S.*")) {
            throw new InputMismatchException("Please ensure that input contains description and end date TT");
        }

        String description = userInput.replaceAll("/by.*", "").trim();
        String deadlineText = userInput.replaceAll(".*/by", "").trim();

        try {
            LocalDate deadline = LocalDate.parse(deadlineText);
            return new Deadline(description, deadline);
        } catch (DateTimeParseException e) {
            System.out.println("Please insert a valid end date in the yyyy-mm-dd format!");
            throw new InputMismatchException("Please ensure your date is in yyyy-mm-dd format TT");
        }
    }

    public static Deadline createNewDeadline(String description, String deadlineText, boolean isMarked) {
        Deadline newDeadline = new Deadline(description, LocalDate.parse(deadlineText));
        if (isMarked) {
            newDeadline.completeTask();
        }
        return newDeadline;
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
