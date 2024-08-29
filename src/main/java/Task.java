import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    public Task(String description) {
            this.description = description;
            this.isDone = false;
            this.dateTime = LocalDateTime.now();
    }

    public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }

    public String changeDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        try {
            if (input.split(" ").length < 2) {
                String[] dateSplit = input.split("/");
                if (dateSplit.length != 3 || dateSplit[0].trim().length() != 4) {
                    throw new WrongDateTimeFormatException("Please input the date in the YYYY/MM/DD format so that I can help you save it!");
                } else {
                    this.dateTime = LocalDateTime.of(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]), 23, 59);
                }
            } else {
                String date = input.split(" ")[0];
                String time = input.split(" ")[1];
                String[] dateSplit = date.split("/");
                String[] timeSplit = time.split("-");

                if (dateSplit.length != 3 || time.length() != 5) {
                    throw new WrongDateTimeFormatException("Please input the date in the YYYY/MM/DD format and time in HH-MM format so that I can help you save them!");
                } else {
                    this.dateTime = LocalDateTime.of(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]),
                            Integer.parseInt(timeSplit[0]), Integer.parseInt(timeSplit[1]));
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
        }
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}

