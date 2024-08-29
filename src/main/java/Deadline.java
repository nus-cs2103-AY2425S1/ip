import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private final static String TASK_TYPE = "D";
    private LocalDate deadline;
    public Deadline(String input, String date) throws AiException {
        super(input);

        try {
            if (date.contains("/")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                deadline = LocalDate.parse(date, formatter);
            } else {
                deadline = LocalDate.parse(date);
            }
        } catch (DateTimeParseException e) {
            throw new AiException("Awww shucksss, your date time format should either be d/M/yyyy HHmm or YYYY-MM-DD\n");
        }
    }

    public Deadline(String input, String date, Boolean isDone) {
        super(input, isDone);

        deadline = LocalDate.parse(date);
    }

    public boolean isEqual(LocalDate date) {
        return deadline.isEqual(date);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (%s)", TASK_TYPE, super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String stringData() {
        return String.format("%s | %s | %s", TASK_TYPE, super.stringData(), deadline);
    }
}
