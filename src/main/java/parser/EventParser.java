package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.ParseException;
import task.KorolevDeadline;
import task.KorolevEvent;
import task.KorolevTask;
import task.KorolevTodo;

public class EventParser {
    private static final String PARSE_WARNING = "fail to parse the record";
    private static final String EMPTY_WARNING = "description of event is not provided or incorrect.";
    private static final String INVALID_RECORD_WARNING = "Invalid record presented in the hard disk.";

    /**
     * Analyses the input string and extracts any substring appearing between
     * stop and start.
     *
     * @param start the start of the expected string
     * @param stop the end of the expected string
     * @param input string to be parsed
     * @return content between start and stop
     * @throws ParseException when cannot find any content with given start and stop
     */
    public static String parseName(String start, String stop, String input) throws ParseException {
        Pattern pattern = Pattern.compile(start + "\\s+(.+)\\s*" + stop);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new ParseException(EMPTY_WARNING);
        }
    }

    /**
     * Parses the records read from local data file.
     *
     * @param record each line of record
     * @return the KorolevTask object
     * @throws ParseException when the line of record cannot be parsed
     */
    public static KorolevTask parseLoadedRecord(String record) throws ParseException {
        KorolevTask t;

        char type = record.charAt(1);
        switch (type) {
        case 'T' -> {
            t = parseTodoRecord(record);
        }
        case 'D' -> {
            t = parseDeadlineRecord(record);
        }
        case 'E' -> {
            t = parseEventRecord(record);
        }
        default -> throw new ParseException(INVALID_RECORD_WARNING);
        }
        String tag = parseTag(record);
        if (tag != null) {
            t.tag(tag);
        }
        return t;
    }

    private static KorolevTodo parseTodoRecord(String record) throws ParseException {
        Pattern p = Pattern.compile("\\]\\s(.+)\\s*\\[tag");
        Matcher m = p.matcher(record);
        String taskDescription;

        if (m.find()) {
            taskDescription = m.group(1);
            KorolevTodo out = new KorolevTodo(taskDescription.strip());
            if (isComplete(record)) {
                out.markTask();
            }
            return out;
        } else {
            throw new ParseException(PARSE_WARNING);
        }
    }

    private static KorolevDeadline parseDeadlineRecord(String record) throws ParseException {
        Pattern p1 = Pattern.compile("\\]\\s(.+)\\s\\(");
        Matcher m1 = p1.matcher(record);

        Pattern p2 = Pattern.compile("by\\s(.+)\\)");
        Matcher m2 = p2.matcher(record);
        String taskDescription, date;

        if (m1.find() && m2.find()) {
            taskDescription = m1.group(1);
            date = DateParser.parseRecordedDate(m2.group(1));
            KorolevDeadline out = new KorolevDeadline(taskDescription, date);
            if (isComplete(record)) {
                out.markTask();
            }
            return out;
        } else {
            throw new ParseException(PARSE_WARNING);
        }
    }

    private static KorolevEvent parseEventRecord(String record) throws ParseException {
        Pattern namePattern = Pattern.compile("\\]\\s(.+)\\s\\(");
        Matcher m1 = namePattern.matcher(record);

        Pattern fromPattern = Pattern.compile("from:\\s(.+)\\s*to");
        Matcher m2 = fromPattern.matcher(record);

        Pattern toPattern = Pattern.compile("to:\\s(.+)\\s*\\)");
        Matcher m3 = toPattern.matcher(record);
        String taskDescription, from, to;

        if (m1.find() && m2.find() && m3.find()) {
            taskDescription = m1.group(1);
            from = DateParser.parseRecordedDate(m2.group(1).strip());
            to = DateParser.parseRecordedDate(m3.group(1).strip());
            KorolevEvent out = new KorolevEvent(taskDescription, from, to);
            if (isComplete(record)) {
                out.markTask();
            }
            return out;
        } else {
            throw new ParseException(PARSE_WARNING);
        }
    }

    private static String parseTag(String record) {
        Pattern tagPattern = Pattern.compile("\\[tag:(.+)\\]");
        Matcher m1 = tagPattern.matcher(record);

        if (m1.find()) {
            return m1.group(1);
        } else {
            return null;
        }
    }

    private static boolean isComplete(String record) {
        return record.contains("[X]");
    }
}
