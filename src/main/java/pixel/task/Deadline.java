package pixel.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pixel.DateTimeParser;
import pixel.PixelException;

public class Deadline extends Task {
    private String type = "D";

    private static String modifyDescription(String des) throws PixelException {
        if (des.length() == 0) {
            throw new PixelException("OH NO!!! The description of Deadline cannot be empty!");
        }
        String regex = "(.*?) /by (.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(des);
        if (matcher.find()) {
            String initalDes = matcher.group(1);
            DateTimeParser time = new DateTimeParser(matcher.group(2));
            return String.format("%s by: %s", initalDes,
                    time);
        } else {
            throw new PixelException("Event should be of this format: {description} /by {date}");
        }
    }

    public Deadline(String description) throws PixelException {
        super(modifyDescription(description));
    }

    public Deadline(String description, String done) {
        super(description, done);
    }

    @Override
    public String getType() {
        return this.type;
    }

}
