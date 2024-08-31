import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class DateParser {
    public static LocalDate parseDate(String input) {
        LocalDate parsedDate = null;

        //get dates for next week
        Map<String, LocalDate> map = buildMap();

        parsedDate = map.get(input.toLowerCase());

        if (parsedDate!=null) {
            return parsedDate;
        }

        DateTimeFormatter[] formatters = {
                //handle suffix (st, rd, etc)
                DateTimeFormatter.ofPattern("d['st']['nd']['rd']['th'] MMM yyyy"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/M/d"),
                DateTimeFormatter.ofPattern("yyyy-M-d"),
                DateTimeFormatter.ofPattern("d-M-yyyy")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                parsedDate = LocalDate.parse(input, formatter);
                break; // Exit loop if parsing is successful
            } catch (DateTimeParseException e) {
                // Continue to the next format if parsing fails
            }
        }

        if (parsedDate == null) {
            for (DateTimeFormatter formatter : formatters) {
                try {
                    parsedDate = LocalDate.parse(input + " " + LocalDate.now().getYear(), formatter);
                    break; // Exit loop if parsing is successful
                } catch (DateTimeParseException e) {
                    // Continue to the next format if parsing fails
                }
            }
        }

        return parsedDate;
    }

    private static Map<String, LocalDate> buildMap() {
        Map<String, LocalDate> map = new HashMap<>();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
            String dayOfWeek = date.getDayOfWeek().name().toLowerCase();
            map.put(dayOfWeek, date);
            map.put(dayOfWeek.substring(0, 3), date);
        }

        return map;
    }


    public static void main(String[] args) {
        System.out.println(parseDate("2/12/2019"));
        System.out.println(LocalTime.parse("07:30"));
    }
}