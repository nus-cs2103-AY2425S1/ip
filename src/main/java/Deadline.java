import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime end;
    private static boolean withTime;

    private Deadline(String str, LocalDateTime end, boolean marked) {
        super(str, marked);
        this.end = end;
    }

    public static Deadline of(String s) throws BigdogException {
        if (s.length() <= 9) {
            throw new BigdogException("deadline can't be empty! Theres nothing to do!");
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '/') {
                return new Deadline(s.substring(9, i - 1), stringToDate(s.substring(i + 4)), false);
            }
        }
        throw new BigdogException("Come on! Set a due by date and get to work!");
    }
    public static Deadline of(String s, boolean marked) throws BigdogException {
        if (s.length() <= 4) {
            throw new BigdogException("data file corrupted! Cause: " + s);
        }

        for (int i = 5; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                LocalDateTime end = LocalDateTime.parse(s.substring(i + 2));
                return new Deadline(s.substring(4, i - 1), end, marked);
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
                            "\nExample correct format: deadline return book /by 02/07/2019 18:00");
                }
            } catch (DateTimeParseException e) {
                throw new BigdogException("Invalid date format :" + str +
                        "\nExample correct format: deadline return book /by 02/07/2019 18:00");
            }

        }
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + this.end;
    }


    @Override
    public String toString() {
        if (withTime) {
            return "[D]" + super.toString() + " (by: " +
                    this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " +
                    this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
        }
    }

}
