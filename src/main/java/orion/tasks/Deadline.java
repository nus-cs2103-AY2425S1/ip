package orion.tasks;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate time;
    public Deadline(String body, LocalDate time) {
        super(body);
        this.time = time;
    }

    public Deadline(String body, String deadline) throws OrionException {
        super(body);
        try {
            this.time = LocalDate.parse(deadline);
        } catch (DateTimeException e) {
            throw new OrionInputException("Correct syntax: deadline <task> /by <yyyy-mm-dd>." +
                    "Please input a valid date in the correct format!");
        }
    }

    public Deadline(String body, boolean isDone, LocalDate time) {
        super(body, isDone);
        this.time = time;
    }

    private String getTimeString() {
        return this.time.getDayOfMonth() + "/" + this.time.getMonthValue() + "/" + this.time.getYear();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (due by: %s)", super.toString(), getTimeString());
    }

    @Override
    public String saveString() {
        return "deadline," + super.saveString() + "," + this.time;
    }
}
