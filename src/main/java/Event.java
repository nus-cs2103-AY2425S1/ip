import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    private LocalDateTime start;
    private LocalDateTime end;
    private static boolean withTime;
    private Event(String str, LocalDateTime start, LocalDateTime end, boolean marked) {
        super(str, marked);
        this.start = start;
        this.end = end;
    }

    public static Event of(String s) throws BigdogException {
        if (s.length() <= 6) {
            throw new BigdogException("event can't be empty! If theres no event then go and sleep!");
        }

        int dashCounter = 0;
        for (int j = s.length() - 1; j > 5; j--) {
            if (s.charAt(j) == '/') {
                dashCounter++;
            }
            if (s.charAt(j) == '/' && dashCounter == 3) {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '/') {
                        return new Event(s.substring(6, i - 1), stringToDate(s.substring(i + 6, j - 1)),
                                stringToDate(s.substring(j + 4)), false);
                    }
                }
            }
        }
        throw new BigdogException("Event has to have a start and end!");
    }

    public static Event of(String s, boolean marked) throws BigdogException {


        if (s.length() <= 4) {
            throw new BigdogException("data file corrupted! Cause: " + s);
        }

            for (int j = s.length() - 1; j > 3; j--) {
                if (s.charAt(j) != '|') {
                    continue;
                }
                LocalDateTime end = LocalDateTime.parse(s.substring(j + 2));
                for (int i = 5; i < s.length(); i++) {
                    if (s.charAt(i) == '|') {
                        return new Event(s.substring(4, i - 1), LocalDateTime.parse(s.substring(i + 2, j - 1)),
                                end, marked);
                    }
                }

            }
        throw new BigdogException("data file corrupted! Cause: " + s);
    }

    private static LocalDateTime stringToDate(String str) throws BigdogException {
        long dashes = str.chars().filter(x -> x == '/').count();
        if (dashes != 2) {
            throw new BigdogException("Invalid date format :" + str);
        } else {
            String[] getTime = str.split(" ");
            String[] getDate = getTime[0].split("/");
            System.out.println(str);
            String year = getDate[2];
            String month = getDate[1];
            String day = getDate[0];

            try {
                if (getTime.length == 2) {
                    withTime = true;
                    return LocalDateTime.parse(String.format("%s-%s-%sT%s", year, month, day, getTime[1]));
                } else if (getTime.length == 1) {
                    withTime = false;
                    return LocalDateTime.parse(String.format("%s-%s-%sT%s", year, month, day, "00:00"));
                } else {
                    throw new BigdogException("Invalid date format :" + str +
                            "\nExample correct format: event meeting with John /from 02/07/2019 18:00 /to 02/07/2019 20:00");
                }
            } catch (DateTimeParseException e) {
            throw new BigdogException("Invalid date format :" + str +
                    "\nExample correct format: deadline return book /by 02/07/2019 18:00");
            }
        }
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + this.start + " | " + this.end;
    }


    @Override
    public String toString() {
        if (withTime) {
            return "[E]" + super.toString() + " (from: "
                    + this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + " to: "
                    + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
        } else {
            return "[E]" + super.toString() + " (from: "
                    + this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " to: "
                    + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
        }
    }

}
