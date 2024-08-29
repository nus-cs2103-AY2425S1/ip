import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private String type = "E";

    private static String modifyDescription(String des) throws TaskException {
        if (des.length() == 0) {
            throw new TaskException("OH NO!!! The description of Event cannot be empty!");
        }
        String regex = "(.*?) /from (.*?) /to (.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(des);
        if (matcher.find()) {
            String initalDes = matcher.group(1);
            DateTimeParser fromTime = new DateTimeParser(matcher.group(2));
            DateTimeParser toTime = new DateTimeParser(matcher.group(3));
            return String.format("%s (from: %s to: %s)", initalDes,
                    fromTime,
                    toTime);
        } else {
            throw new TaskException("Event should be of this format: {description} /from {date} /to {date}");
        }
    }

    public Event(String description) throws TaskException {
        super(modifyDescription(description));
    }

    public Event(String description, String done) {
        super(description, done);
    }

    @Override
    public String getType() {
        return this.type;
    }
}
