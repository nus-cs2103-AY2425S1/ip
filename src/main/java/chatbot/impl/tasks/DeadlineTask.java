package chatbot.impl.tasks;

import chatbot.exceptions.InvalidMessageException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends AbstractTask {

    private final LocalDate deadline;

    public DeadlineTask(String description, String deadline) throws InvalidMessageException {
        super(description);

        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidMessageException("Sorry, date should be in yyyy-mm-dd format. :(");
        }
    }

    public static DeadlineTask deserialize(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length != 4 || !parts[0].equals("D")) {
            throw new IllegalArgumentException("Invalid DeadlineTask format");
        }

        DeadlineTask deadlineTask = null;
        try {
            deadlineTask = new DeadlineTask(parts[2], parts[3]);
            deadlineTask.setDone(parts[1].equals("1"));
        } catch (InvalidMessageException e) {
            e.printStackTrace();
        }

        return deadlineTask;
    }

    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline.format(DATE_OUTPUT_FORMAT));
    }

}
