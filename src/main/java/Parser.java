import java.time.format.DateTimeFormatter;

public abstract class Parser {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yy");
    public abstract Task parse(String[] taskInfo);

}
