import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    private LocalDateTime parseDate(String s) {
        try {
            return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException dte) {
            System.out.println("   Input date and time format at yyyy-mm-dd tttt");
        }
        return null;
    }

    public void parseDeadline(String s, TaskList tasks) {
        String[] info = s.split(" /by ", 2);
        LocalDateTime by = this.parseDate(info[1]);
        if (by != null) {
            Task curr = new Deadline(info[0], by);
            tasks.add(curr);
            ui.printStatus(curr, tasks.size());
        }
    }

    public void parseEvent(String s, TaskList tasks) {
        String[] info = s.split(" /from | /to ", 3);
        LocalDateTime from = this.parseDate(info[1]);
        if (from != null) {
            LocalDateTime to = this.parseDate(info[2]);
            if (to != null) {
                Task curr = new Event(info[0], from, to);
                tasks.add(curr);
                ui.printStatus(curr, tasks.size());
            }
        }
    }
}
