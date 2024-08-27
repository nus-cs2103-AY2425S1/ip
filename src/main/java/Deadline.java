import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime by;

    public Deadline(String taskDescription, String taskDeadline) throws MeejuException{
        super(taskDescription, false);
        String[] dateAndTime = taskDeadline.split(" ");
        String exceptionMessage = "I'm having a bit of trouble understanding the task.\n"
                + "Could you please explain it using the correct format?\n"
                + "The Correct format is -> deadline <desc> /by DD/MM/YYYY HHMM";
        if (dateAndTime.length != 2) {
            throw new MeejuException(exceptionMessage);
        }
        String dateRaw = dateAndTime[0];
        String timeRaw = dateAndTime[1];
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalDate date;
        LocalTime time;
        try {
            date = LocalDate.parse(dateRaw, dateFormatter);
            time = LocalTime.parse(timeRaw, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new MeejuException(exceptionMessage);
        }

        this.by = LocalDateTime.of(date, time);
    }

    private final String TASK_ICON = "[D]";

    public String getDeadline() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedDate = this.by.toLocalDate().format(dateFormatter);
        LocalTime time = this.by.toLocalTime();
        return formattedDate + " " + time + "HRS";
    }

    @Override
    public String serializeDetails() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = this.by.toLocalDate().format(dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String formattedTime = this.by.toLocalTime().format(timeFormatter);
        return "D !- " + this.getIsDone() + "!- "
                + this.getTaskDescription() + "!- "
                + formattedDate + " " + formattedTime + "\n";
    }

    @Override
    public String toString() {
        return TASK_ICON + super.toString() + " (by: " + this.getDeadline() + ")";
    }
}
