package Tasks;

import java.util.InputMismatchException;

public class Deadline extends Task {
    
    private String deadline;

    private Deadline(String description, String deadline) {
        super(description); 
        this.deadline = deadline; 
    }

    public static Deadline createNewDeadline(String userInput) throws InputMismatchException {
        if (!userInput.matches("^\\s+\\S.*\\s*/by\\s*\\S.*")) {
            throw new InputMismatchException("Ensure that input contains description and end date");
        }

        String description = userInput.replaceAll("/by.*", "").trim();
        String deadline = userInput.replaceAll(".*/by", "").trim();
        return new Deadline(description, deadline);
    }

    public static Deadline createNewDeadline(String description, String deadline, boolean isMarked) {
        Deadline newDeadline = new Deadline(description, deadline);
        if (isMarked) {
            newDeadline.completeTask();
        }
        return newDeadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
