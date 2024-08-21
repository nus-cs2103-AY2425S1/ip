import java.lang.Exception;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }

    public static class EventParser {
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
            Pattern pattern = Pattern.compile(start + "\\s+(.*)\\s*" + stop);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                throw new DukeKorolev.ParseException("description of event is not provided or incorrect.");
            }
        }
    }
}
