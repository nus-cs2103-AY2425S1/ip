import com.sun.source.util.TaskEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventParser {
    public static String parseDate(String eventString) throws DukeKorolev.ParseException {
        Pattern fromPattern = Pattern.compile("/from\\s+([\\w\\s]+)");
        Pattern toPattern = Pattern.compile("/to\\s+([\\w\\s]+)");
        Pattern byPattern = Pattern.compile("/by\\s+([\\w\\s]+)");

        Matcher fromMatcher = fromPattern.matcher(eventString);
        Matcher toMatcher = toPattern.matcher(eventString);
        Matcher byMatcher = byPattern.matcher(eventString);

        if (fromMatcher.find() && toMatcher.find()) {
            return "from: " + fromMatcher.group(1) + " " + "to: " + toMatcher.group(1);
        } else if (byMatcher.find()) {
            return "by: " + byMatcher.group(1);
        } else {
            throw new DukeKorolev.ParseException("the description of event is incorrect");
        }
    }

    public static String parseName(String start, String stop, String input)  throws DukeKorolev.ParseException {
        Pattern pattern = Pattern.compile(start + "\\s+(.+)\\s*" + stop);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new DukeKorolev.ParseException("description of event is not provided or incorrect.");
        }
    }

    public static KorolevTask parseLoadedRecord(String record) throws DukeException {
        char type = record.charAt(1);
        KorolevTask out = new KorolevTask("");
        switch (type) {
            case 'T' -> {
                return parseTodoRecord(record);
            }
            case 'D' -> {
                return parseDeadlineRecord(record);
            }
            case 'E' -> {
                return parseEventRecord(record);
            }
            default -> {
                throw new DukeException("Invalid record presented in the hard disk.");
            }
        }
    }

    private static KorolevTodo parseTodoRecord(String record) throws DukeException {
        Pattern p = Pattern.compile("\\]\\s(.+)");
        Matcher m = p.matcher(record);
        String taskDescription;
        if (m.find()) {
            taskDescription = m.group(1);
            return new KorolevTodo(taskDescription);
        } else {
            throw new DukeException("Fail to parse the record");
        }
    }

    private static KorolevDeadline parseDeadlineRecord(String record) throws DukeException {
        Pattern p1 = Pattern.compile("\\]\\s(.+)\\s\\(");
        Matcher m1 = p1.matcher(record);

        Pattern p2 = Pattern.compile("\\(from:\\s(.+)\\s*to");
        Matcher m2 = p2.matcher(record);
        String taskDescription, date;
        if (m1.find() && m2.find()) {
            taskDescription = m1.group(1);
            date = m2.group(1);
            return new KorolevDeadline(taskDescription, "by" + date);
        } else {
            throw new DukeException("Fail to parse record");
        }
    }

    private static KorolevEvent parseEventRecord(String record) throws DukeException {
        return new KorolevEvent("", "");
    }
}