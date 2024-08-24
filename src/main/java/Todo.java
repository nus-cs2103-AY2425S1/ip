import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Todo extends Task {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    public Todo(String description) {
        super(description, 'T', LocalDate.now().format(dateFormatter) + " 0000",
                LocalDate.now().format(dateFormatter) + " 0000");
    }

    public String getDescription() {
        return super.description;
    }
}
